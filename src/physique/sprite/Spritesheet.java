package physique.sprite;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.util.Pair;

public class Spritesheet {

	private String name;
	private Image image;
	private HashMap<Pair<Integer, Integer>, Image> TileSet = new HashMap<>();
	
	public Spritesheet(String name) {
		this.name = name;
		loadImage();
		parse();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Image getImage() {
		return image;
	}
	
	public HashMap<Pair<Integer, Integer>, Image> getTileSet(){
		return TileSet;
	}
	
	private void loadImage() {
		image = new Image("file:ressources/spritesheets/"+name+".png");
	}
	
	private void parse(){
		int width = (int) Math.floorDiv((int) image.getWidth(), 17);
		int height = (int) Math.floorDiv((int) image.getHeight(), 17);
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Image tile = new WritableImage(image.getPixelReader(), 1+x*17, 1+y*17, 16, 16);
				TileSet.put(new Pair<>(x, y), tile);
			}
		}
	}
	
	public Image getTile(Pair<Integer, Integer> coordinates) {
		return TileSet.get(coordinates);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spritesheet other = (Spritesheet) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
