package Field.Containables.Actors.Actions;

import Field.Containables.Actors.Character;

public abstract class Action {

	protected Character chtr;
	protected Direction dir;

	public Action(Character chtr) {
		this.chtr = chtr;
	}
	
	public Action(Character chtr, Direction dir) {
		this.chtr = chtr;
		this.dir = dir;
	}

	public abstract void apply();// Applies the action to the character
}
