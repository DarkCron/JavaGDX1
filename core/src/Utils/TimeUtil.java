package Utils;

import java.sql.Struct;

import com.badlogic.gdx.Gdx;

/**
 * @author bernd
 * Manages and converts timevalues.
 */
public class TimeUtil {
	private int deltaMilliseconds = 0;
	private float secondsCounter = 0f;

	private int totalSecondsPassed = 0;

	private int targetFPS = 60;
	private int frameCount = 0;
	private int currentFPS = 0;

	public TimeUtil(int targetFPS) {
		this.targetFPS = targetFPS;
	}

	public void tick() {
		float tempTick = Gdx.graphics.getDeltaTime();
		secondsCounter += tempTick;		
		deltaMilliseconds = (int) (tempTick * 1000f);
		frameCount += 1;

		if (secondsCounter >= 1f) {

			secondsCounter -= 1;
			totalSecondsPassed += 1;
			//System.out.print(totalSecondsPassed + "\n");
			currentFPS = frameCount;
			//System.out.print("FPS: " + frameCount + "\n");
			frameCount = 0;
		}
	}
	
	public GameTimeStamp getTimeStamp() {
		GameTimeStamp gts = new GameTimeStamp(deltaMilliseconds,totalSecondsPassed);
		return gts;
	}
	
}
