package main;

import java.sql.SQLException;

import database.connection.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.mainView.MainViewController;

public class Launch extends Application {

	public static Scene scene;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Launch.class.getResource("/view/mainView/MainView.fxml"));
			root = (BorderPane) loader.load();
			scene = new Scene(root);
			primaryStage.setScene(scene);
			// primaryStage.setMinHeight(600);
			// primaryStage.setMinWidth(1050);
			primaryStage.setTitle("Map Editor 0.0.1");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		DatabaseConnection.closeConnection();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
