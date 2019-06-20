package Field.Containables.Actors;

import java.util.ArrayList;

import Field.Containables.Ground;

public class Hero extends Character {

	// create an enum Classes (knight, thief, mage, ...)
	private Item weapon;
	private ArrayList<Item> inventory;

	public Hero(Ground g) {
		super(g);
	}

	public Hero(String name, int level, Ground g) {
		super(name, level, g);
	}
	
	
	public String toString() {
		return "YOU";
	}
}
