package shops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class QuizMaster {
	private Map<Integer, Door> doors;

	public void lineUpDoors(int numberOfAlternatives) {
		doors = new HashMap<Integer, Door>();
		for(int i = 0; i < numberOfAlternatives; i++) {
			doors.put(new Integer(i), new Door());
		}

		int choiceOfRightAnswer = getRandomOneChoiceOf(numberOfAlternatives);

		doors.get(new Integer(choiceOfRightAnswer)).hideTheTreasure();
	}

	public void openAnEnptyUnselectedDoor(Integer selected) {
		Set<Integer> emptyUnselectedDoors = new HashSet<Integer>(doors.keySet());
		emptyUnselectedDoors.remove(selected);

		Integer unselectedTreasureKey = null;
		for(Integer doorKey: emptyUnselectedDoors) {
			if (this.doors.get(doorKey).hasTreasure()){
				unselectedTreasureKey = doorKey;
			}
		}

		if(unselectedTreasureKey != null) {
			emptyUnselectedDoors.remove(unselectedTreasureKey);
		}

		List<Integer> choicesList = new ArrayList<>(emptyUnselectedDoors);

		int choiceIndex = getRandomOneChoiceOf(choicesList.size());

		doors.get(choicesList.get(choiceIndex)).open();
	}

	public boolean hasOpenedTheDoor(Integer doorKey) {
		return doors.get(doorKey).isOpened();
	}

	private int getRandomOneChoiceOf(int numberOfAlternatives) {
		Random dice = new Random();
		return dice.nextInt(numberOfAlternatives);
	}
}
