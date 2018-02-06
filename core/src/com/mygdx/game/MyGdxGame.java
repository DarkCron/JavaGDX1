package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Scene.BaseScene;
import com.mygdx.game.Scene.TestScene;

import Utils.TimeUtil;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture BerImg;
	TimeUtil timeUtil = new TimeUtil(60);
	BaseScene currentScene;
	TestScene testScene;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		BerImg = new Texture("beep.png");
		testScene = new TestScene(99, "Test Scene");
		currentScene = testScene;
	}

	@Override
	public void render () {
		timeUtil.tick();
		currentScene.Update(timeUtil.getTimeStamp());
		currentScene.Draw();
		
		//The following 2 lines clear the screen before it's drawn on the canvas
		//You can change the Color values, according to RGBA, 0.0f - 1.0f
		//Try and experiment to see what happens if you don't clear the screen ;)
		// Gdx.gl.glClearColor(1, 0, 0, 1);
		// Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//
		// batch.begin();
		// batch.draw(img, 0, 0);
		// batch.draw(BerImg, 60,60,200,70);
		// batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
