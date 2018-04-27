package base;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;

public class Principale extends Application {

	static Jeu X;

	public static void main(String[] args) throws InterruptedException, IOException {
		launch(args);
	}

	public void start(Stage primeryStage) throws Exception {
		Jeu X = new Jeu(primeryStage);
		boucle();
	}
	
	public static void boucle() throws InterruptedException {
		while(Jeu.fini == false) {
			long count1 = System.currentTimeMillis(); 
			//X.update();
			//X.render();
			System.out.println("Un tour !!");
			Thread.sleep(100 - (System.currentTimeMillis()-count1));
		}
	}
}
