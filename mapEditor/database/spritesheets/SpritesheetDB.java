package database.spritesheets;

import physique.sprite.Spritesheet;

public class SpritesheetDB {
	
	private int ID_Spritesheet;
	private Spritesheet spritesheet;
	
	public SpritesheetDB(int iD_Spritesheet, Spritesheet spritesheet) {
		super();
		ID_Spritesheet = iD_Spritesheet;
		this.spritesheet = spritesheet;
	}

	public int getID_Spritesheet() {
		return ID_Spritesheet;
	}

	public void setID_Spritesheet(int iD_Spritesheet) {
		ID_Spritesheet = iD_Spritesheet;
	}

	public Spritesheet getSpritesheet() {
		return spritesheet;
	}

	public void setSpritesheet(Spritesheet spritesheet) {
		this.spritesheet = spritesheet;
	}

}
