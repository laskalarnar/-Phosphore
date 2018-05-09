package physique.character;

import physique.engine.Interactable;
import physique.sprite.ImageSprite;

public class Individu extends Interactable{
	
	protected int etat ;
	protected int sens ;
	protected ImageSprite view[][];
	protected int XX;
	protected int YY;
	

	public Individu(int etat, String name) {
		super();
		this.etat = etat;
		this.sens = 0;
		this.view = new ImageSprite[4][2];
		loadImage(name);
	}
	
	public ImageSprite getActualImage() {
		return this.view[this.sens][this.etat];
	}



	public ImageSprite[][] loadImage(String name) {
		
		System.out.println("On veut chercher le nom de "+name);
		view[0][0] = appealImage("SI/"+name+"0_0.png");
		view[1][0] = appealImage("SI/"+name+"1_0.png");
		view[2][0] = appealImage("SI/"+name+"2_0.png");
		view[3][0] = appealImage("SI/"+name+"3_0.png");
		
		view[0][1] = appealImage("SI/"+name+"0_0.png");
		view[1][1] = appealImage("SI/"+name+"1_0.png");
		view[2][1] = appealImage("SI/"+name+"2_0.png");
		view[3][1] = appealImage("SI/"+name+"3_0.png");
		
		return view;
		
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	public int getSens() {
		return sens;
	}

	public void setSens(int sens) {
		this.sens = sens;
	}

	public ImageSprite[][] getView() {
		return view;
	}

	public void setView(ImageSprite[][] view) {
		this.view = view;
	}

	public int getXX() {
		return XX;
	}

	public void setXX(int xX) {
		XX = xX;
	}

	public int getYY() {
		return YY;
	}

	public void setYY(int yY) {
		YY = yY;
	}
	
	
}
