package Field.Containables.Actors.Actions;

import Field.Containables.Actors.Character;

public class Move extends Action {

//	public Move(Character chtr) {
//		super(chtr);
//		// TODO Auto-generated constructor stub
//	}
	
	public Move(Character chtr, Direction dir) {
		super(chtr, dir);
	}
	
	public void apply() {
		switch(dir) {
		case UP:
			if(this.chtr.getCoordY()<=0)
				return;
			
			this.chtr.setGround(g);;
			//notifyFloor
			break;
		}
	}
}
