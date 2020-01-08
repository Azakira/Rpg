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
	
	public int getCoordX() {
		return ground.getCoordX();
	}

	public int getCoordY() {
		return ground.getCoordY();
	}
	
	//without verification
	public void setGround(Ground g) {
		this.ground = g;
	}
	
	public void moveTo(Ground newG) {
		if(newG.isOccupied())
			return;
		newG.setOnGround(this);
		this.ground = newG;
		this.ground.free();
			
	}
}
