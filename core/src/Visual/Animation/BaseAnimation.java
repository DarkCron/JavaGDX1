package Visual.Animation;

import java.awt.Point;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import Utils.GameTimeStamp;

public class BaseAnimation {
	// Allows to divide animation over different texture files
	private Texture[] textures;
	// The source of the frames on a texture/textures
	private TextureRegion[] frames;
	// Time per frame of animation, you could use just 1 time value for all frames,
	// but this allows for more control
	private int[] frameTime;
	// What is the length in time of the current frame
	private int currentFrameTime = 0;
	// How much time has passed on the current frame?
	private int frameTimer = 0;
	private boolean bIsPlaying = true;
	private int currentFrame = 0;
	
	public BaseAnimation() {

	}

	public BaseAnimation(Texture tex, Rectangle[] sourceFrames) {
		textures = new Texture[1];
		textures[0] = tex;

		frames = new TextureRegion[sourceFrames.length];
		frameTime = new int[sourceFrames.length];
		for (int i = 0; i < sourceFrames.length; i++) {
			frames[i] = new TextureRegion(tex, (int)sourceFrames[i].x, (int)sourceFrames[i].y, (int)sourceFrames[i].width,
					(int)sourceFrames[i].height);
			frameTime[i] = 130;
		}

		if (frames.length != 0) {
			currentFrameTime = frameTime[0];
		}
	}

	public void Update(GameTimeStamp gts) {
		if (bIsPlaying) {
			frameTimer += gts.getDeltaMilli();

			if (frameTimer >= currentFrameTime) {
				//This means the current frame has been on screen for the right amount
				SwitchToNextFrame();
			}
		}

	}

	private void SwitchToNextFrame() {
		frameTimer = 0;
		if (frames.length == 0) {
			bIsPlaying = false;
			return; //error prevention
		}
		
		currentFrame++;
		
		if (currentFrame >= frames.length) {
			//If the current frame is the last of a set frames, play the first one again.
			currentFrame = 0;
		}
		
		currentFrameTime = frameTime[currentFrame];
	}

	/**
	 * Draws the animation, exactly as the frames at the position loc
	 * @param batch
	 * @param loc
	 */
	public void Draw(SpriteBatch batch, Point loc) {
		if (frames.length == 0) {
			return; //error prevention
		}
		
		batch.draw(frames[currentFrame], loc.x, loc.y, 0, 0, frames[currentFrame].getRegionWidth(), frames[currentFrame].getRegionHeight(), 1f, 1f, 0f);
	}
	
	/**
	 * Draws the animation, at a position loc, with size
	 * @param batch
	 * @param loc
	 * @param size
	 */
	public void Draw(SpriteBatch batch, Point loc, Point size) {
		if (frames.length == 0) {
			return; //error prevention
		}
		
		batch.draw(frames[currentFrame], loc.x, loc.y, 0, 0, size.x, size.y, 1f, 1f, 0f);
	}
	
	/**
	 * Draws the animation, defined by rectangle loc
	 * @param batch
	 * @param loc
	 * @param size
	 */
	public void Draw(SpriteBatch batch, Rectangle loc) {
		if (frames.length == 0) {
			return; //error prevention
		}
		
		batch.draw(frames[currentFrame], loc.x, loc.y, 0, 0, loc.width, loc.height, 1f, 1f, 0f);
	}
	
	/**
	 * Draws the animation, defined by rectangle loc
	 * @param batch
	 * @param loc
	 * @param size
	 */
	public void Draw(SpriteBatch batch, Rectangle loc, Vector2 scale) {
		if (frames.length == 0) {
			return; //error prevention
		}
		
		batch.draw(frames[currentFrame], loc.x, loc.y, 0, 0, loc.width, loc.height, scale.x, scale.y, 0f);
	}
}

