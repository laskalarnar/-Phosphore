package animation;

import physique.character.Individu;

public class ChangeState extends FlashMotion {

	protected int state;
	protected Individu target;
	
	
	
	public ChangeState(int state, Individu target) {
		super();
		this.state = state;
		this.target = target;
	}

	public int doing() {
		int x = this.target.getActualImage().getShiftX();
		int y = this.target.getActualImage().getShiftY();
		this.target.setEtat(this.state);
		this.target.getActualImage().setShiftX(x);
		this.target.getActualImage().setShiftY(y);
		return 0;
	}
}
