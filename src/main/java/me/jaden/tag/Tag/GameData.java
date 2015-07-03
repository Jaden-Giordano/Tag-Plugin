package me.jaden.tag.Tag;


public class GameData {

	private boolean it;

	public GameData() {
		this.it = false;
	}

	public boolean isIt() {
		return this.it;
	}

	public void tagged() {
		this.it = true;
	}

	public void setIt(boolean it) {
		this.it = it;
	}

}
