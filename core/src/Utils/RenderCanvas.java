package Utils;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Rectangle;

public class RenderCanvas {

	private int width = 50;
	private int height = 50;
	private SpriteBatch canvas_batch;
	private FrameBuffer canvas; // The canvas we will draw on
	private OrthographicCamera canvas_camera;
	private Sprite canvas_sprite; // This sprite will hold the info of the offscreen render

	public RenderCanvas() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates an (offscreen) render canvas, along with a custom spritebatch for the
	 * instanced canvas.
	 * 
	 * @param _width
	 *            Supposed canvas width
	 * @param _height
	 *            Supposed canvas height
	 */
	public RenderCanvas(int _width, int _height) {
		width = _width;
		height = _height;

		canvas = new FrameBuffer(Format.RGBA8888, _width, _height, false);
		canvas_camera = new OrthographicCamera(width, height);
		canvas_camera.translate(canvas_camera.viewportWidth / 2, canvas_camera.viewportHeight / 2);
		// A camera is initialized centered around origin (0,0) therefore the
		// translation
		canvas_camera.update();
		// Update the camera because we move it
		canvas_batch = new SpriteBatch();
		// A separate batch to draw on the canvas with
		canvas_batch.setProjectionMatrix(canvas_camera.combined);
		// Set our offscreen render on the same perspective as our batch

		canvas_sprite = null;
	}

	public void Resize(Point size) {
		if (size.x != width && size.y != height) {
			canvas.dispose(); // This dumps the current canvas properly

			width = size.x;
			height = size.y;

			canvas = new FrameBuffer(Format.RGBA8888, width, height, false);
			canvas_camera = new OrthographicCamera(width, height);
			canvas_camera.translate(canvas_camera.viewportWidth / 2, canvas_camera.viewportHeight / 2);
			// A camera is initialized centered around origin (0,0) therefore the
			// translation
			canvas_camera.update();
			// Update the camera because we move it
			// NOTE: no need to create a new canvas_batch, just update the older one with:
			canvas_batch.setProjectionMatrix(canvas_camera.combined);
			// Set our offscreen render on the same perspective as our batch

			canvas_sprite = null;
		}
	}

	public void Begin(Color _clearColor) {
		canvas.begin();
		Gdx.gl.glClearColor(_clearColor.r, _clearColor.g, _clearColor.b, _clearColor.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		canvas_batch.begin();
	}

	public void End() {
		canvas_batch.end();
		canvas.end();

		canvas_sprite = new Sprite(canvas.getColorBufferTexture());
		canvas_sprite.flip(false, true); // Because of how framebuffers work textures from the buffer should be flipped

	}

	public SpriteBatch getBatch() {
		return canvas_batch;
	}

	/**
	 * In some cases this is preferred since it's faster.
	 */
	public void EndWithoutUpdatingSprite() {
		canvas_batch.end();
		canvas.end();
	}

	/**
	 * @param batch
	 */
	public void Draw(SpriteBatch batch, Rectangle loc) {
		batch.draw(canvas_sprite, loc.x, loc.y, loc.width, loc.height);
	}

	public void Draw(SpriteBatch batch, Point loc) {
		batch.draw(canvas_sprite, loc.x, loc.y);
	}
}
