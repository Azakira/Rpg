package Model;
import java.util.ArrayList;
import Field.Floor;
import Field.Containables.Actors.Character;
import Field.Containables.Actors.Hero;
import Field.Containables.Actors.Actions.Direction;
import Field.Containables.Actors.Actions.Move;

public class Game {
	
	private ArrayList<Floor> floors;
	private int currentFloor;
	public Hero hero;
	
	public Game() {
		this.hero = new Hero("Kevin", 1, null);
		this.floors = new ArrayList<Floor>(0);
		this.currentFloor = 0;
		this.floors.add(new Floor(this, currentFloor));
		
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.hero.moveDown();
		game.hero.moveDown();
		game.hero.moveDown();
		game.hero.moveDown();
		game.hero.moveDown();
		game.hero.mine();
		game.hero.moveDown();
		game.hero.mine();
		game.hero.moveDown();
		game.hero.mine();
		game.hero.moveDown();
		System.out.println(game.floors.get(0));
		
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}

}
