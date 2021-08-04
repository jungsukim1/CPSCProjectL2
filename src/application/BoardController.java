package application;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import codes.Card;
import codes.Dealer;
import codes.Deck;
import codes.Player;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class BoardController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
	private ImageView cardImage;
	
	@FXML
	private Button showButton;
	@FXML
	private Button exitButton;
	@FXML
	private Label userName;
	@FXML
	private AnchorPane anchor;
	
	private TrackerApp tracker = TrackerApp.getInstance();
	private ArrayList<Player> players= tracker.getPlayers();

	private int x;
	private int y;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tracker.setWhosPlayingNext();
		if(tracker.getWhosPlaying() == tracker.getNumOfPlayers()) {
			//call for new round
		}
		int indexOfPlayer = tracker.getWhosPlaying();
		userName.setText(players.get(indexOfPlayer).getName());
		x = 0;
		y = 0;
		
		if(!players.get(indexOfPlayer).getPlay()) {
			this.initialize(arg0, arg1);
		}
		
	}
	

	public void showCard(ActionEvent e) throws IOException {
		Card card = players.get(tracker.getWhosPlaying()).drawCard();
		ImageView im = new ImageView();
        im.setImage(card.getCardImage());
        im.setFitWidth(137);
        im.setFitHeight(180);
        im.setLayoutX(572);
        im.setLayoutY(497);
        TranslateTransition tt = new TranslateTransition(Duration.millis(20), im);
	    tt.setByX(x);
	    tt.setByY(y);
	    tt.play();
	    x = x+25;
	    y = y-30;
		anchor.getChildren().add(im);
		
		System.out.println("CARDS\n");
		tracker.getDeck().getDeckInfo();
		System.out.println("\n\n Used CARDS\n");
		tracker.getDeck().getUsedDeckInfo();
		
		
		}
	
	public void exit(ActionEvent event) {
	    Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
	}

	
	
}
