package Field.Containables;

import Field.Floor;

public class Wall extends Containable{

	public Wall(int x, int y, Floor floor) {
		super(x, y, floor);
	}
	
	@Override
	public String toString() {
		return "███";
	}

}
