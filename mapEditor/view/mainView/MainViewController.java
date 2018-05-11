package view.mainView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import database.spritesheets.SpritesheetsLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Pair;
import physique.sprite.Spritesheet;
import physique.tile.SimpleTile;
import view.toolbars.ColumnToolBarController;
import view.toolbars.RowToolBarController;

public class MainViewController {

	private boolean unsaved;
	private boolean displayGrid;
	private StackPane contentStackPane;
	private TileCell selectedTileCell;

	@FXML
	private BorderPane MainBorderPane;
	@FXML
	private BorderPane MapBorderPane;
	@FXML
	private Slider zoomSlider;
	@FXML
	private VBox Tiles;

	private ArrayList<Spritesheet> SpritesheetsList = new ArrayList<>();
	private HashMap<Spritesheet, ArrayList<SimpleTile>> tileSets = new HashMap<>();

	@FXML
	private void initialize() {
		setToolBars();
		setTileList();
	}

	private void setToolBars() {
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

	private void setTileList() {
		SpritesheetsList = SpritesheetsLoader.loadSpritesheets();
		for (Spritesheet ss : SpritesheetsList) {
			ss.loadImage();
			ss.parseSpritesheet();
			HashMap<Pair<Integer, Integer>, Image> tileSet = ss.getTileSet();
			AnchorPane anchorPane = new AnchorPane();
			int i = 0;
			for (Pair<Integer, Integer> coordinates : tileSet.keySet()) {
				TileCell tileCell = new TileCell(new SimpleTile(ss, coordinates));
				setUpTileCell(tileCell);
				tileCell.getChildren().add(new ImageView(tileSet.get(coordinates)));
				anchorPane.getChildren().add(tileCell);
				AnchorPane.setTopAnchor(tileCell, (double) Math.floorDiv(i, 12) * 17);
				AnchorPane.setLeftAnchor(tileCell, (double) (i % 12) * 17);
				i++;
			}
			Tiles.getChildren().add(new Label(ss.getName()));
			Tiles.getChildren().add(new Separator());
			Tiles.getChildren().add(anchorPane);
			Separator sep = new Separator();
			VBox.setMargin(sep, new Insets(5, 10, 0, 0));
			Tiles.getChildren().add(sep);
		}
	}

	private void setUpTileCell(TileCell tile) {
		tile.setOnMouseClicked((event) -> {
			if (selectedTileCell != null) {
				selectedTileCell.unSelect();
			}
			selectedTileCell = tile;
			selectedTileCell.select();
		});
	}

	private class TileCell extends AnchorPane {
		private SimpleTile tile;
		private Rectangle selected = new Rectangle(18, 18, Color.BLUE);

		public TileCell(SimpleTile tile) {
			this.tile = tile;
			Color c = new Color(0, 0, 1, 0.2);
			selected.setFill(c);
		}

		public void select() {
			getChildren().add(selected);
			AnchorPane.setTopAnchor(selected, -1.0);
			AnchorPane.setLeftAnchor(selected, -1.0);
		}

		public void unSelect() {
			getChildren().remove(selected);
		}
	}
}
