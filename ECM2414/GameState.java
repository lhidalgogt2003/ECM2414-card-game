package ECM2414;

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
