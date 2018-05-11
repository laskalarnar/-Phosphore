package database.simpleTiles;

import physique.tile.SimpleTile;

public class SimpleTileDB {

	private int ID;
	private SimpleTile simpleTile;
	
	public SimpleTileDB(int iD, SimpleTile simpleTile) {
		ID = iD;
		this.simpleTile = simpleTile;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public SimpleTile getSimpleTile() {
		return simpleTile;
	}

	public void setSimpleTile(SimpleTile simpleTile) {
		this.simpleTile = simpleTile;
	}
	
	
}
