package street;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import shops.Answerer;
import shops.Answerer.ChoiceMode;
import shops.QuizMaster;

public class FirstAvenue {
	static List<String> choicesOnProgram = Arrays.asList("Left", "Center", "Right");

	public static void main(String[] args) {
		System.out.println("Hello, Mr.Hall");

		Map<Integer, String> choices = new HashMap<Integer, String>();

		int index = 0;
		for(String nameOfChoice: choicesOnProgram) {
			choices.put(index, nameOfChoice);
			index++;
		}

		QuizMaster hall = new QuizMaster();
		hall.lineUpDoors(choices.size());

		Answerer nameless = new Answerer(ChoiceMode.manual);


		System.out.println("Select your answer");
		choices.forEach((Integer i, String name) -> {
			System.out.print(i.toString() + " (" + name + "),");
		});
		System.out.println();
		Scanner scan = new Scanner(System.in);
		Integer theFirstChoice = nameless.makeAChoice(choices, scan);

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
		Integer theLastChoice = nameless.makeAChoice(choices, scan);
		scan.close();

		if(hall.hasHideenTheTreasureIn(theLastChoice)) {
			System.out.println("You've got the treasure!!");
		} else {
			System.out.println("You selected a wrong door...");
		}
	}
}