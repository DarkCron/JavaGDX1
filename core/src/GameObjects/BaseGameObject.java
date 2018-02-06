package GameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Utils.GameTimeStamp;

public class BaseGameObject {
	
	private int ID = -1;
	private String objectName = "object";
	
	
	public BaseGameObject() {
		
	}
	
	public BaseGameObject(int ID, String objectName) {
		this.ID = ID;
		this.objectName = objectName;
	}
	
	public void Update(GameTimeStamp gts) {
		
	}
	
	
	/**
	 * @param batch
	 * The spritebatch that draws the current object to the screen
	 */
	public void Draw(SpriteBatch batch) {
		
	}
}
