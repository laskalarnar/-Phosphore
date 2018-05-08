package physique;

import afficheur.GIA;

public class Map implements GIA {

	private int mapXX;
	private int mapYY;
	private String mapName;
	private Tile tileArray[][];
	
	public Map(int mapWidth, int mapHeight, String mapName) {
		this.mapXX = mapWidth;
		this.mapYY = mapHeight;
		this.mapName = mapName;
		this.tileArray=new Tile[mapWidth][mapHeight];

		// setMinWidth(scale * mapWidth * Tile.TILE_SIZE);
		// setMinHeight(scale * mapHeight * Tile.TILE_SIZE);
	}
	
	public Map(String code) {
		int xcounter = 0;
		int xcountermax = 0;
		int ycounter = 0;
		int xPlayer = 1;
		int yPlayer = 1;

		for (int i = 0; i < code.length(); i++) {
			char item = code.charAt(i);
			if (item == '\n') {
				ycounter += 1;
				xcountermax = Math.max(xcounter, xcountermax);
				xcounter = 0;
				continue;
			}
			if (item == 'X') {
				xPlayer = xcounter;
				yPlayer = ycounter;
			} else {
				xcounter++;
				continue;
			}

		}

		
		this.mapXX = xcountermax;
		this.mapYY = ycounter;
		this.tileArray=new Tile[xcountermax][ycounter ];
		initialisationMap(code);

		// setMinWidth(scale * mapWidth * Tile.TILE_SIZE);
		// setMinHeight(scale * mapHeight * Tile.TILE_SIZE);
	}

	public Tile[][] getTileArray() {
		return tileArray;
	}

	public void setTileArray(Tile tileArray[][]) {
		this.tileArray = tileArray;
	}
	
	public int getMapXX() {
		return mapXX;
	}

	public void setMapXX(int mapXX) {
		this.mapXX = mapXX;
	}

	public int getMapYY() {
		return mapYY;
	}

	public void setMapYY(int mapYY) {
		this.mapYY = mapYY;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}

	public void initialisationMap(String code) {
		
		for (int x = 0; x < this.getMapXX(); x++) {
			for (int y = 0; y < this.getMapYY(); y++) {
				char item = code.charAt(y * (this.getMapXX() + 1) + x);
				this.tileArray[x][y] = new Tile(item);

				}
			}

		}
	
	public String viewToString() {
		String mapping = new String();
		System.out.println("On fait la vue sur la map XX="+this.getMapXX()+" et YY="+getMapYY());
		for (int i = 0; i < this.getMapXX(); i++) {
			for (int j = 0; j < this.getMapYY(); j++) {
				if (this.tileArray[i][j].getInteractable() != null) {
					mapping += "2";
					continue;
				}
				mapping += "1";
			}

			mapping += "\n";
		}

		return mapping;
	}

	public void addPlayer(Player player) {
		System.out.println("ON A LOCALISE L'IMAGE EN x="+(SIZE*(CX+1))+" et y="+(SIZE*(CY+1)-SIZE/2));
		this.tileArray[player.getXX()][player.getYY()].setInteractable(player);;
		player.getActualImage().relocate(480,264);
		// TODO Auto-generated method stub
		
	}
	
	public void move(Individu ind) {
		this.tileArray[ind.getXX()][ind.getYY()].setInteractable(null);;
		ind.mover();
		this.tileArray[ind.getXX()][ind.getYY()].setInteractable(ind);
	}
	}




