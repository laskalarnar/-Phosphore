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
		this.view = new ImageSprite[4][3];
		loadImage(name);
	}
	
	public ImageSprite getActualImage() {
		System.out.println("On eut le sens et etat"+this.sens+" -- "+this.etat);
		return this.view[this.sens][this.etat];
	}

	

	public ImageSprite[][] loadImage(String name) {
		System.out.println("CETTE FONCTION EST APPELEE");
		System.out.println("On veut chercher le nom de "+name);
		
		for (int sens = 0 ; sens <4 ; sens++) {
			for (int etat = 0 ; etat < 3 ; etat++) {
				view[sens][etat] = appealImage("SI/"+name+sens+"_"+etat+".png");
				view[sens][etat].setShiftY(-SIZE/2);
			}
		}
		
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
		int x = this.getActualImage().getShiftX();
		int y = this.getActualImage().getShiftY();
		this.sens = sens;
		this.getActualImage().setShiftX(x);
		this.
		getActualImage().setShiftY(y);
		
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
	public int sensY() {
		return (1+this.sens)*(this.sens-1)*(this.sens-3)/3;
	}
	public int sensY(int senss) {
		return (1+senss)*(senss-1)*(senss-3)/3;
	}
	public int sensX() {
		return sensY(3-this.sens);
	}
	
	public void mover() {
		System.out.println("On move là !!! :D (Individu) x="+sensX()+" y="+sensY());
		this.XX+=sensX();
		this.YY+=sensY();
	}
	
}
