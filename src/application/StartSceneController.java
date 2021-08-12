package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import codes.Deck;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class StartSceneController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private int deckNumber;
	private int playerNumber;
	
	@FXML
	private Spinner<Integer> deckSpinner;
	@FXML
	private Spinner<Integer> playerSpinner;
	@FXML
	private Button exitButton;
	
	private TrackerApp x = TrackerApp.getInstance();

	//sets up the spinners with values that we need
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Integer> list = FXCollections.observableArrayList(1,2,4,5,6,7,8);
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(list);
		deckSpinner.setValueFactory(valueFactory);		
		deckNumber = deckSpinner.getValue();
		
		deckSpinner.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				deckNumber = deckSpinner.getValue();
			}
		});
		
		ObservableList<Integer> list2 = FXCollections.observableArrayList(1,2,3,4,5,6,7,8);
		SpinnerValueFactory<Integer> valueFactory2 = new SpinnerValueFactory.ListSpinnerValueFactory<Integer>(list2);
		playerSpinner.setValueFactory(valueFactory2);		
		playerNumber = playerSpinner.getValue();
		
		playerSpinner.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {
				playerNumber = playerSpinner.getValue();
			}
		});
	}
	//Continue button
	//sets the # of decks used and # of players playing
	//then switches the scene
	public void switchToPlayerListScene(ActionEvent event) throws Exception {
		x.setNumOfDecks(getDeckNumber());
		x.setNumOfPlayers(getPlayerNumber());
		root = FXMLLoader.load(getClass().getResource("PlayerListScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		System.out.println("deck chosen was: " + getDeckNumber());
		System.out.println("The amount of players: " + getPlayerNumber());
		
	}
	
	//exit button
	public void exit(ActionEvent event) {
	    Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
	}
	
	public int getDeckNumber() {
		return deckNumber;
	}
	
	
	public int getPlayerNumber() {
		return playerNumber;
	}
}

