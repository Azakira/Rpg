package Field.Containables;

import Field.Containables.Actors.OnGround;

public class Ground extends Containable{
	
	private int nbRoom;
	private boolean occupied;
	private OnGround onGround;
	//ref to Character / loot

	public Ground(int x, int y) {
		super(x, y);
		this.nbRoom = -1;
		this.occupied = false;
	}
	
	public Ground(int x, int y, int nbRoom) {
		super(x, y);
		this.nbRoom = nbRoom;
		this.occupied = false;
	}
	/**
	 * Adds an character/loot to the ground if it isn't already occupied
	 * @param oG a character/loot
	 * @return true if oG is successfully added
	 */
	public boolean addOnGround(OnGround oG) {
		if(occupied)
			return false;
		this.onGround = oG;
		oG.setGround(this);
		this.occupied = true;
		return true;
	}
	
	@Override
	public String toString() {
		if (occupied)
			return onGround.toString();
		if (nbRoom<10)
			return "["+nbRoom+"]";
		return "["+nbRoom;
	}
	
	public int getNbRoom() {
		return this.nbRoom;
	}
	
	public boolean isOccupied(){
		return this.occupied;
	}
}
