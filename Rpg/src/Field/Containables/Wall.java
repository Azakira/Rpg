package Field.Containables;

import Field.Floor;

public class Wall extends Containable{

	public Wall(int x, int y, Floor floor) {
		super(x, y, floor);
	}
	
	public boolean isNull() {
		if (this.getCoordX()==-1 && this.getCoordY()==-1)
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "███";
	}

}
