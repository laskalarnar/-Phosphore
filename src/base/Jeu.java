package base;

import java.io.IOException;

import afficheur.Afficheur;
import afficheur.GIA;
import afficheur.SceneGame;
import controle.Controle;
import controle.ControlerKeyBoard;
import controle.ControlerKeyBoard;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import physique.character.Player;
import physique.engine.MoteurPhysique;
import physique.world.World;

public class Jeu implements GIA {

	protected static boolean fini;
	protected MoteurPhysique mp;
	protected Afficheur a;
	public static Player player;
	public static World world;
	
	public Jeu(Stage stage) throws IOException {
		fini = false;
		this.world = new World(); 
		this.player = new Player(0);
		world.getCurrentMapFromWorld().addPlayer(player);
		Controle controle = new Controle();
        ControlerKeyBoard ckb = new ControlerKeyBoard(controle);
		mp = new MoteurPhysique(this.world, controle);
		a = new Afficheur(this.world);
		
		
		
		//Pour avoir la fenetre
		AnchorPane root;
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Principale.class.getResource("/view/frame.fxml"));
        root = (AnchorPane) loader.load();
        
        
        SceneGame scene = new SceneGame(root,(2*CX+1)*SIZE,(2*CY+1)*SIZE,Color.DARKRED,ckb);
        root.getChildren().add(a);
        
        
        
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	public void update() {
		// TODO Auto-generated method stub
		this.mp.update();
	} 

	public void rendering() throws InterruptedException {
		// TODO Auto-generated method stub
		this.a.render();
	}


	public Player getPlayer() {
		// TODO Auto-generated method stub
		return this.player;
	}

}
