package shops;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Answerer {
	public Integer makeAManualChoice(Map<Integer, String> choices, Scanner scan) {
		Integer answer;

		do {
			String input = scan.nextLine();

			answer = Integer.valueOf(input);
		} while (!choices.containsKey(answer));

		return answer;
	}

	public Integer makeAnAutoChoice(Map<Integer, String> choices) {
		List<Integer> choicesList = new ArrayList<>(choices.keySet());

		Random dice = new Random();
		int choiceIndex = dice.nextInt(choicesList.size());

		return choicesList.get(choiceIndex);
	}
}
