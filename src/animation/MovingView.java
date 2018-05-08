package animation;

import physique.ImageSprite;
import physique.Individu;
import physique.ObjectGame;

public class MovingView extends Motion {

	protected int stayingTime;
	protected int still;
	protected Individu target;
	protected int distance;
	
	public MovingView(int stayingTime, int distance,Individu target) {
		this.stayingTime = stayingTime;
		this.still = stayingTime * distance;
		this.target = target;
		this.distance = distance;
	}
	
	public int doing() {
		System.out.println("(MovVie) on a modulo sensX="+target.sensX());
		if (still%stayingTime==0) {
			ImageSprite image = this.target.getActualImage();
			image.evolve(target.sensX(),target.sensY());
		}
		this.still --;
		return still;
	}

	
	
}
