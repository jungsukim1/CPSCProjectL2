package application;

import java.io.IOException;
import java.util.ArrayList;

import codes.Dealer;
import codes.Deck;
import codes.Player;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TrackerApp {
	private int whosPlaying = -1;
	
	private int numOfPlayers;
	private int numOfDecks;
	private Deck deck;
	private ArrayList<Player> players;
	private Dealer dealer;
	
	private static TrackerApp singleton;
	
	
	
	public static TrackerApp getInstance() {
		if (singleton == null) {
			singleton = new TrackerApp();
		}
		return singleton;
	}
	
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	public void setNumOfPlayers(int numOfPlayers) {
		this.numOfPlayers = numOfPlayers;
	}
	public int getNumOfDecks() {
		return numOfDecks;
	}
	public void setNumOfDecks(int numOfDecks) throws Exception {
		this.numOfDecks = numOfDecks;
		this.deck = new Deck(numOfDecks);
		dealer = new Dealer(deck);
	}

	public Deck getDeck() {
		return deck;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Dealer getDealer() {
		return dealer;
	}
	
	//pointer to right player to ask if they want to play
	public int getWhosPlaying() {
		return whosPlaying;
	}
	//must be called after getWhosPlaying()
	public void setWhosPlayingNext() {
		whosPlaying++;
	}
	//used to reset back to the first player
	public void resetWhosPlaying() {
		whosPlaying = -1;
	}
	
	
	


}
