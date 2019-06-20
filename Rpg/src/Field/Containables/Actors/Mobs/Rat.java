package Field.Containables.Actors.Mobs;

import Field.Containables.Ground;

public class Rat extends Mob {
	
	public Rat(int level, Ground g) {
		super("Rat", level, g);
	}
	
	@Override
	public String toString() {
		return "RAT";
	}
}
