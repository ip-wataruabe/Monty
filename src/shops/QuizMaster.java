package shops;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class QuizMaster {
	private Map<Integer, Door> doors;

	public void lineUpDoors(int numberOfAlternatives) {
		doors = new HashMap<Integer, Door>();
		for(int i = 0; i < numberOfAlternatives; i++) {
			doors.put(new Integer(numberOfAlternatives) ,new Door());
		}

		int coiceOfRightAnswer = getRandomOneCoiceOf(numberOfAlternatives);

		doors.get(coiceOfRightAnswer).hideTheTreasure();
	}

	private int getRandomOneCoiceOf(int numberOfAlternatives) {
		Random dice = new Random();
		return dice.nextInt(numberOfAlternatives);
	}
}
