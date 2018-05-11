package afficheur;

import java.util.ArrayList;

import base.Jeu;
import javafx.scene.layout.AnchorPane;
import physique.sprite.ImageSprite;
import physique.tile.SimpleTile;
import physique.world.Map;
import physique.world.World;

public class Afficheur extends AnchorPane implements GIA{

	protected World world;
	
	public Afficheur(World world) {
		this.world = world;
	}
	
	public void render() throws InterruptedException {
		
		Map map = world.getCurrentMapFromWorld();
		//System.out.println("Bon sang mais affichez moi cette map Johnson !\n"+map.viewToString());		
		//Thread.sleep(1000);
		this.getChildren().clear();
		ImageSprite im = Jeu.player.getActualImage();
		int CenXX = Jeu.player.getXX();
		int CenYY = Jeu.player.getYY();
		System.out.println("Alors on est centr� sur la position XX="+CenXX+" YY="+CenYY);
		//int CenX = (int) Jeu.player.getActualImage().getLayoutX();
		//int CenY = (int) Jeu.player.getActualImage().getLayoutY()+SIZE/2;
		
		for (int j = CenYY-CY-1; j <= CenYY+CY+1; j++) {
			//ArrayList<Interactable> interactables = new ArrayList<Interactable>();
			 for (int i = CenXX-CX-1; i <= CenXX+CX+1; i++) {
				//System.out.println("Initialisation au stade x:"+i+" y:"+j);
				
				if (0<=i && i<map.getMapXX() && 0<=j && j<map.getMapYY() ) {
					

					SimpleTile til = map.getTileArray()[i][j];
					til.getActualImage().relocate(SIZE*(i+CX-CenXX)-im.getShiftX(),SIZE*(j+CY-CenYY)-im.getShiftY());
					this.getChildren().addAll(til.getActualImage());
					/*
					if (til.getInteractable() != null) {
						System.out.println("On fait un personnage l� en x = " + j + "  ���  y = " + i + "  ���  "
								+ til.toString());
						this.getChildren().addAll(til.getInteractable().getActualImage());
						interactables.add(til.getInteractable());
						
						System.out.println(til.getInteractable().getActualImage().getLayoutY()+"On affiche x et y de image x="+til.getInteractable().getActualImage().getLayoutX());
						ImageSprite img =til.getInteractable().getActualImage();
						img.relocate(SIZE*(i+CX-CenXX)+img.getShiftX()-im.getShiftX(),SIZE*(j+CY-CenYY)+img.getShiftY()-im.getShiftY());
						this.getChildren().addAll(til.getInteractable().getActualImage());*/
					}
				}
				
				
				
			 /*
			 for (int k = 0; k<interactables.size();k++) {
					ImageSprite img =interactables.get(k).getActualImage();
					img.relocate(i+CX-CenXX)+img.getShiftX()-im.getShiftX(),SIZE*(j+CY-CenYY)+img.getShiftY()-im.getShiftY());
					this.getChildren().addAll(interactables.get(k).getActualImage());*/

			 for (int i = CenXX-CX; i <= CenXX+CX; i++) {
				 if (0<=i && i<map.getMapXX() && 0<=j && j<map.getMapYY() ) {
				 SimpleTile til = map.getTileArray()[i][j];
					if (til.getInteractable() != null) {
						
						ImageSprite img =til.getInteractable().getActualImage();
						img.relocate(SIZE*(i+CX-CenXX)+img.getShiftX()-im.getShiftX(),SIZE*(j+CY-CenYY)+img.getShiftY()-im.getShiftY());
						this.getChildren().addAll(til.getInteractable().getActualImage());

			 }
		}
			 }

		
		
	}
	}
}
