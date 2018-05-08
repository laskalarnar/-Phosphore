package animation;

import java.util.ArrayList;

import physique.Individu;

public class Sequence {

	protected int actual;
	protected ArrayList<Motion> animation;
	protected boolean stop;
	
	public Sequence() {
		super();
		this.actual = 0;
		this.animation = new ArrayList<Motion>();
		this.stop = false;
	}
	
	public void makeMovement(Individu ind) {
		this.animation.add(new MovingView(1,8,ind));
		this.animation.add(new ChangeState(1,ind));
		this.animation.add(new MovingView(1,8,ind));
		this.animation.add(new ChangeState(2,ind));
		this.animation.add(new MovingView(1,8,ind));
		this.animation.add(new ChangeState(0,ind));
		this.animation.add(new MovingView(1,8,ind));
		this.animation.add(new ChangeState(1,ind));
		this.animation.add(new MovingView(1,8,ind));
		this.animation.add(new ChangeState(2,ind));
		this.animation.add(new MovingView(1,8,ind));
		this.animation.add(new Move(ind));
	}
	
	public void execute() {
		System.out.println(this.stop+"MISE EN EXECUTION D'un ELEMENT de SEQUENCE number "+actual);
		if (!(this.stop)) {
			int i = animation.get(actual).doing();
			if (i==0) {
				actual++;
				}
			if (actual==animation.size()){
				this.stop = true;
				}
		}		
	}

	public boolean isStop() {
		// TODO Auto-generated method stub
		return this.stop;
	}
}
