package Field.Containables.Actors;

import Field.Containables.Ground;
import Model.Game;

public abstract class OnGround {

	protected Ground ground;
	
	public OnGround(Ground g) {
		this.ground = g;
	}
	
	public String toString() {
		return "OG?";
	}
	
	public void moveTo(Ground g) {
		this.ground = g;
	}
	
	public int getCoordX() {
		return ground.getCoordX();
	}

	public int getCoordY() {
		return ground.getCoordY();
	}
	
	public void setGround(Ground g) {
		this.ground = g;
	}
}
