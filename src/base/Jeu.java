package base;

import java.io.IOException;

import afficheur.Afficheur;
import afficheur.GIA;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import physique.MoteurPhysique;
import physique.Player;
import physique.World;

public class Jeu implements GIA {

	protected static boolean fini;
	protected MoteurPhysique mp;
	protected Afficheur a;
	public static Player player;
	public World world;
	
	public Jeu(Stage stage) throws IOException {
		fini = false;
		mp = new MoteurPhysique();
		a = new Afficheur();
		this.player = new Player(0);
		
		
		//Pour avoir la fenetre
		AnchorPane root;
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Principale.class.getResource("/view/frame.fxml"));
        root = (AnchorPane) loader.load();
        
        Scene scene = new Scene(root,(2*CX+1)*SIZE,(2*CY+1)*SIZE,Color.DARKRED);
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	public void update() {
		// TODO Auto-generated method stub
		mp.update();
	}

	public void render() {
		// TODO Auto-generated method stub
		a.render();
	}


	public Player getPlayer() {
		// TODO Auto-generated method stub
		return this.player;
	}

}
