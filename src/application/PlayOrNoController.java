package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import codes.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PlayOrNoController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private TrackerApp tracker = TrackerApp.getInstance();
	private ArrayList<Player> players= tracker.getPlayers();
	@FXML
	private Button noButton;
	@FXML
	private Button yesButton;
	@FXML
	private Label userName;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tracker.setWhosPlayingNext();
		int indexOfPlayer = tracker.getWhosPlaying();
		userName.setText(players.get(indexOfPlayer).getName());
	}
	
	public void yesOrNo(ActionEvent event) throws IOException {
		if(tracker.getWhosPlaying() == tracker.getNumOfPlayers()-1) {
			tracker.resetWhosPlaying();
			root = FXMLLoader.load(getClass().getResource("Board.fxml"));
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
		player.playOrNo(true);
		player.setup();
		yesOrNo(event);
	}
	public void no(ActionEvent event) throws IOException {
		int indexOfPlayer = tracker.getWhosPlaying();
		Player player = players.get(indexOfPlayer);
		player.playOrNo(false);
		yesOrNo(event);
	}

}
