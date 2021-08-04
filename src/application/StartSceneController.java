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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

public class StartSceneController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private int deckNumber;
	
	@FXML
	private Spinner<Integer> deckSpinner;

	
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
	}
	
	public void switchToPlayerListScene(ActionEvent event) throws Exception {
		root = FXMLLoader.load(getClass().getResource("PlayerListScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		System.out.println("deck chosen was: " + getDeckNumber());
	}
	
	public int getDeckNumber() {
		return deckNumber;
	}
}

