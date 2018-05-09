package physique.sprite;

import java.net.URL;

import afficheur.GIA;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImageSprite extends ImageView implements GIA {
	
	protected int shiftX;
	protected int shiftY;
	
	public ImageSprite(Image image) {
		super(image);
		this.shiftX=0;
		this.shiftY=0;
	}
	
	public ImageSprite appealImage(String name) {
		URL loc;
		loc = this.getClass().getResource("pictures/"+name+".png");
		return new ImageSprite(resample(new Image(loc.toExternalForm()),MULTIPLICATOR));
	}
	
	public Image resample(Image input, int scaleFactor) {
		final int W = (int) input.getWidth();
		final int H = (int) input.getHeight();
		final int S = scaleFactor;

		WritableImage output = new WritableImage(W * S, H * S);

		PixelReader reader = input.getPixelReader();
		PixelWriter writer = output.getPixelWriter();

		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				final int argb = reader.getArgb(x, y);
				for (int dy = 0; dy < S; dy++) {
					for (int dx = 0; dx < S; dx++) {
						writer.setArgb(x * S + dx, y * S + dy, argb);
					}
				}
			}
		}

		return output;
	}
/*
	public ImageSprite[][] loadImage(String name) {

		ImageSprite[][] view = null;
		
		view[0][0] = appealImage(name+"0_0");
		view[1][0] = appealImage(name+"1_0");
		view[2][0] = appealImage(name+"2_0");
		view[3][0] = appealImage(name+"3_0");
		
		view[0][1] = appealImage(name+"0_1");
		view[1][1] = appealImage(name+"1_1");
		view[3][1] = appealImage(name+"2_1");
		view[3][1] = appealImage(name+"3_1");
		
		return view;
		
	}
	*/
	public void evolve(int SX,int SY) {
		this.shiftX +=SX;
		this.shiftY +=SY;
	}

	public int getShiftX() {
		return shiftX;
	}

	public void setShiftX(int shiftX) {
		this.shiftX = shiftX;
	}

	public int getShiftY() {
		return shiftY;
	}

	public void setShiftY(int shiftY) {
		this.shiftY = shiftY;
	}
	
	
}
