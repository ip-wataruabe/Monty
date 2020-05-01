package street;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Integer theFirstChoice = nameless.makeTheFirstAnswer(choices);
	}
}