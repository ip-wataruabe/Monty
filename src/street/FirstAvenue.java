package street;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import shops.Answerer;
import shops.QuizMaster;

public class FirstAvenue {
	static List<String> choicesOnProgram = Arrays.asList("Left", "Center", "Right");

	enum AutomationType {
		flex,
		fixed
	};

	public static void main(String[] args) {
		System.out.println("Hello, Mr.Hall");

		manualPlay();

		int treasuresOnFlexStrategy = 0;
		for(int i = 0; i < 10000; i++) {
			treasuresOnFlexStrategy += autoPlay(AutomationType.flex);
		}
		System.out.println("If you changed the choice, you can get " + treasuresOnFlexStrategy + " treasures in 10,000 trials ");


		int treasuresOnFixedStrategy = 0;
		for(int i = 0; i < 10000; i++) {
			treasuresOnFixedStrategy += autoPlay(AutomationType.fixed);
		}
		System.out.println("If you don't changed the choice, you can get " + treasuresOnFixedStrategy + " treasures in 10,000 trials ");
	}

	private static void manualPlay() {
		Map<Integer, String> choices = new HashMap<Integer, String>();

		int index = 0;
		for(String nameOfChoice: choicesOnProgram) {
			choices.put(index, nameOfChoice);
			index++;
		}

		QuizMaster hall = new QuizMaster();
		hall.lineUpDoors(choices.size());

		Answerer nameless = new Answerer();

		System.out.println("Select your answer");
		choices.forEach((Integer i, String name) -> {
			System.out.print(i.toString() + " (" + name + "),");
		});
		System.out.println();
		Scanner scan = new Scanner(System.in);
		Integer theFirstChoice = nameless.makeAManualChoice(choices, scan);

		hall.openAnEnptyUnselectedDoor(theFirstChoice);

		Integer openedKey = null;
		for(Integer doorKey: choices.keySet()) {
			if(hall.hasOpenedTheDoor(doorKey)) {
				openedKey = doorKey;
			}
		}

		if(openedKey != null) {
			String openedDoorName = choices.get(openedKey);
			choices.remove(openedKey);
			System.out.println(
					"The door " + openedKey.toString()
					+ "(" + openedDoorName + ") has been opened.");
		}

		System.out.println("Reselect your answer");
		choices.forEach((Integer j, String name) -> {
			System.out.print(j.toString() + " (" + name + "),");
		});
		System.out.println();
		Integer theLastChoice = nameless.makeAManualChoice(choices, scan);
		scan.close();

		if(hall.hasHideenTheTreasureIn(theLastChoice)) {
			System.out.println("You've got the treasure!!");
		} else {
			System.out.println("You selected a wrong door...");
		}
	}

	private static int autoPlay(AutomationType automationType) {
		Map<Integer, String> choices = new HashMap<Integer, String>();

		int index = 0;
		for(String nameOfChoice: choicesOnProgram) {
			choices.put(index, nameOfChoice);
			index++;
		}

		QuizMaster hall = new QuizMaster();
		hall.lineUpDoors(choices.size());

		Answerer nameless = new Answerer();
		Integer theFirstChoice = nameless.makeAnAutoChoice(choices);

		hall.openAnEnptyUnselectedDoor(theFirstChoice);

		Integer openedKey = null;
		for(Integer doorKey: choices.keySet()) {
			if(hall.hasOpenedTheDoor(doorKey)) {
				openedKey = doorKey;
			}
		}

		if(openedKey != null) {
			choices.remove(openedKey);
		}

		Integer theLastChoice;
		if(automationType == AutomationType.flex) {
			choices.remove(theFirstChoice);
			theLastChoice = nameless.makeAnAutoChoice(choices);
		} else {
			theLastChoice = theFirstChoice;
		}

		if(hall.hasHideenTheTreasureIn(theLastChoice)) {
			return 1;
		} else {
			return 0;
		}
	}
}