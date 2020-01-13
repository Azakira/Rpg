package Field.Containables.Actors.Actions;

import Field.Containables.Wall;
import Field.Containables.Actors.Character;

public class Mine extends Action{

	public Mine(Character chtr) {
		super(chtr);
	}
	
	public void mine() {
		Wall w = this.chtr.getGround().getFloor().getNextWall(chtr.getGround(), chtr.getFacing());
		if (w.isNull()) {
			System.out.println("wall null");
			return;
		}
		System.out.println(w.getCoordX()+", "+w.getCoordY());
		this.chtr.getGround().getFloor().mineWall(w);
	}

}
