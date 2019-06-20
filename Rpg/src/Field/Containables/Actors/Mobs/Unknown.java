package Field.Containables.Actors.Mobs;

import Field.Containables.Ground;

public class Unknown extends Mob {

	public Unknown(int level, Ground g) {
		super("Unknown", level, g);
	}

	@Override
	public String toString() {
		return "???";
	}
}
