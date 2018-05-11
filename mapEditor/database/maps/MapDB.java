package database.maps;

import physique.world.Map;

public class MapDB {
	
	private int ID;
	private Map map;
	
	public MapDB(int iD, Map map) {
		ID = iD;
		this.map = map;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
