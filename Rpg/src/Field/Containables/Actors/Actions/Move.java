package Field.Containables.Actors.Actions;

import Field.Floor;
import Field.Containables.Ground;
import Field.Containables.Actors.Character;
import Model.Game;

public class Move extends Action {

//	public Move(Character chtr) {
//		super(chtr);
//		// TODO Auto-generated constructor stub
//	}
	
	public Move(Character chtr) {
		super(chtr);
	}
	
	public void up() {
		this.chtr.moveTo(this.chtr.getGround().getFloor().getNextGround(this.chtr.getGround(),Direction.UP));
	}
	
	public void down() {
		this.chtr.moveTo(this.chtr.getGround().getFloor().getNextGround(this.chtr.getGround(),Direction.DOWN));
	}
	
	public void left() {
		this.chtr.moveTo(this.chtr.getGround().getFloor().getNextGround(this.chtr.getGround(),Direction.LEFT));
	}
	
	public void right() {
		this.chtr.moveTo(this.chtr.getGround().getFloor().getNextGround(this.chtr.getGround(),Direction.RIGHT));
	}
}
