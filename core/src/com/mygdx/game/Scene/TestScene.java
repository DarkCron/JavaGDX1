package com.mygdx.game.Scene;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import GameObjects.GameSprite;
import Input.BaseGameInputProcessor;
import Input.InputKey;
import Input.TestSceneInput;
import Utils.GameTimeStamp;
import Utils.RenderCanvas;
import Visual.Animation.BaseAnimation;

public class TestScene extends BaseScene {

	TestSceneInput input_handler;
	GameSprite idle_char_sprite;
	RenderCanvas canvas;
	RenderCanvas main_canvas;
	Texture main_screen_test_tex;
	int speed = 3;
	boolean sprite_is_moving_right = false;
	boolean sprite_is_moving_left = false;
	boolean sprite_is_moving_up = false;
	boolean sprite_is_moving_down = false;

	public TestScene(int id, String name) {
		super(id, name);

		CreateAnimationManually();

		input_handler = new TestSceneInput(GenerateInputKeyList());
		Gdx.input.setInputProcessor(input_handler);
		canvas = new RenderCanvas(200, 250);
		main_canvas = new RenderCanvas(1000, 768);
		main_screen_test_tex = new Texture("TestScreen.png");
	}

	private InputKey[] GenerateInputKeyList() {
		InputKey[] temp = new InputKey[10];
		temp[0] = new InputKey(0, "move left", Keys.LEFT);
		temp[0].SetActionKeyDown(this.MoveSpriteLeft);
		temp[0].SetActionKeyUp(this.MoveSpriteLeftStop);
		temp[1] = new InputKey(0, "move left", Keys.RIGHT);
		temp[1].SetActionKeyDown(this.MoveSpriteRight);
		temp[1].SetActionKeyUp(this.MoveSpriteRightStop);
		temp[2] = new InputKey(0, "move left", Keys.UP);
		temp[2].SetActionKeyDown(this.MoveSpriteUp);
		temp[2].SetActionKeyUp(this.MoveSpriteUpStop);
		temp[3] = new InputKey(0, "move left", Keys.DOWN);
		temp[3].SetActionKeyDown(this.MoveSpriteDown);
		temp[3].SetActionKeyUp(this.MoveSpriteDownStop);

		return temp;
	}

	Runnable MoveSpriteLeft = new Runnable() {
		@Override
		public void run() {
			sprite_is_moving_left = true;
		}
	};

	Runnable MoveSpriteRight = new Runnable() {
		@Override
		public void run() {
			sprite_is_moving_right = true;
		}
	};

	Runnable MoveSpriteUp = new Runnable() {
		@Override
		public void run() {
			sprite_is_moving_up = true;
		}
	};

	Runnable MoveSpriteDown = new Runnable() {
		@Override
		public void run() {
			sprite_is_moving_down = true;
		}
	};

	Runnable MoveSpriteLeftStop = new Runnable() {
		@Override
		public void run() {
			sprite_is_moving_left = false;
		}
	};

	Runnable MoveSpriteRightStop = new Runnable() {
		@Override
		public void run() {
			sprite_is_moving_right = false;
		}
	};

	Runnable MoveSpriteUpStop = new Runnable() {
		@Override
		public void run() {
			sprite_is_moving_up = false;
		}
	};

	Runnable MoveSpriteDownStop = new Runnable() {
		@Override
		public void run() {
			sprite_is_moving_down = false;
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

		idle_char_sprite = new GameSprite(0, "Test Sprite", new Rectangle(50, 50, 19 * 3, 34 * 3));
		idle_char_sprite.setStandardAnimation(new BaseAnimation(idle_image, frames));
	}

	@Override
	public void Update(GameTimeStamp gts) {
		super.Update(gts);

		idle_char_sprite.Update(gts);

		if (sprite_is_moving_down) {
			idle_char_sprite.Move(new Point(0, -speed));
		}
		if (sprite_is_moving_up) {
			idle_char_sprite.Move(new Point(0, speed));
		}
		if (sprite_is_moving_left) {
			idle_char_sprite.Move(new Point(-speed, 0));
		}
		if (sprite_is_moving_right) {
			idle_char_sprite.Move(new Point(speed, 0));
		}
	}

	@Override
	public void Draw() {
		canvas.Begin(Color.BLACK);
		canvas.End();

		main_canvas.Begin(Color.BLACK);
		main_canvas.getBatch().draw(main_screen_test_tex, 0, 0);
		idle_char_sprite.Draw(main_canvas.getBatch());
		main_canvas.End();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.enableBlending();

		Color opacity_Color = new Color(Color.WHITE);
		opacity_Color.a = 0.2f;
		batch.setColor(opacity_Color);
		main_canvas.Draw(batch, new Rectangle(0, -768f * 0.366f / 2f, 1366, 768f * 1.366f));

		batch.setColor(Color.WHITE);
		main_canvas.Draw(batch, new Point(183, 0));

		batch.end();
	}

}