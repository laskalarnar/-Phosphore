package physique.tile;

import java.net.URL;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Pair;
import physique.engine.Interactable;
import physique.engine.ObjectGame;
import physique.sprite.ImageSprite;
import physique.sprite.Spritesheet;

public class SimpleTile extends ObjectGame{
	
	protected Interactable interactable;
	protected boolean walkable;
	
	private Spritesheet spritesheet;
	private Pair<Integer, Integer> spritesheetCoordinates;
	
	public SimpleTile(Spritesheet spritesheet, Pair<Integer, Integer> spritesheetCoordinates) {
		this.spritesheet = spritesheet;
		this.spritesheetCoordinates = spritesheetCoordinates;
	}
	
	public SimpleTile(ImageView floor, Interactable interactable, boolean walkable) {
		super();
		this.interactable = interactable;
		this.walkable = walkable;
	}
	
	public SimpleTile(char chary) {
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
	
	public Pair<Integer, Integer> getSpritesheetCoordinates(){
		return spritesheetCoordinates;
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleTile other = (SimpleTile) obj;
		if (spritesheet == null) {
			if (other.spritesheet != null)
				return false;
		} else if (!spritesheet.equals(other.spritesheet))
			return false;
		if (spritesheetCoordinates == null) {
			if (other.spritesheetCoordinates != null)
				return false;
		} else if (!spritesheetCoordinates.equals(other.spritesheetCoordinates))
			return false;
		return true;
	}

	
}
