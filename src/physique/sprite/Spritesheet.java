package physique.sprite;

import java.net.URL;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Spritesheet {

	private String name;
	private Image image;
	
	public Spritesheet(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void loadImage() {
		image = new Image("file:ressources/spritesheets/"+name+".png");
	}
}
