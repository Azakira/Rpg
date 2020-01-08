package Field;
import Field.Containables.Containable;
import Field.Containables.Wall;

public class Case {
	private Containable content;
	
	public Case(int x, int y, Floor floor) {
		this.content = new Wall(x, y, floor);
	}
	
	public Case(int x, int y, Containable c) {
		this.content = c;
	}
	
	public String toString() {
		return content.toString();
	}
	
	public Containable getContent() {
		return this.content;
	}

}
