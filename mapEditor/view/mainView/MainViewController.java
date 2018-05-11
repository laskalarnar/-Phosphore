package view.mainView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import database.maps.MapDB;
import database.maps.MapsLoader;
import database.simpleTiles.SimpleTileDB;
import database.simpleTiles.SimpleTilesLoader;
import database.spritesheets.SpritesheetDB;
import database.spritesheets.SpritesheetsLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Pair;
import physique.sprite.Spritesheet;
import physique.tile.SimpleTile;
import physique.world.Map;
import view.mainView.MainViewController.MapCell;
import view.map.newMap.NewMapController;
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
	@FXML
	private ListView<MapDB> Maps;

	private ArrayList<SpritesheetDB> SpritesheetsList = new ArrayList<>();
	private HashMap<Spritesheet, ArrayList<SimpleTile>> tileSets = new HashMap<>();

	ArrayList<MapDB> MapsArrayList = new ArrayList<>();
	public ObservableList<MapDB> MapsList = FXCollections.observableArrayList(MapsArrayList);

	@FXML
	private void initialize() {
		setToolBars();
		setTileList();
		setMapList();
		setContentStackPane();
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
		SimpleTilesLoader.updateSimpleTilesTable();
		SpritesheetsList = SpritesheetsLoader.loadSpritesheets();
		for (SpritesheetDB ss : SpritesheetsList) {
			ArrayList<SimpleTileDB> tileSet = SimpleTilesLoader.loadTileSet(ss.getSpritesheet());
			AnchorPane anchorPane = new AnchorPane();
			int i = 0;
			for (SimpleTileDB tile : tileSet) {
				Pair<Integer, Integer> coordinates = tile.getSimpleTile().getSpritesheetCoordinates();
				TileCell tileCell = new TileCell(tile);
				setUpTileCell(tileCell);
				tileCell.getChildren().add(new ImageView(ss.getSpritesheet().getTile(coordinates)));
				anchorPane.getChildren().add(tileCell);
				AnchorPane.setTopAnchor(tileCell, (double) Math.floorDiv(i, 12) * 17);
				AnchorPane.setLeftAnchor(tileCell, (double) (i % 12) * 17);
				i++;
			}
			Tiles.getChildren().add(new Label(ss.getSpritesheet().getName()));
			Tiles.getChildren().add(new Separator());
			Tiles.getChildren().add(anchorPane);
			Separator sep = new Separator();
			VBox.setMargin(sep, new Insets(5, 10, 0, 0));
			Tiles.getChildren().add(sep);
		}
	}

	private void setMapList() {

		Maps.setItems(MapsList);
		MapsList.addAll(MapsLoader.loadMaps());

		Maps.setCellFactory(new Callback<ListView<MapDB>, ListCell<MapDB>>() {
			@Override
			public ListCell<MapDB> call(ListView<MapDB> Maps) {
				return new MapCell();
			}
		});

		Maps.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MapDB>() {
			public void changed(ObservableValue<? extends MapDB> observable, MapDB oldValue, MapDB newValue) {
				displayMap(newValue.getMap());
			}
		});
	}

	private void setContentStackPane() {
		contentStackPane = new StackPane();
		((ScrollPane) MapBorderPane.getCenter()).heightProperty()
				.addListener((obs, oldVal, newVal) -> contentStackPane.setMinHeight((double) newVal));
		((ScrollPane) MapBorderPane.getCenter()).widthProperty()
				.addListener((obs, oldVal, newVal) -> contentStackPane.setMinWidth((double) newVal));
		((ScrollPane) MapBorderPane.getCenter()).setContent(contentStackPane);
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

	private void displayMap(Map map) {
		AnchorPane mapAnchorPane = new AnchorPane();
		for (int x = 0; x < map.getMapXX(); x++) {
			for (int y = 0; y < map.getMapYY(); y++) {
				SimpleTile tile = map.getTileArray()[x][y];
				if (tile != null) {
					ImageView img = new ImageView(tile.getSpritesheet().getTile(tile.getSpritesheetCoordinates()));
					mapAnchorPane.getChildren().add(img);
					AnchorPane.setTopAnchor(img, (double) x * 16);
					AnchorPane.setLeftAnchor(img, (double) y * 16);
				}
			}
		}
		mapAnchorPane.setMaxWidth(map.getMapXX() * 16);
		mapAnchorPane.setMaxHeight(map.getMapYY() * 16);
		contentStackPane.getChildren().add(mapAnchorPane);
	}

	@FXML
	private void handleNewMap() {
		try {
			AnchorPane root;
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("/view/map/newMap/NewMapView.fxml"));
			root = (AnchorPane) loader.load();
			NewMapController newMapController = loader.getController();
			newMapController.setMainViewController(this);
			Scene scene = new Scene(root);
			Stage newMapStage = new Stage();
			newMapController.setStage(newMapStage);
			newMapStage.setResizable(false);
			newMapStage.setTitle("New Map");
			newMapStage.setScene(scene);
			newMapStage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class TileCell extends AnchorPane {
		private SimpleTileDB tile;
		private Rectangle selected = new Rectangle(18, 18, Color.BLUE);

		public TileCell(SimpleTileDB tile) {
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

	public static class MapCell extends ListCell<MapDB> {
		@Override
		public void updateItem(MapDB item, boolean empty) {
			super.updateItem(item, empty);
			if (item != null) {
				setText(item.getMap().getMapName());
			}
		}
	}
}
