package physique.sprite;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.util.Pair;
import physique.tile.Tile;

public class Spritesheet {

	private String name;
	private Image image;
	private HashMap<Pair<Integer, Integer>, Image> TileSet = new HashMap();
	
	public Spritesheet(String name) {
		this.name = name;
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
	
	public void loadImage() {
		image = new Image("file:ressources/spritesheets/"+name+".png");
	}
	
	public void parseSpritesheet(){
		int width = (int) Math.floorDiv((int) image.getWidth(), 17);
		int height = (int) Math.floorDiv((int) image.getHeight(), 17);
		

		Stage test = new Stage();
		TilePane a = new TilePane();
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Image tile = new WritableImage(image.getPixelReader(), 1+x*17, 1+y*17, 16, 16);
				TileSet.put(new Pair<>(x, y), tile);
				a.getChildren().add(new ImageView(tile));
			}
		}
		
		Scene sc = new Scene(a);
		test.setScene(sc);
		test.show();
	}
}
