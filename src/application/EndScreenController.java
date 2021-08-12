package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

//when the game is over switch to this screen
public class EndScreenController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private Button exitButton;
	@FXML
	private Button playAgainButton;
	
	
	//play again button
	public void playAgain(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//exit button
	public void exit(ActionEvent event) {
		   Stage stage = (Stage) exitButton.getScene().getWindow();
		   stage.close();
	}
}
