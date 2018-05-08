package controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import base.Jeu;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class ControlerKeyBoard {
	
	protected Controle controle;
	
	public ControlerKeyBoard(Controle controle) {
		super();
		this.controle = controle;
	}


	public void reactionClavier(KeyCode code) {
		// TODO Auto-generated method stub
		if (code == KeyCode.UP) {
			if (Jeu.player.getSens()==2) {
				controle.makeRight();
			}
			else {
				Jeu.player.setSens(2);
				controle.cleanControle();
			}
		}
		if (code == KeyCode.DOWN) {
			if (Jeu.player.getSens()==0) {
				controle.makeRight();
			}
			else {
				Jeu.player.setSens(0);
				controle.cleanControle();
			}
		}
		if (code == KeyCode.RIGHT) {
			if (Jeu.player.getSens()==3) {
				controle.makeRight();
			}
			else {
				Jeu.player.setSens(3);
				controle.cleanControle();
			}
			
		}
		if (code == KeyCode.LEFT) {
			if (Jeu.player.getSens()==1) {
				controle.makeRight();
			}
			else {
				Jeu.player.setSens(1);
				controle.cleanControle();
			}
		}
		if (code == KeyCode.E) {
			
		}
		if (code == KeyCode.Z) {
			
		}
		if (code == KeyCode.A) {
			
		}
		
	}
}
