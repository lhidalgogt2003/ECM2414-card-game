package ECM2414;

public class GameState {
	private boolean isOver;
	
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
}
