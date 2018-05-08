package base;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Principale extends Application {

	Jeu X;

	public static void main(String[] args) throws InterruptedException, IOException {
		launch(args);
	}

	public void start(Stage primeryStage) throws IOException, InterruptedException {
		
		this.X = new Jeu(primeryStage);
		this.X.rendering();
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(15),ae -> {
			try {
				boucle();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		
		//boucle();
	}
	
	public void boucle() throws InterruptedException { 
		X.update();
		this.X.rendering();
		System.out.println("Un tour !!");
		//Thread.sleep(100  - (System.currentTimeMillis()-count1));

	}
}
