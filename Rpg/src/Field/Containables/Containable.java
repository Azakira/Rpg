package Field.Containables;

import Field.Floor;

public class Containable {
	private Floor floor;
	private int coordX;
	private int coordY;
	
	public Containable(int x, int y, Floor floor) {
		this.setCoordX(x);
		this.setCoordY(y);
		this.setFloor(floor);
	}
	
	public int getCoordX() {
		return this.coordX;
	}
	
	public int getCoordY() {
		return this.coordY;
	}
	
	public Floor getFloor() {
		return this.floor;
	}

	protected void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	protected void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	protected void setFloor(Floor floor) {
		this.floor = floor;
	}
	
	@Override
	public String toString() {
		return "[?]";
	}
}
