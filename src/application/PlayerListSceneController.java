package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import codes.AiPlayer;
import codes.HumanPlayer;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
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
	@FXML
	private TextField player1Name, player2Name, player3Name, player4Name, player5Name, player6Name, player7Name, player8Name;
	@FXML
	private CheckBox player1AI, player2AI, player3AI, player4AI, player5AI, player6AI, player7AI, player8AI;
	@FXML
	private Button exitButton;
	@FXML
	private Button nextButton;
	
	private ArrayList <Integer> money = new ArrayList<Integer>();
	
	private ArrayList <Slider> slider = new ArrayList<Slider>();
	
	private ArrayList <Label> label = new ArrayList<Label>();
	
	private ArrayList <TextField> names = new ArrayList<TextField>();
	
	private ArrayList <CheckBox> AI = new ArrayList<CheckBox>();
	
	private TrackerApp y = TrackerApp.getInstance();
	
	//initializes the slider listeners since when you slide it, it should update the text linked with eack slider
	//also adds the 8 checkbox, 8 label, 8 slider, 8 textbox, 8 money labels into each respective array
	// and then from the array we choose what to show and not to show depending on how many players are going to play
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		player1Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player1MONEY = (int)player1Slider.getValue();
				player1Text.setText("$"+Integer.toString(player1MONEY));
			}	
		});
		AI.add(player1AI);
		names.add(player1Name);
		slider.add(player1Slider);
		money.add(player1MONEY);
		label.add(player1Text);
		player2Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player2MONEY = (int)player2Slider.getValue();
				player2Text.setText("$"+Integer.toString(player2MONEY));
			}	
		});
		AI.add(player2AI);
		names.add(player2Name);
		slider.add(player2Slider);
		money.add(player2MONEY);
		label.add(player2Text);
		player3Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player3MONEY = (int)player3Slider.getValue();
				player3Text.setText("$"+Integer.toString(player3MONEY));
			}	
		});
		AI.add(player3AI);
		names.add(player3Name);
		slider.add(player3Slider);
		money.add(player3MONEY);
		label.add(player3Text);
		player4Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player4MONEY = (int)player4Slider.getValue();
				player4Text.setText("$"+Integer.toString(player4MONEY));
			}	
		});
		AI.add(player4AI);
		names.add(player4Name);
		slider.add(player4Slider);
		money.add(player4MONEY);
		label.add(player4Text);
		player5Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player5MONEY = (int)player5Slider.getValue();
				player5Text.setText("$"+Integer.toString(player5MONEY));
			}	
		});
		AI.add(player5AI);
		names.add(player5Name);
		slider.add(player5Slider);
		money.add(player5MONEY);
		label.add(player5Text);
		player6Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player6MONEY = (int)player6Slider.getValue();
				player6Text.setText("$"+Integer.toString(player6MONEY));
			}	
		});
		AI.add(player6AI);
		names.add(player6Name);
		slider.add(player6Slider);
		money.add(player6MONEY);
		label.add(player6Text);
		player7Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player7MONEY = (int)player7Slider.getValue();
				player7Text.setText("$"+Integer.toString(player7MONEY));
			}	
		});
		AI.add(player7AI);
		names.add(player7Name);
		slider.add(player7Slider);
		money.add(player7MONEY);
		label.add(player7Text);
		player8Slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				player8MONEY = (int)player8Slider.getValue();
				player8Text.setText("$"+Integer.toString(player8MONEY));
			}	
		});
		AI.add(player8AI);
		names.add(player8Name);
		slider.add(player8Slider);
		money.add(player8MONEY);
		label.add(player8Text);
		
		for (int i = 0; i < 8; i ++) {
			slider.get(i).setVisible(false);
			label.get(i).setVisible(false);
			names.get(i).setVisible(false);
			AI.get(i).setVisible(false);
		}
		
		for (int i = 0; i < y.getNumOfPlayers(); i ++) {
			slider.get(i).setVisible(true);
			//money.get(i);
			label.get(i).setVisible(true);
			names.get(i).setVisible(true);
			AI.get(i).setVisible(true);
		}
	}
	
	//backButton
	public void switchToStartScene(ActionEvent event) throws IOException {
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
	
	//nextButton and sets the players that are about to play
	//goes to next scene
	public void createPlayers(ActionEvent event) throws IOException {
		ArrayList<Player> players = new ArrayList<Player>();
		for (int i = 0; i < y.getNumOfPlayers(); i ++) {
			if(AI.get(i).isSelected()) {
				HumanPlayer player = new  HumanPlayer (y.getDeck(), names.get(i).getText(), slider.get(i).getValue());
				players.add(player);
			}else {
				AiPlayer AIplayer = new AiPlayer (y.getDeck(), names.get(i).getText(), slider.get(i).getValue());
				players.add(AIplayer);
			}
		}
		y.setPlayers(players);
		
		root = FXMLLoader.load(getClass().getResource("PlayOrNo.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
	}
}