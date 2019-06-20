package Field.Containables.Actors.Mobs;

import Field.Containables.Ground;
import Field.Containables.Actors.Character;

public abstract class Mob extends Character{
	
	private static int exp;
	
	public Mob(Ground g) {
		super(g);
		this.exp = 1;
	}
	
	public Mob(String name, int level, Ground g) {
		super(name, level, g);
		this.exp = level;
	}
}
