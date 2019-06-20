package Field.Containables.Actors.Mobs;

import Field.Containables.Ground;

public class Slime extends Mob {
	
	public Slime(int level, Ground g) {
		super("Slime", level, g);
	}
	
	@Override
	public String toString() {
		return "SLM";
	}
}
