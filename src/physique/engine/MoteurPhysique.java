package physique.engine;

import java.util.ArrayList;

import animation.Sequence;
import base.Jeu;
import controle.Controle;
import physique.world.World;

public class MoteurPhysique {

	protected World world;
	protected Sequence seqControle;
	protected Controle controle;
	
	public MoteurPhysique(World world, Controle controle) {
		this.world = world;
		this.controle = controle;
		this.seqControle = null;
	}
	
	
	public void update() {
		System.out.println("Depuis le MP on UPDATE");
		if (seqControle == null) {
			if (controle.right==true) {
				System.out.println("On cr�� une s�quence");
				seqControle = new Sequence();
				seqControle.makeMovement(Jeu.player);
				controle.cleanControle();
			}
		}
		if (seqControle != null) {
			seqControle.execute();
		}
		if (seqControle != null && seqControle.isStop()) {
			seqControle = null;
		}
	}
	
}

