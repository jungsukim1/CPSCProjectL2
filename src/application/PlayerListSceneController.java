package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class PlayerListSceneController implements Initializable{
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	//playerMONEY
	int player1MONEY, player2MONEY, player3MONEY, player4MONEY, player5MONEY, player6MONEY, player7MONEY, player8MONEY;
	@FXML
	private Slider player1Slider, player2Slider, player3Slider, player4Slider, player5Slider, player6Slider, player7Slider, player8Slider;
	@FXML
	private Label player1Text, player2Text, player3Text, player4Text, player5Text, player6Text, player7Text, player8Text;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		player1Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player1MONEY = (int)player1Slider.getValue();
				player1Text.setText("$"+Integer.toString(player1MONEY));
			}	
		});
		player2Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player2MONEY = (int)player2Slider.getValue();
				player2Text.setText("$"+Integer.toString(player2MONEY));
			}	
		});
		player3Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player3MONEY = (int)player3Slider.getValue();
				player3Text.setText("$"+Integer.toString(player3MONEY));
			}	
		});
		player4Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player4MONEY = (int)player4Slider.getValue();
				player4Text.setText("$"+Integer.toString(player4MONEY));
			}	
		});
		player5Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player5MONEY = (int)player5Slider.getValue();
				player5Text.setText("$"+Integer.toString(player5MONEY));
			}	
		});
		player6Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player6MONEY = (int)player6Slider.getValue();
				player6Text.setText("$"+Integer.toString(player6MONEY));
			}	
		});
		player7Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player7MONEY = (int)player7Slider.getValue();
				player7Text.setText("$"+Integer.toString(player7MONEY));
			}	
		});
		player8Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player8MONEY = (int)player8Slider.getValue();
				player8Text.setText("$"+Integer.toString(player8MONEY));
			}	
		});
	}
	
	public void switchToStartScene(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	
}