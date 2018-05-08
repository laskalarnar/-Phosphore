package physique;

import java.net.URL;

import afficheur.GIA;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ObjectGame implements GIA {

	public ImageSprite img;
	
	
	public ImageSprite getActualImage() {
		return img;
	}
	public void setImg(ImageSprite img) {
		this.img = img;
	}

	public ImageSprite appealImage(String access) {
		URL loc;
		loc = this.getClass().getResource(access);
		System.out.println("On cherche à afficher l'image du path :"+access) ;
		Image mamage = new Image(loc.toExternalForm());
		return new ImageSprite(resample(mamage,MULTIPLICATOR));
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
}
