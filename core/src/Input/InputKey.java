package Input;

import com.badlogic.gdx.Input.Keys;

public class InputKey {

	//We initialize all variables here since we know that every variable is going to be used.
	protected int defaultGameKey = Keys.UNKNOWN;
	protected int gameKey = Keys.UNKNOWN; // the pressed key id, not to be confused with
	protected int ID = -1; // action ID
	protected String name = ""; // action name
	
	protected Runnable action_key_down;
	protected Runnable action_key_up;
	
	public InputKey(int _ID, String _name, int _gameKey) {
		defaultGameKey = _gameKey;
		gameKey = _gameKey; //Key assignment via these lines of code only happens through the code, a keyboard key is thus default.
		ID = _ID;
		name = _name;
	}
	
	public int getKeyCode() {
		return gameKey;
	}
	
	public void SetActionKeyDown(Runnable _action) {
		action_key_down = _action;
	}
	
	public void SetActionKeyUp(Runnable _action) {
		action_key_up = _action;
	}
	
	public void ExecuteKeyDown() {
		action_key_down.run();
	}
	
	public void ExecuteKeyUp() {
		action_key_up.run();
	}
}
