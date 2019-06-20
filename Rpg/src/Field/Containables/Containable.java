package Field.Containables;

public class Containable {
	private int coordX;
	private int coordY;
	
	public Containable(int x, int y) {
		this.setCoordX(x);
		this.setCoordY(y);
	}
	
	public int getCoordX() {
		return this.coordX;
	}
	
	public int getCoordY() {
		return this.coordY;
	}

	protected void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	protected void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	@Override
	public String toString() {
		return "[?]";
	}
}
