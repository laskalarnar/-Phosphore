package controle;

public class Controle {
	//The Controle class will be a platform between the KeyBoard and the world.
	//Be right
	//Yeah
	//I know it's difficult but let's be happy guys.

	
	
	public boolean right;
	public boolean left;
	public boolean up;
	public boolean down;
	public boolean interact;
	public boolean attack;

	public void cleanControle() {
		this.right = false;
		this.left = false;
		this.up = false;
		this.down = false;
		this.interact = false;
		this.attack = false;
	}
	public void makeRight() {
		this.cleanControle();
		System.out.println("On appelle à tourner à droite");
		this.right = true;
	}
	public void makeLeft() {
		this.cleanControle();
		this.left = true;
	}
	public void makeUp() {
		this.cleanControle();
		this.up = true;
	}

	public void makeDown() {
		this.cleanControle();
		this.down = true;
	}
	public void makeInteract() {
		this.cleanControle();
		this.interact = true;
	}
	public void makeAttack() {
		this.cleanControle();
		this.attack = true;
	}
}
