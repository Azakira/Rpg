package Field;

import java.awt.Dimension;
import java.util.ArrayList;

import Field.Containables.Ladder;
import Field.Containables.Mark;
import Field.Containables.Containable;
import Field.Containables.Ground;
import Field.Containables.Wall;
import Field.Containables.Actors.Character;
import Field.Containables.Actors.Hero;
import Field.Containables.Actors.Actions.Direction;
import Field.Containables.Actors.Mobs.Mob;
import Field.Containables.Actors.Mobs.Rat;
import Field.Containables.Actors.Mobs.Slime;
import Field.Containables.Actors.Mobs.Unknown;
import Model.Game;

public class Floor {

	public static final int WIDTH = 30; // Grid's width (30)
	public static final int HEIGHT = 30; // Grid's height (30)

	public static final int lengthRoomMin = 2; //2
	public static final int lengthRoomMax = 6; //6

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

	/**
	 * Constructor: initializes a floor
	 * @param game initializes the reference to game
	 * @param nbFloor the floor number
	 */
	public Floor(Game game, int nbFloor) {
		this.game = game;
		this.nbFloor = nbFloor;
		createGrid();
		this.rooms = new ArrayList<ArrayList<Dimension>>(0);
		createRooms();
		nameRooms();
		createLadders();
		addHero();
		generateMobs();
	}
	/**
	 * Initializes the grid
	 */
	private void createGrid(){
		this.grid = new Containable[HEIGHT][WIDTH];
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				grid[i][j] = new Wall(i, j, this); // Init. grid with walls
			}
		}
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
						this.grid[j][i] = new Ground(i, j, this);
	}
	/**
	 * Creates all the rooms in the grid, with a random size between lengthRoomMin and lengthRoomMax.
	 * For each case in the grid, there is a 1/probRoom chance of generating a room there.
	 */
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

	/**
	 * Generates the up and down ladders on a random free ground
	 */
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

	/**
	 * Fills the room that contains grid[y1][x1] with Marks.
	 * 
	 * Fills the Grounds around grid[y1][x1] with Mark instances and applies the same method to these Grounds.
	 * @param x1 coordinate x
	 * @param y1 coordinate y
	 */
	public void markRoom(int x1, int y1) {
		if (this.grid[y1][x1] instanceof Ground)
			grid[y1][x1] = new Mark(x1, y1, this);

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

	/**
	 * Assigns each room a number by marking them 1 by 1 and giving a number to the marked area
	 * 
	 */
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

	/**
	 * Assigns a number to each ground of the marked area
	 * @param nbRoom the number you want the room to have
	 */
	public void nameRoom(int nbRoom) {
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				if (grid[j][i] instanceof Mark) {
					grid[j][i] = new Ground(i, j, this, nbRoom);
					this.rooms.get(nbRoom).add(new Dimension(i, j));
				}
			}
		}

	}

	/**
	 * Puts the hero on a random Ground near the up ladder
	 */
	public void addHero() {
		posHero = randNear(this.upLadderGround);
		posHero.addOnGround(this.game.hero);
	}

	/**
	 * Returns a random Ground near the one given
	 * @param g a Ground
	 * @return a random Ground near g
	 */
	public Ground randNear(Ground g) {
		int x = g.getCoordX();
		int y = g.getCoordY();
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
				return g;
			}
		}
	}
	
	/**
	 * Generates all the mobs on random places in the floor (except in the "spawn room", the one where the hero came from)
	 */
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

	/**
	 * Generates a random mob (the higher the current floor level is, the higher are the chances to generate a high class mob)
	 * @param level the mob's level
	 * @param g the ground it's generated on
	 * @return
	 */
	public Mob generateRandMob(int level, Ground g) {
		Mob mob;
		int randMob = (int) (Math.random() * (nbFloor));// switch on "nbFloor" => 1 new monster class per floor
		switch (randMob) {
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
	
	/**
	 * Returns true if grid[y][x] is in the same room as g
	 * /!\ grid[y][x] MUST be a Ground
	 * @param x coordinate x
	 * @param y coordinate y
	 * @param g a Ground
	 * @return boolean
	 */
	public boolean sameRoom(int x, int y, Ground g) {
		if((((Ground) grid[y][x]).getNbRoom())==g.getNbRoom())
			return true;
		return false;
	}

	/**
	 * Displays the floor
	 */
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

	public Ground getNextGround(Ground g, Direction dir) {
		switch(dir) {
		case UP:
			if (g.getCoordY()-1 >= 0 && this.grid[g.getCoordY()-1][g.getCoordX()] instanceof Ground) {
				return (Ground) this.grid[g.getCoordY()-1][g.getCoordX()];
			}
			break;
		case DOWN:
			if (g.getCoordY()+1 < HEIGHT && this.grid[g.getCoordY()+1][g.getCoordX()] instanceof Ground) {
				return (Ground) this.grid[g.getCoordY()+1][g.getCoordX()];
			}
			break;
		case LEFT:
			if (g.getCoordX()-1 >= 0 && this.grid[g.getCoordY()][g.getCoordX()-1] instanceof Ground) {
				return (Ground) this.grid[g.getCoordY()][g.getCoordX()-1];
			}
			break;
		case RIGHT:
			if (g.getCoordX()+1 < WIDTH && this.grid[g.getCoordY()][g.getCoordX()+1] instanceof Ground) {
				return (Ground) this.grid[g.getCoordY()][g.getCoordX()+1];
			}
			break;
		}
		return g;
	}
	
	public Wall getNextWall(Ground g, Direction dir) {
		int gX = g.getCoordX();
		int gY = g.getCoordY();
		switch(dir) {
		case UP:
			if (gY-1 >= 0 && this.grid[gY-1][gX] instanceof Wall) {
				return (Wall) this.grid[gY-1][gX];
			}
			break;
		case DOWN:
			if (gY+1 < HEIGHT && this.grid[gY+1][gX] instanceof Wall) {
				return (Wall) this.grid[gY+1][gX];
			}
			break;
		case LEFT:
			if (gX-1 >= 0 && this.grid[gY][gX-1] instanceof Wall) {
				return (Wall) this.grid[gY][gX-1];
			}
			break;
		case RIGHT:
			if (gX+1 < WIDTH && this.grid[gY][gX+1] instanceof Wall) {
				return (Wall) this.grid[gY][gX+1];
			}
			break;
		}
		return new Wall(-1,-1, this);
	}
	
	public void mineWall(Wall w) {
		setGrid(new Ground(w.getCoordY(), w.getCoordX(), this)); //Swapped X and Y cuz i suck at coding: Ground's X are Grid's Y...
	}
	
	public Containable getGrid(int x, int y) {
		return this.grid[y][x];
	}
	
	public void setGrid(Containable c) {
		if (c.getCoordY()>=0 && c.getCoordY()<HEIGHT && c.getCoordX()>=0 && c.getCoordX()<WIDTH) {
			this.grid[c.getCoordY()][c.getCoordX()] = c;
		}
	}

}
