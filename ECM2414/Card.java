package ECM2414;

/**
 * Card is a class that gets the face value
 * of each card and uses an
 * event listener to get the face value od each card
 * @author George Hynes, Luis Hidalgo
 * @version 1.0
 *
 */

public class Card {
	private final int faceValue;

	/**
	 * Card is an object that is assigned a
	 * @param faceValue int value of card
 	*/
	public Card(int faceValue) {
		this.faceValue = faceValue;
	}

	public int getFaceValue() {
		return faceValue;
	}

	public String toString() {
		return String.valueOf(faceValue);
	}

}
