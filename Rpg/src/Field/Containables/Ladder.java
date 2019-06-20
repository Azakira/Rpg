package Field.Containables;

import Field.Containables.Actors.OnGround;

public class Ladder extends OnGround {
	
	private boolean goesDown;
	
	public Ladder(Ground g, boolean goesDown) {
		super(g);
		this.goesDown = goesDown;
	}
	
	public String toString() {
		if(goesDown)
			return " Hv";
	return " H^";
	}

}
