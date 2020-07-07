package Model;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


import Field.Floor;
import Field.Containables.Actors.Character;
import Field.Containables.Actors.Hero;
import Field.Containables.Actors.Actions.Direction;
import Field.Containables.Actors.Actions.Move;
import View.ViewGame;
import View.View;

public class Game {
	
	public ArrayList<Floor> floors;
	private int nbFloor;
	public Hero hero;
	public boolean readyToDisplay;
	
	public View view;
	
	public Game() {
		this.readyToDisplay = false;
		this.view = new View(this);
		this.hero = new Hero("Kevin", 1, null);
		this.floors = new ArrayList<Floor>(0);
		this.nbFloor = 0;
		this.floors.add(new Floor(this, nbFloor));
		this.readyToDisplay = true;
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		//System.out.println(game.floors.get(0));
		
	}
	
	private static void wait(int sec) {
		try {
			TimeUnit.SECONDS.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Floor getCurrentFloor() {
		return this.floors.get(nbFloor);
	}
	
	public ViewGame getViewG() {
		return this.view.vGame;
	}
	
	public void setView(View view) {
		this.view = view;
	}

}
