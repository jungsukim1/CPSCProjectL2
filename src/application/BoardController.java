package application;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


import codes.Card;
import codes.Dealer;
import codes.Deck;
import codes.Hand;
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
	private Button hitButton;
	@FXML
	private Button standButton;
	@FXML
	private Button doubleButton;
	@FXML
	private Button splitButton;
	@FXML
	private Button exitButton;
	@FXML
	private Label userName;
	@FXML
	private AnchorPane boardScene;
	@FXML
	private Button continueButton;
	@FXML
	private Label overLabel;
	@FXML
	private Label jackPotLabel;
	@FXML
	private Label handValueLabel;
	
	
	
	private TrackerApp tracker = TrackerApp.getInstance();
	private ArrayList<Player> players= tracker.getPlayers();
	private Player player;
	private ArrayList<Hand> hands;
	private int handIndex;
	
	private int xs = 25;
	private int ys = -30;
	
	private int x; //player
	private int y; //player
	private int x0 = 0; //dealer
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		x = 0;
		y = 0;
		x0 = 0;
		handIndex = 0;
		player = players.get(tracker.getWhosPlaying());
		
		splitButton.setDisable(true);
		doubleButton.setDisable(true);
		continueButton.setVisible(false);
		overLabel.setVisible(false);
		jackPotLabel.setVisible(false);
		
		hands = player.getHands();
		userName.setText(player.getName());
		
		if(player.canSplit() && !player.getSplit()) {
			splitButton.setDisable(false);
		}
		if(hands.get(handIndex).canDouble()) {
			doubleButton.setDisable(false);
		}
		handValueLabel.setText(Integer.toString(hands.get(handIndex).getHandValue()));
		
		showDealerCard();
		showInitialPlayerCards();
	}
	
	public void splitHit(ActionEvent event) {
		doubleButton.setDisable(true);
		Hand hand = hands.get(handIndex);
		Card card = hand.hit();
		ImageView im = new ImageView();
        im.setImage(card.getCardImage());
        im.setFitWidth(137);
        im.setFitHeight(180);
        im.setLayoutX(400);
        im.setLayoutY(497);
        TranslateTransition tt = new TranslateTransition(Duration.millis(20), im);
	    tt.setByX(xs);
	    tt.setByY(ys);
	    tt.play();
	    xs = xs+25;
	    ys = ys-30;
		boardScene.getChildren().add(im);
		if(hand.getHandValue() > 21) {
			continueButton.setVisible(true);
			splitButton.setDisable(true);
			standButton.setDisable(true);
			doubleButton.setDisable(true);
			hitButton.setDisable(true);
			overLabel.setVisible(true);
			hand.isFinished();
		}
		else if(hand.getHandValue() == 21) {
			continueButton.setVisible(true);
			splitButton.setDisable(true);
			standButton.setDisable(true);
			doubleButton.setDisable(true);
			hitButton.setDisable(true);
			overLabel.setVisible(false);
			jackPotLabel.setVisible(true);
			hand.isFinished();
		}
		handValueLabel.setText(Integer.toString(hands.get(handIndex).getHandValue()));
	}
	
	public void hit(ActionEvent event) {
		if(handIndex == 1){
			splitHit(event);
		}
		else {
			doubleButton.setDisable(true);
			Hand hand = hands.get(handIndex);
			Card card = hand.hit();
			if(hand.canDouble()) {
				doubleButton.setDisable(false);
			}
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
			boardScene.getChildren().add(im);
			
			if(hand.getHandValue() > 21) {
				continueButton.setVisible(true);
				splitButton.setDisable(true);
				standButton.setDisable(true);
				doubleButton.setDisable(true);
				hitButton.setDisable(true);
				overLabel.setVisible(true);
				hand.isFinished();
				if(player.getSplit()) {
					handIndex = 1;
					x = 0;
					y = 0;
					continueButton.setVisible(false);
					splitButton.setDisable(true);
					standButton.setDisable(false);
					doubleButton.setDisable(false);
					hitButton.setDisable(false);
					overLabel.setVisible(false);
					hit(event);
					if(hands.get(handIndex).canDouble()) {
						doubleButton.setDisable(false);
					}
					handValueLabel.setText(Integer.toString(hands.get(handIndex).getHandValue()));
				}
			}
			else if(hand.getHandValue() == 21) {
				continueButton.setVisible(true);
				splitButton.setDisable(true);
				standButton.setDisable(true);
				doubleButton.setDisable(true);
				hitButton.setDisable(true);
				overLabel.setVisible(false);
				jackPotLabel.setVisible(true);
				hand.isFinished();
				if(player.getSplit()) {
					handIndex = 1;
					x = 0;
					y = 0;
					continueButton.setVisible(false);
					splitButton.setDisable(true);
					standButton.setDisable(false);
					doubleButton.setDisable(false);
					hitButton.setDisable(false);
					overLabel.setVisible(false);
					jackPotLabel.setVisible(false);
					//hit it once
					hit(event);
					if(hands.get(handIndex).canDouble()) {
						doubleButton.setDisable(false);
					}
					handValueLabel.setText(Integer.toString(hands.get(handIndex).getHandValue()));
				}
			}
			handValueLabel.setText(Integer.toString(hand.getHandValue()));
		}
	}
	
	public void doubleDown(ActionEvent event) throws Exception {
		Hand hand = hands.get(handIndex);
		hand.doubleDown();
		hit(event);
		hitButton.setDisable(true);
	}
	
	public void split(ActionEvent event) throws Exception {
		int bet = (int)hands.get(0).getBetAmount();
		player.splitHand();
		player.splitHandBet(bet);
		root = FXMLLoader.load(getClass().getResource("Board.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void continueMethod(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("PlayerTransition.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void showInitialPlayerCards() {
		Hand hand = hands.get(handIndex);
		ArrayList<Card> cards = new ArrayList<Card>();
		ArrayList<Card> splitCards = new ArrayList<Card>();
		if (player.getSplit()) {
			player.getHands().get(0).hit();
			handValueLabel.setText(Integer.toString(hands.get(handIndex).getHandValue()));
			cards.add(player.getHands().get(0).getHand().get(0));
			cards.add(player.getHands().get(0).getHand().get(1));
			splitCards.add(player.getHands().get(1).getHand().get(0));
		}
		else {
			cards.add(player.getFirstCard());
			cards.add(player.getSecondCard());
		}
		
		for(Card c : cards) {
			ImageView im = new ImageView();
			im.setImage(c.getCardImage());
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
		    boardScene.getChildren().add(im);
		}
		for(Card c : splitCards) {
			ImageView im = new ImageView();
			im.setImage(c.getCardImage());
			im.setFitWidth(137);
	        im.setFitHeight(180);
	        im.setLayoutX(400);
	        im.setLayoutY(497);
	        TranslateTransition tt = new TranslateTransition(Duration.millis(20), im);
		    tt.setByX(0);
		    tt.setByY(0);
		    tt.play();
		    boardScene.getChildren().add(im);
		}
		
		if(hand.getHandValue() > 21) {
			continueButton.setVisible(true);
			splitButton.setDisable(true);
			standButton.setDisable(true);
			doubleButton.setDisable(true);
			hitButton.setDisable(true);
			overLabel.setVisible(true);
		}
		else if(hand.getHandValue() == 21) {
			continueButton.setVisible(true);
			splitButton.setDisable(true);
			standButton.setDisable(true);
			doubleButton.setDisable(true);
			hitButton.setDisable(true);
			overLabel.setVisible(false);
			jackPotLabel.setVisible(true);
		}
	}
	
	public void showDealerCard() {
		ArrayList<Card> dealerHand = new ArrayList<Card>();
		Card firstCard = tracker.getDealer().getHand().get(0);
		Card secondCard = new Card("cardBack.jpg");
		dealerHand.add(firstCard);
		dealerHand.add(secondCard);
		
		for(Card c : dealerHand) {
			ImageView im = new ImageView();
			im.setImage(c.getCardImage());
			im.setFitWidth(89);
	        im.setFitHeight(132);
	        im.setLayoutX(334);
	        im.setLayoutY(41);
	        TranslateTransition tt = new TranslateTransition(Duration.millis(20), im);
		    tt.setByX(x0);
		    //tt.setByY(y0);
		    tt.play();
		    x0 = x0+95;
			boardScene.getChildren().add(im);
		}
	}
	
	public void exit(ActionEvent event) {
	    Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
	}
	
	public void stand(ActionEvent event) throws IOException {
		continueButton.setVisible(true);
		splitButton.setDisable(true);
		standButton.setDisable(true);
		doubleButton.setDisable(true);
		hitButton.setDisable(true);
		overLabel.setVisible(true);
		player.getHands().get(handIndex).isFinished();
		if((player.getHands().size()>1) && (!player.getHands().get(1).getIsFinished())) {
			continueButton.setVisible(false);
			splitButton.setDisable(true);
			standButton.setDisable(false);
			doubleButton.setDisable(true);
			hitButton.setDisable(false);
			overLabel.setVisible(false);
			handIndex = 1;
			x = 0;
			y = 0;
			//hit it once
			hit(event);
			
			if(hands.get(handIndex).canDouble()) {
				doubleButton.setDisable(false);
			}
			handValueLabel.setText(Integer.toString(hands.get(handIndex).getHandValue()));
		}
	}
	
	
	
	
	

	
	
}
