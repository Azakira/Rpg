package Field.Containables.Actors;

import java.util.ArrayList;

import Field.Containables.Ground;
import Field.Containables.Actors.Actions.Action;
import Field.Containables.Actors.Actions.Move;

public class Character extends OnGround{
	
	public static final int TIME_PER_TURN = 100;

	protected Stats stats;
	protected String name;
	private int untilTurn;
	private Move move;

	public Character(Ground g) {
		super(g);
		name = "???";
		stats = new Stats();
		untilTurn = TIME_PER_TURN;
		move = new Move(this);
	}

	public Character(String name, int level, Ground g) {
		super(g);
		this.name = name;
		this.stats = new Stats(level);
		untilTurn = TIME_PER_TURN;
		move = new Move(this);
	}
	
	public void turnPass() {
		untilTurn -= this.stats.getSpeed();
	}
	
	public void turn(Action action) {
		
		this.untilTurn = TIME_PER_TURN;
	}
	
	public void moveUp() {
		this.move.up();
	}
	public void moveDown() {
		this.move.down();
	}
	public void moveLeft() {
		this.move.left();
	}
	public void moveRight() {
		this.move.right();
	}
	
	@Override
	public String toString() {
		return "[c]";
	}

	/*
	public void attack(Character c) {
		int damages = this.getStats().getAttack() - c.getStats().getArmor();
		c.stats.health -= damages; // Since armor < attack no matter what, basic attack system
		System.out
				.println(this.name + " inflicts " + damages + "! " + c.name + "'s health is now at " + c.stats.health);
	}

	public static void fight(Character a, Character b) {
		System.out.println(a.getName() + " vs " + b.getName());
		do {
			a.attack(b);
			b.attack(a);
		} while (a.getStats().getHealth() > 0 && b.getStats().getHealth() > 0); // while a.health & b.health > 0

	}
	*/

	// Getters & Setters

	public Stats getStats() {
		return stats;
	}

	protected void setStats(Stats stats) {
		this.stats = stats;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	public void resetUntilTurn() {
		this.untilTurn = TIME_PER_TURN;
	}
	
	public Ground getGround() {
		return this.ground;
	}

	private class Stats {

		private int level;
		private int exp;
		private int healthMax;
		private int health;
		private int speed;
		private int attack;
		private int armor;

		public Stats() {
			setLevel(1);
			setExp(0);
			healthMax = 50;
			setHealth(healthMax);
			speed = 10;
			setAttack(10);
			setArmor(5);
		}

		// Most used constructor: set stats depending on the level selected.
		public Stats(int lvl) {
			setLevel(lvl);
			setExp(0);
			healthMax = 50 + 10 * lvl;
			setHealth(healthMax);
			speed = 10+2*lvl;
			setAttack(10 + 2 * lvl);
			setArmor(5 + 1 * lvl);
		}

		// Getters & Setters

		public int getLevel() {
			return level;
		}

		private void setLevel(int level) {
			this.level = level;
		}

		public int getExp() {
			return exp;
		}

		private void setExp(int exp) {
			this.exp = exp;
		}

		public int getHealth() {
			return health;
		}

		private void setHealth(int health) {
			this.health = health;
		}
		
		public int getSpeed() {
			return speed;
		}

		public int getAttack() {
			return attack;
		}

		private void setAttack(int attack) {
			this.attack = attack;
		}

		public int getArmor() {
			return armor;
		}

		private void setArmor(int armor) {
			this.armor = armor;
		}

	}

}
