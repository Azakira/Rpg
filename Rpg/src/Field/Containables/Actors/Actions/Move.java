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
	
	public Move(Character chtr, Direction dir) {
		super(chtr, dir);
	}
	
	public void apply() {
		this.chtr.setGround(this.chtr.getGround().getFloor().getNextGround(this.chtr.getGround(),dir));
		
	}
}
