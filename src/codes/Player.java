package codes;

import java.util.ArrayList;

public abstract class Player {
	
	private double MONEY;
	private String NAME;
	private ArrayList<Hand> HANDS= new ArrayList<Hand>();
	
	private Boolean PLAY = null;
	
	protected Boolean HUMAN;
	protected Deck DECK;
	/**
	 * Constructor
	 * @param deck (takes in a deck in the constructor parameter)
	 *  
	 */
	public Player(Deck deck, String name, double startMoney) {

		this.DECK = deck;
		this.MONEY = startMoney;
		this.NAME = name;
	}
	//dont use
	public void wonHand(double moneyWon) {
		MONEY += moneyWon;
	}
	
	//dont use
	public void lostHand(double moneyWon) {
		MONEY -= moneyWon;
	}
	
	//use when player wants to play a round
	public void setup() {
		Hand hand = new Hand(DECK, this);
		HANDS.add(hand);
	}
	
	//use when drawing out cards
	public Card drawCard() {
		return (getHands().get(0)).hit();
	}
	
	
	
	//use for initial bet
	public void initialBet(double initialBet) throws Exception {
		getHands().get(0).bet(initialBet);
	}
	
	
	//use after splitting
	public void splitHandBet(double betForSplitHand) throws Exception {
		getHands().get(1).bet(betForSplitHand);
	}
	
	//check if they can split
	public Boolean canSplit() {
		if(HANDS.size() == 1 && ((HANDS.get(0)).getHand()).size() == 2) {
			Card firstCard = ((HANDS.get(0)).getHand()).get(0);
			Card secCard = ((HANDS.get(0)).getHand()).get(1);
			if(secCard.getValue().get(0) == firstCard.getValue().get(0)) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
	}
	
	
	//use after canSplit()
	public void splitHand() throws Exception {
		try {
			Hand splitHand = new Hand(DECK, this, HANDS.get(0).split());
			HANDS.add(splitHand);
		}
		catch(Exception e) {
			throw new Exception("You cannot split right now.");
		}
		
	}

	public Card getFirstCard() {
		Card firstCard = getHands().get(0).getHand().get(0);
		return firstCard;
	}
	
	public Card getSecondCard() {
		Card secCard = getHands().get(0).getHand().get(1);
		return secCard;
	}
	
	//use when asking player if they want to play this round
	public void playOrNo(Boolean bool) {
		this.PLAY = bool;
	}
	
	
	public void clearHands() {
		HANDS.clear();
	}
	
	//seeing if they want to play this round
	public Boolean getPlay() {
		return PLAY;
	}
	
	public double getMoney() {
		return MONEY;
	}
	
	public String getName() {
		return NAME;
	}
	
	public ArrayList<Hand> getHands(){
		return HANDS;
	}

	
	public Boolean isHuman() {
		return HUMAN;
	}
	
	public String toString() {
		return "Player name: " + getName() + "\nMoney: " + getMoney() + "\nIs human: " + isHuman() +
				"\nHands: " + getHands(); 
	}
	
	
	

}