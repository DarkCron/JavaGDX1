package com.mygdx.game.Scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import Utils.GameTimeStamp;
import Visual.Animation.BaseAnimation;

public class TestScene extends BaseScene {

	BaseAnimation idle_char;
	public TestScene(int id, String name) {
		super(id, name);
		
		CreateAnimationManually();
		
	}
	private void CreateAnimationManually() {
		Texture idle_image = new Texture("idle.png");
		int frame_width = 19;
		int frame_height = 34;
		
		Rectangle[] frames = new Rectangle[10];
		for (int i = 0; i < 9; i++) {
			frames[i] = new Rectangle(i*frame_width,0,frame_width,frame_height);
		}
		frames[9] = new Rectangle(0,frame_height,frame_width,frame_height);
		
		idle_char = new BaseAnimation(idle_image,frames);
	}
	
	@Override
	public void Update(GameTimeStamp gts) {
		super.Update(gts);
		
		idle_char.Update(gts);
	}

	@Override
	public void Draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		idle_char.Draw(batch, new Rectangle(50,50,19*5,34*5));
		batch.end();
	}

}
