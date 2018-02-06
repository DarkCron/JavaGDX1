package com.mygdx.game.Scene;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import Utils.*;

//You can auto-generate comments like this using Alt + Shift + J 
/**
 * @author bernd
 * provides a minimum scene to initialize, update and draw.
 *
 */
public class BaseScene {
	//A spritebatch queries and draws images on the screen.
	protected SpriteBatch batch;
	boolean bIsActive = false;
	
	/**
	 * @param id
	 * identifier for code
	 * @param name
	 * identifier for human reading
	 */
	public BaseScene(int id,String name) {
		Create();
	}
	
	public void Create() {
		batch = new SpriteBatch();
	}
	
	public void Update(GameTimeStamp gts) {
		
	}
	
	public void Draw() {
		
	}
}
