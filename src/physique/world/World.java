package physique;

import java.util.ArrayList;



public class World {

	
	protected ArrayList<Region> regions;
	protected int currentRegion;
	
	public World() {
		this.currentRegion = 0;
		this.regions = new ArrayList<Region>();
		this.regions.add(new Region());
		}

	public Map getCurrentMapFromWorld() {
		// TODO Auto-generated method stub
		return regions.get(currentRegion).getCurrentMapFromRegion();
	}
	
	
	
}
