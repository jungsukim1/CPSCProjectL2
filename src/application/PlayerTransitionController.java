package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import codes.Card;
import codes.Hand;
import codes.Player;
import javafx.animation.TranslateTransition;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

//contains ending of the round scene, dealer show its card scene, players turn scene
public class PlayerTransitionController implements Initializable{
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private TrackerApp tracker = TrackerApp.getInstance();
	private ArrayList<Player> players = tracker.getPlayers();
	private Player player;
	private ArrayList<Card> dealerHand;
	
	@FXML
	private AnchorPane endRoundScene;
	@FXML
	private AnchorPane transitionScene;
	@FXML
	private Label name1, name2, name3, name4, name5, name6, name7, name8;
	@FXML
	private Label money1, money2, money3, money4, money5, money6, money7, money8;
	private ArrayList <Label> nameLabel = new ArrayList<Label>();
	private ArrayList <Label> moneyLabel = new ArrayList<Label>();
	
	@FXML
	private Label userName;
	@FXML
	private Button startButton;
	@FXML
	private Button quit;
	@FXML
	private Button continueButton;
	@FXML
	private Label handValueLabel;
	@FXML
	private AnchorPane dealerScene;
	@FXML
	private Button finishDealer;
	
	//sets up the 8 name labels, 8 money labels, 8 bet labels for th ending scene
	//switches state with the tracker to show whos playing in this state
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setupTheLabelArrays();
		transitionScene.setVisible(true);
		endRoundScene.setVisible(false);
		dealerScene.setVisible(false);
		finishDealer.setVisible(false);
		
		//first it will be -1
		tracker.setWhosPlayingNext(); //now 0
		int index = tracker.getWhosPlaying();
		
		if (index == tracker.getNumOfPlayers()) {
			try {
				roundOver();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			player = players.get(index);
			userName.setText(player.getName());
			if(!player.getPlay()) {
				this.initialize(arg0, arg1);
			}
		}
	}
	
	//start button, goes to the board scene
	public void start(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("Board.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	//exit button
	public void exit(ActionEvent event) {
	    Stage stage = (Stage) quit.getScene().getWindow();
	    stage.close();
	}
	
	//setup here
	//after showing the ending round results, if press continue we setup the dealer again (refresh its hand and adds in a new instance of a hand)
	//if the 50% or more of the deck is used, it refreshes the deck and reshuffles
	public void continueGame(ActionEvent event) throws Exception {
		tracker.updatePlayers();
		tracker.getDealer().clearHand();
		tracker.getDealer().setup();
		if(tracker.getNumOfPlayers() != 0) {
			for(Player p : players) {
				p.clearHands();
			}
			if(tracker.getDeck().getPercentageOfUsed() > 50) {
				System.out.println("More than 50% of the deck was used.\nReshuffling deck...\n");
				tracker.getDeck().resetDeck();
			}
			
			root = FXMLLoader.load(getClass().getResource("PlayOrNo.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else {
			root = FXMLLoader.load(getClass().getResource("EndScreen.fxml"));
			stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
	//showing the dealer cards at the end of the round
	private void showDealerCards() throws InterruptedException {
		int x = 0;
		//showing first two cards first to build suspense
		for(Card card : dealerHand) {
			ImageView im = new ImageView();
			im.setImage(card.getCardImage());
			im.setFitWidth(180);
	        im.setFitHeight(264);
	        im.setLayoutX(234);
	        im.setLayoutY(236);
	        TranslateTransition tt = new TranslateTransition(Duration.millis(2000), im);
		    tt.setByX(x);
		    tt.play();
		    x = x+ 185;
			dealerScene.getChildren().add(im);
		}
		handValueLabel.setText(" "+Integer.toString(tracker.getDealer().getHandValue()));
		Thread.sleep(500);
		finishDealer.setVisible(true);
		
		
	}
	
	//once all the players have played, the dealer now plays
	//it then shows the dealer cards scene to show case what cards it got
	public void roundOver() throws InterruptedException {
		tracker.getDealer().playNow();
		dealerHand = tracker.getDealer().getHand();
		dealerScene.setVisible(true);
		showDealerCards();
		for (Player p : players) {
			double wonOrLostAmount = 0;
			if(p.getPlay()) {
				for(Hand h : p.getHands()) {
					if(h.getHandValue() == tracker.getDealer().getHandValue() ) {
						wonOrLostAmount += 0;
					}
					else if(h.getHandValue() > tracker.getDealer().getHandValue() && h.getHandValue() <= 21) {
						h.win();
						wonOrLostAmount += h.getBetAmount();
					}
					else if (tracker.getDealer().getHandValue() > 21 && h.getHandValue() <= 21 ) {
						h.win();
						wonOrLostAmount += h.getBetAmount();
					}
					else {
						h.lose();
						wonOrLostAmount -= h.getBetAmount();
					}
				}
			}
		}

	}
	
	
	//reset the index of whos playing (resetting the states)
	//makes the ending round scene visible
	public void showEndingScene(ActionEvent event) {
		tracker.resetWhosPlaying();
		transitionScene.setVisible(false);
		dealerScene.setVisible(false);
		endRoundScene.setVisible(true);
		setRoundOverScene();
	}

	//sets the results for each player
	//makes the appropriate labels visible
	public void setRoundOverScene() {
		for (int i = 0; i < 8; i ++) {
			nameLabel.get(i).setVisible(false);
			moneyLabel.get(i).setVisible(false);
		}
		for (int i = 0; i < tracker.getNumOfPlayers(); i ++) {
			nameLabel.get(i).setText(players.get(i).getName());
			moneyLabel.get(i).setText("$"+String.valueOf((int)players.get(i).getMoney()));
			
			nameLabel.get(i).setVisible(true);
			moneyLabel.get(i).setVisible(true);
		}
	}
	//setting up the arrays to be used in initialize
	public void setupTheLabelArrays() {
		nameLabel.clear();
		moneyLabel.clear();
		nameLabel.add(name1);
		nameLabel.add(name2);
		nameLabel.add(name3);
		nameLabel.add(name4);
		nameLabel.add(name5);
		nameLabel.add(name6);
		nameLabel.add(name7);
		nameLabel.add(name8);
		moneyLabel.add(money1);
		moneyLabel.add(money2);
		moneyLabel.add(money3);
		moneyLabel.add(money4);
		moneyLabel.add(money5);
		moneyLabel.add(money6);
		moneyLabel.add(money7);
		moneyLabel.add(money8);
	}

}
