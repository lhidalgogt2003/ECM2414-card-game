package ECM2414;


/**
 * GameSate is a class that checks if the game
 * has finished and which player has won the game
 * and synchronizes the scheduler so the threads
 * stops when the game is won by a player.
 * @author George Hynes, Luis Hidalgo
 * @version 1.0
 * 
 */



public class GameState {
	private boolean isOver;
	private int wonBy;
	
	public boolean isOver() {
		synchronized(this) {
			return isOver;
		}
	}
	
	public void setIsOver() {
		synchronized(this) {
			isOver = true;
		}
	}

	public void setWonBy(int id) {
		synchronized(this) {
			wonBy = id;
		}
	}

	public int getWonBy() {
		synchronized (this) {
			return wonBy;
		}
	}
}
