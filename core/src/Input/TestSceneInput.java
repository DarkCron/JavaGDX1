package Input;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class TestSceneInput extends BaseGameInputProcessor {	
	
	public TestSceneInput(InputKey[] _keyList) {
		super(_keyList);
	}

	@Override
	public boolean keyDown(int keycode) {
		for (int i = 0; i < keyList.length; i++) {
			if(keyList[i] != null && keyList[i].getKeyCode() == keycode) {
				keyList[i].ExecuteKeyDown();
				break;
			}
		}
		
		return super.keyDown(keycode);
	}
	
	@Override
	public boolean keyUp(int keycode) {
		for (int i = 0; i < keyList.length; i++) {
			if(keyList[i] != null && keyList[i].getKeyCode() == keycode) {
				keyList[i].ExecuteKeyUp();
				break;
			}
		}
		
		return super.keyDown(keycode);
	}
	
}
