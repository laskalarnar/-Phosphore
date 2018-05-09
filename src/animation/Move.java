package animation;

import afficheur.GIA;
import base.Jeu;
import physique.character.Individu;

public class Move extends FlashMotion implements GIA{

	protected Individu target;
	
	
	
	public Move(Individu target) {
		super();
		this.target = target;
	}



	public int doing() {
		Jeu.world.getCurrentMapFromWorld().move(target);
		target.getActualImage().setShiftX(0);
		target.getActualImage().setShiftY(-SIZE/2);
		return 0;
	}
}
