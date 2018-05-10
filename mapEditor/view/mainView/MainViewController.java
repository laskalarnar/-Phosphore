package view.mainView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import database.SpritesheetsLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import physique.sprite.Spritesheet;
import physique.tile.Tile;
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
	
	private ArrayList<Spritesheet> SpritesheetsList = new ArrayList<>();
	private HashMap<Spritesheet, ArrayList<Tile>> tileSets = new HashMap<>();

	@FXML
	private void initialize() {
		setToolBars();
		setTileList();
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
	
	public void setTileList() {
		SpritesheetsList = SpritesheetsLoader.loadSpritesheets();
		for(Spritesheet ss : SpritesheetsList) {
			ss.loadImage();
			ss.parseSpritesheet();
		}
		/*Stage test = new Stage();
		AnchorPane a = new AnchorPane();
		SpritesheetsList.get(0).loadImage();
		ImageView img = new ImageView(SpritesheetsList.get(0).getImage());
		a.getChildren().add(img);
		Scene sc = new Scene(a);
		test.setScene(sc);
		test.show();*/
	}
}
