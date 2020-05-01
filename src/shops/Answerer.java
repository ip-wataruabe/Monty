package shops;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Answerer {
	private ChoiceMode mode;

	public enum ChoiceMode{
		auto,
		manual
	}

	public Answerer (ChoiceMode mode) {
		this.mode = mode;
	}

	public Integer makeAChoice(Map<Integer, String> choices, Scanner scan) {
		Integer answer;
		if (mode == ChoiceMode.manual) {
			answer = makeAManualChoice(choices, scan);
		} else {
			answer = makeAnAutoChoice(choices);
		}

		return answer;
	}

	private Integer makeAManualChoice(Map<Integer, String> choices, Scanner scan) {
		Integer answer;

		do {
			System.out.println("Select your answer");
			choices.forEach((Integer index, String name) -> {
				System.out.print(index.toString() + " (" + name + "),");
			});
			System.out.println();
			String input = scan.nextLine();

			answer = Integer.valueOf(input);
		} while (!choices.containsKey(answer));

		return answer;
	}

	private Integer makeAnAutoChoice(Map<Integer, String> choices) {
		List<Integer> choicesList = new ArrayList<>(choices.keySet());

		Random dice = new Random();
		int choiceIndex = dice.nextInt(choicesList.size());

		return choicesList.get(choiceIndex);
	}
}
