package view.toolbars;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.mainView.MainViewController;

public class ColumnToolBarController {

	@FXML
	private Button addLeftColumn;
	@FXML
	private Button addRightColumn;
	@FXML
	private Button delLeftColumn;
	@FXML
	private Button delRightColumn;
	
	private MainViewController mainViewController;
	
	public void setMainViewController(MainViewController mainViewController) {
		this.mainViewController = mainViewController;
	}
}
