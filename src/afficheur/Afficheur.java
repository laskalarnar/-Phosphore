package afficheur;

import base.Jeu;
import javafx.scene.layout.AnchorPane;
import physique.Map;
import physique.Tile;
import physique.World;

public class Afficheur extends AnchorPane implements GIA{

	protected World world;
	
	public Afficheur() {
		
	}
	
	public void render() {
		
		Map map = world.getCurrentMapFromWorld();
				
		this.getChildren().clear();
		for (int i = Jeu.player.getXX()-CX; i < Jeu.player.getXX()+CX; i++) {
			for (int j = Jeu.player.getYY()-CY; j < Jeu.player.getYY()+CY; i++) {
				
				
				if (map.getTileArray()[i][j] != null) {

					Tile til = map.getTileArray()[i][j];
					this.getChildren().addAll(til.getActualImage());

					if (til.getInteractable() != null) {
						System.out.println("On fait un personnage là en x = " + j + "  ———  y = " + i + "  ———  "
								+ til.toString());
						this.getChildren().addAll(til.getInteractable().getActualImage());
					}
				}
			}
		}
		
		
		
	}

}
