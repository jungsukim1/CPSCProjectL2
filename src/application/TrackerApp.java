package application;

import java.util.ArrayList;

import codes.Deck;
import codes.Player;

public class TrackerApp {
	private int numOfPlayers;
	private int numOfDecks;
	private Deck deck;
	private ArrayList<Player> players;
	
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
	
	
	


}
