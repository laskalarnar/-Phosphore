package physique.world;

import java.util.ArrayList;

public class Region implements mapInterface{

	public ArrayList<Map> maps;
	protected int currentMap;

	public Region() {
		this.currentMap = 0;
		this.maps = new ArrayList<Map>();
		this.maps.add(new Map(baselevel));
	}
	
	public Map getCurrentMapFromRegion() {
		return maps.get(currentMap);
	
	}
	
}
