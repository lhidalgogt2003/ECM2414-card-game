package ECM2414;

/**
 * Card is a class that gets the face value
 * of each card and uses an
 * event listener to get the face value od each card
 * @authors George Hynes, Luis Hidalgo
 * @version 1.0
 * 
 */
 
public class Card {
	private int faceValue;

	/**
	 * Card is an event listener that uses a super class to
	 * @return the face value of the card
	 * @param int faceValue
 	*/
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
