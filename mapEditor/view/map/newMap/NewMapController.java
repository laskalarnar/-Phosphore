package view.map.newMap;

import database.maps.MapsLoader;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import physique.world.Map;
import view.mainView.MainViewController;

public class NewMapController {

	@FXML
	private TextField nameField;
	@FXML
	private TextField widthField;
	@FXML
	private TextField heightField;

	private Stage stage;
	private MainViewController mainViewController;

	public void setMainViewController(MainViewController mainViewController) {
		this.mainViewController = mainViewController;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
	private void addMap() {
		Map map = new Map(Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText()),
				nameField.getText());
		MapsLoader.addMap(map);
		stage.close();
	}

}
