package ECM2414;

public class Card {
	private int faceValue;

	public Card(int faceValue) {
		super();
		this.faceValue = faceValue;
	}

	public int getFaceValue() {
		return faceValue;
	}

	public String toString() {
		return String.valueOf(faceValue);
	}

}
