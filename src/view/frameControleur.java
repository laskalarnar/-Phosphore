package view;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;



public class frameControleur {


	@FXML
	private AnchorPane MainAnchorPane;

	@FXML
	public void initialize() {
		System.out.println("Création de la fenêtre ! C'est parti pour une nouvelle aventure !");

	}
}
