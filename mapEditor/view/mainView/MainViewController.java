package view.mainView;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import view.toolbars.ColumnToolBarController;
import view.toolbars.RowToolBarController;

public class MainViewController {

	private boolean unsaved;
	private boolean displayGrid;
	private StackPane contentStackPane;

	@FXML
	private BorderPane MainBorderPane;
	@FXML
	private BorderPane MapBorderPane;
	@FXML
	private Slider zoomSlider;

	@FXML
	private void initialize() {
		// setToolBars();

	}

	public void setToolBars() {
		try {
			AnchorPane columnToolBar;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("/view/toolbars/ColumnToolBar.fxml"));
			columnToolBar = (AnchorPane) loader.load();
			ColumnToolBarController controller = loader.getController();
			controller.setMainViewController(this);
			MapBorderPane.setTop(columnToolBar);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			AnchorPane rowToolBar;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("/view/toolbars/RowToolBar.fxml"));
			rowToolBar = (AnchorPane) loader.load();
			RowToolBarController controller = loader.getController();
			controller.setMainViewController(this);
			MapBorderPane.setLeft(rowToolBar);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void setUnsaved(boolean unsaved) {
		this.unsaved = unsaved;
	}
}
