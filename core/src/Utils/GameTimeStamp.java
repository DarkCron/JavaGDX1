package Utils;

public class GameTimeStamp{
	private int deltaMilliseconds = 0;
	private int totalSecondsPassed = 0;	
	
	public GameTimeStamp(int deltaMilli, int totsec) {
		deltaMilliseconds = deltaMilli;
		totalSecondsPassed = totsec;
	}
	
	public int getDeltaMilli() {
		return deltaMilliseconds;
	}
	
	public int getTotalSeconds() {
		return totalSecondsPassed;
	}
}