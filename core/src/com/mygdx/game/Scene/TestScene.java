package com.mygdx.game.Scene;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import GameObjects.GameSprite;
import Input.BaseGameInputProcessor;
import Input.InputKey;
import Input.TestSceneInput;
import Utils.GameTimeStamp;
import Visual.Animation.BaseAnimation;

public class TestScene extends BaseScene {

	TestSceneInput input_handler;
	GameSprite idle_char_sprite;

	public TestScene(int id, String name) {
		super(id, name);

		CreateAnimationManually();

		input_handler = new TestSceneInput(GenerateInputKeyList());
		Gdx.input.setInputProcessor(input_handler);
	}

	private InputKey[] GenerateInputKeyList() {
		InputKey[] temp = new InputKey[10];
		temp[0] = new InputKey(0, "move left", Keys.LEFT);
		temp[0].SetAction(this.MoveSpriteLeft);
		return temp;
	}
	
	Runnable MoveSpriteLeft = new Runnable() {
		
		@Override
		public void run() {
			idle_char_sprite.Move(new Point(-10, 0));
			
		}
	};

	private void CreateAnimationManually() {
		Texture idle_image = new Texture("idle.png");
		int frame_width = 19;
		int frame_height = 34;

		Rectangle[] frames = new Rectangle[10];
		for (int i = 0; i < 9; i++) {
			frames[i] = new Rectangle(i * frame_width, 0, frame_width, frame_height);
		}
		frames[9] = new Rectangle(0, frame_height, frame_width, frame_height);

		idle_char_sprite = new GameSprite(0, "Test Sprite", new Rectangle(50, 50, 19 * 5, 34 * 5));
		idle_char_sprite.setStandardAnimation(new BaseAnimation(idle_image, frames));
	}

	@Override
	public void Update(GameTimeStamp gts) {
		super.Update(gts);

		idle_char_sprite.Update(gts);
	}

	@Override
	public void Draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		idle_char_sprite.Draw(batch);
		batch.end();
	}

}