package Field;

import java.awt.Dimension;
import java.util.ArrayList;

import Field.Containables.Ladder;
import Field.Containables.Mark;
import Field.Containables.Containable;
import Field.Containables.Ground;
import Field.Containables.Wall;
import Field.Containables.Actors.Hero;
import Field.Containables.Actors.Mobs.Mob;
import Field.Containables.Actors.Mobs.Rat;
import Field.Containables.Actors.Mobs.Slime;
import Field.Containables.Actors.Mobs.Unknown;
import Model.Game;

public class Floor {

	public static final int WIDTH = 30; // Grid's width
	public static final int HEIGHT = 30; // Grid's height

	public static final int lengthRoomMin = 2;
	public static final int lengthRoomMax = 6;

	public static final int probRoom = 20; // Probability for creating a room per block (= 1/probRoom)

	private Game game;
	private Ground posHero;
	private int nbFloor;
	private Containable[][] grid;
	private ArrayList<ArrayList<Dimension>> rooms; // Contains a list of rooms that each contains a list of the
													// coordinates of the rooms' elements
	private ArrayList<Character> turn;
	private Ground upLadderGround;
	private Ground downLadderGround;

	public Floor(Game game, int nbFloor) {
		this.game = game;
		this.nbFloor = nbFloor;
		this.grid = new Containable[HEIGHT][WIDTH];
		this.rooms = new ArrayList<ArrayList<Dimension>>(0);
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				grid[i][j] = new Wall(i, j); // Init. grid with walls
			}
		}
		createRooms();
		nameRooms();
		createLadders();
		addHero();
		generateMobs();

	}

	/**
	 * Creates a room at grid[y][x] that has w of width and h of height
	 * 
	 * @param x coordinate x
	 * @param y coordinate y
	 * @param w room's width
	 * @param h room's height
	 */

	public void createRoom(int x, int y, int w, int h) {
		for (int i = x; i < x + w; i++)
			for (int j = y; j < y + h; j++)
				if (j < HEIGHT)
					if (i < WIDTH)
						this.grid[j][i] = new Ground(i, j);
	}

	public void createRooms() {
		int prevI = 0;
		int prevJ = 0;
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if ((int) (Math.random() * probRoom) == 0 && (Math.abs(prevI - i) > 3 || Math.abs(prevJ - j) > 3)) {
					int wRoom = lengthRoomMin + (int) (Math.random() * ((lengthRoomMax - lengthRoomMin) + 1));
					int hRoom = lengthRoomMin + (int) (Math.random() * ((lengthRoomMax - lengthRoomMin) + 1));
					this.createRoom(i, j, wRoom, hRoom);
					prevI = i;
					prevJ = j;
				}
			}
		}
	}

	public void createLadders() {
		int x1;
		int y1;
		int x2;
		int y2;
		int cpt = 0;

		do {
			x1 = (int) (Math.random() * WIDTH);
			y1 = (int) (Math.random() * HEIGHT);
		} while (!(this.grid[y1][x1] instanceof Ground));
		do {
			do {
				x2 = (int) (Math.random() * WIDTH);
				y2 = (int) (Math.random() * HEIGHT);
			} while (!(this.grid[y2][x2] instanceof Ground));
			cpt++;
		} while (((Ground) this.grid[y2][x2]).getNbRoom() == ((Ground) this.grid[y1][x1]).getNbRoom() && cpt < 10);
		((Ground) this.grid[y1][x1]).addOnGround(new Ladder((Ground) this.grid[y1][x1], true));
		((Ground) this.grid[y2][x2]).addOnGround(new Ladder((Ground) this.grid[y2][x2], false));
		this.downLadderGround = (Ground) this.grid[y1][x1];
		this.upLadderGround = (Ground) this.grid[y2][x2];

	}

	public void markRoom(int x1, int y1) {
		if (this.grid[y1][x1] instanceof Ground)
			grid[y1][x1] = new Mark(x1, y1);

		if (y1 + 1 < HEIGHT) {
			if (this.grid[y1 + 1][x1] instanceof Ground) {
				markRoom(x1, y1 + 1);
			}
		}
		if (x1 + 1 < WIDTH) {
			if (this.grid[y1][x1 + 1] instanceof Ground) {
				markRoom(x1 + 1, y1);
			}
		}
		if (x1 - 1 >= 0) {
			if (this.grid[y1][x1 - 1] instanceof Ground) {
				markRoom(x1 - 1, y1);
			}
		}
		if (y1 - 1 >= 0) {
			if (this.grid[y1 - 1][x1] instanceof Ground) {
				markRoom(x1, y1 - 1);
			}
		}

	}

	public void nameRooms() {
		int nbRoom = 0;
		this.rooms = new ArrayList<ArrayList<Dimension>>(0);
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (grid[j][i] instanceof Ground && ((Ground) grid[j][i]).getNbRoom() == -1) {
					markRoom(i, j);
					rooms.add(new ArrayList<Dimension>(0));
					nameRoom(nbRoom++);
				}
			}
		}

	}

	public void nameRoom(int nbRoom) {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (grid[j][i] instanceof Mark) {
					grid[j][i] = new Ground(i, j, nbRoom);
					this.rooms.get(nbRoom).add(new Dimension(i, j));
				}
			}
		}

	}

	public void addHero() {
		posHero = randNear(this.upLadderGround);
		posHero.addOnGround(this.game.hero);
	}


	public Ground randNear(Ground c) {
		int x = c.getCoordX();
		int y = c.getCoordY();
		while (true) {
			int nbCase = (int) (Math.random() * 3);
			switch (nbCase) {
			case 0:
				if (x + 1 < WIDTH && grid[y][x + 1] instanceof Ground)
					return (Ground) grid[y][x + 1];
				break;
			case 1:
				if (y + 1 < HEIGHT && grid[y + 1][x] instanceof Ground)
					return (Ground) grid[y + 1][x];
				break;
			case 2:
				if (x - 1 >= 0 && grid[y][x - 1] instanceof Ground)
					return (Ground) grid[y][x - 1];
				break;
			case 3:
				if (y - 1 >= 0 && grid[y - 1][x] instanceof Ground)
					return (Ground) grid[y - 1][x];
			break;
			default:
				System.out.println("error randNear()");
				return c;
			}
		}
	}

	public void generateMobs() {
		int x, y;
		for (int i = 0; i < 10 + nbFloor; i++) {
			Ground g;
			do {
				do {
					x = (int) (Math.random() * WIDTH);
					y = (int) (Math.random() * HEIGHT);
				} while (!(this.grid[y][x] instanceof Ground));
				g = ((Ground) this.grid[y][x]);
			} while (g.isOccupied() || sameRoom(x,y,posHero));
			((Ground) grid[y][x]).addOnGround(generateRandMob(nbFloor, (Ground)grid[y][x]));
		}
	}

	public Mob generateRandMob(int level, Ground g) {
		Mob mob;
		int nbMob = (int) (Math.random() * (nbFloor));// switch on "nbFloor" => 1 new monster per floor
		switch (nbMob) {
		case 0:
			mob = new Rat(level, g);
			break;
		case 1:
			mob = new Slime(level, g);
			break;
		default:
			mob = new Unknown(level, g); // Unknown = bug
			break;
		}
		return mob;
	}
	
	public boolean sameRoom(int x, int y, Ground g) {
		if((((Ground) grid[y][x]).getNbRoom())==g.getNbRoom())
			return true;
		return false;
	}

	public String toString() {
		String str = "";
		for (Containable[] line : grid) {
			for (Containable c : line) {
				str += c.toString();
			}
			str += "\n";
		}
		return str;
	}

}
