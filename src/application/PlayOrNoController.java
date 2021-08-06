package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import codes.Player;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class PlayOrNoController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private TrackerApp tracker = TrackerApp.getInstance();
	private ArrayList<Player> players= tracker.getPlayers();
	private int initialBetValue;
	@FXML
	private Button noButton;
	@FXML
	private Button yesButton;
	@FXML
	private Label userName;
	@FXML
	private Slider betSlider;
	@FXML 
	private Label initialBetLabel;
	@FXML
	private Label betText;
	@FXML
	private Label questionLabel;
	@FXML
	private Label questionLabel1;
	@FXML
	private Button okButton;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		betSlider.setVisible(false);
		initialBetLabel.setVisible(false);
		questionLabel.setVisible(true);
		questionLabel1.setVisible(false);
		okButton.setVisible(false);
		betText.setVisible(false);
		tracker.setWhosPlayingNext();
		int indexOfPlayer = tracker.getWhosPlaying();
		userName.setText(players.get(indexOfPlayer).getName());
	}
		
		
	public void yesOrNo(ActionEvent event) throws IOException {
		if(tracker.getWhosPlaying() == tracker.getNumOfPlayers()-1) {
			tracker.resetWhosPlaying();
			tracker.drawCardsOut();
			
			root = FXMLLoader.load(getClass().getResource("PlayerTransition.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
			
			for(Player i : tracker.getPlayers()) {
				System.out.println(i.getName() + " money: " +i.getMoney() + " AI: " + i.isHuman() + " isPlaying: " + i.getPlay());
			}
			
		}
		else {
			root = FXMLLoader.load(getClass().getResource("PlayOrNo.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	public void yes(ActionEvent event) throws IOException {
		int indexOfPlayer = tracker.getWhosPlaying();
		Player player = players.get(indexOfPlayer);
		
		questionLabel.setVisible(false);
		questionLabel1.setVisible(true);
		yesButton.setVisible(false);
		noButton.setVisible(false);
		betSlider.setVisible(true);
		initialBetLabel.setVisible(true);
		okButton.setVisible(true);
		betText.setVisible(true);
		initialBetLabel.setText("$"+ Integer.toString((int)player.getMoney()));
		betSlider.setMin(1);
		betSlider.setMax(player.getMoney());
		betSlider.setValue(player.getMoney());
		
		betSlider.valueProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				initialBetValue = (int)betSlider.getValue();
				initialBetLabel.setText("$"+ Integer.toString(initialBetValue));
			}
		});
		
	}
	public void no(ActionEvent event) throws IOException {
		int indexOfPlayer = tracker.getWhosPlaying();
		Player player = players.get(indexOfPlayer);
		player.playOrNo(false);
		yesOrNo(event);
	}
	
	public void ok(ActionEvent event) throws Exception {
		int indexOfPlayer = tracker.getWhosPlaying();
		Player player = players.get(indexOfPlayer);
		player.playOrNo(true);
		player.setup();
		player.initialBet(initialBetValue);
		yesOrNo(event);
	}
	
	

}
