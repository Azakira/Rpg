package Field.Containables;

import Field.Floor;

public class Mark extends Containable{

	public Mark(int x, int y, Floor floor) {
		super(x, y, floor);
	}
	
	public String toString() {
		return "[~]";
	}
}
