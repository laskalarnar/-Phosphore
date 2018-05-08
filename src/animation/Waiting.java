package animation;

public class Waiting extends Motion{

	protected int timing; // en nombre de frame
	protected int still;
	
	public Waiting(int timing) {
		super();
		this.timing = timing;
		this.still = timing;
	}
	
	public int doing() {
		this.still --;
		return still;
	}
	
	
}
