package GameObjects;

import java.awt.Point;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import Utils.GameTimeStamp;
import Visual.Animation.BaseAnimation;

public class GameSprite extends BaseGameObject {

	protected Rectangle game_loc;
	protected BaseAnimation std_animation = null;

	public GameSprite() {
	}

	public GameSprite(int ID, String objectName) {
		super(ID, objectName);
		game_loc = new Rectangle(0, 0, 64, 64);
	}

	public GameSprite(int ID, String objectName, Rectangle _game_loc) {
		super(ID, objectName);
		game_loc = _game_loc;
	}

	public void setStandardAnimation(BaseAnimation _anim) {
		std_animation = _anim;
	}
	
	public void Move(Point _p) {
		Vector2 temp = new Vector2();
		game_loc.getPosition(temp);
		temp.add(_p.x, _p.y);
		game_loc.setPosition(temp);
	}

	@Override
	public void Update(GameTimeStamp gts) {
		if (std_animation == null) {
			return; // error prevention no default animation
		}

		std_animation.Update(gts);
	}

	@Override
	public void Draw(SpriteBatch batch) {
		if (std_animation == null) {
			return; // error prevention no default animation
		}

		std_animation.Draw(batch, game_loc);
	}
}
