package physique.tile;

import java.net.URL;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import physique.engine.Interactable;
import physique.engine.ObjetJeu;
import physique.sprite.ImageSprite;

public class Tile extends ObjetJeu{
	//private static final long serialVersionUID = -652661369913332480L;
	protected Interactable interactable;
	protected boolean walkable;
	
	
	
	public Tile(ImageView floor, Interactable interactable, boolean walkable) {
		super();
		this.interactable = interactable;
		this.walkable = walkable;
	}
	
	public Tile(char chary) {
		super();
		this.interactable = null;
		this.walkable = true;
		this.img = appealImage(chary);
	}
	
	public ImageSprite appealImage(String name) {
		URL loc;
		loc = this.getClass().getResource("ST/"+name+".png");
		return new ImageSprite(resample(new Image(loc.toExternalForm()),MULTIPLICATOR));
	}
	
	public ImageSprite appealImage(char chary) {
		switch (chary) {
		case 'W':
			return appealImage("wall");
		case 'L':
			return appealImage("leaves");
		case 'X':
			return appealImage("leaves");
		default:
			return appealImage("error");
		}
	}
	
	public Interactable getInteractable() {
		return interactable;
	}
	public void setInteractable(Interactable interactable) {
		this.interactable = interactable;
	}
	public boolean isWalkable() {
		return walkable;
	}
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
	
}
