package shops;

public class Door {
	private boolean hasTreasure;
	private boolean isOpened;

	Door() {
		this.hasTreasure = false;
		this.isOpened = false;
	}

	void hideTheTreasure() {
		this.hasTreasure = true;
	}

	void open() {
		this.isOpened = true;
	}
}
