package codes;

import java.util.ArrayList;

public class Hand {
	private int HAND_VALUE;
	private double BET_AMOUNT; //dynamic
	private ArrayList<Card> HAND = new ArrayList<Card>();
	private Deck DECK;
	private Player PLAYER;
	private boolean FINISHED;
	
	public Hand(Deck deck, Player player) {
		this.DECK = deck;
		this.BET_AMOUNT = 0;
		this.PLAYER = player;
		this.FINISHED = false;
	}
	
	public Hand(Deck deck, Dealer dealer) {
		this.DECK = deck;
	}
	
	//use when split
	public Hand(Deck deck, Player player, Card card) {
		this.DECK = deck;
		this.BET_AMOUNT = 0;
		this.PLAYER = player;
		addSplitCard(card);
	}
	
	
	public Card hit() {
		Card card = DECK.drawCard();
		HAND.add(card);
		HAND_VALUE += (card.getValue()).get(0); //note that every Ace is initially calculated with value = 1
		changeAceValue();
		return card;
	}

	private void addSplitCard(Card card) {
		HAND.add(card);
		HAND_VALUE += (card.getValue()).get(0); //note that every Ace is initially calculated with value = 1
		changeAceValue();
	}
	
	private void changeAceValue() {
		boolean hasAce = false;
		int valueWithoutAce = 0;
		int numberOfOtherAces = -1;
		//checking if the hand has an Ace
		for (Card card : HAND) {
			if ((card.getName()).equals("Ace")) {
				hasAce = true;
				numberOfOtherAces++;
			}
			else {
				valueWithoutAce += card.getValue().get(0);
			}
		}
		
		int valueOfAce = HAND_VALUE - valueWithoutAce - numberOfOtherAces;
		
		if (hasAce) {
			//first try setting the Ace to 11
			if ((HAND_VALUE - valueOfAce + 11) <= 21) {
				HAND_VALUE = HAND_VALUE - valueOfAce + 11; //setting one of the Ace(s) to 11
			}
			else if((HAND_VALUE - valueOfAce + 1) <= 21) {
				HAND_VALUE = HAND_VALUE - valueOfAce + 1; //setting one of the Ace(s) to 1
			}
			else {
				HAND_VALUE = HAND_VALUE - valueOfAce + 1; //set the Ace to 1 default if its over 21
			}
		}
		
	}
	
	//dont use
	public Card split() {
		Card temp = HAND.get(1);
		HAND.remove(1);
		if (temp.getName().equals("Ace")) {
			HAND_VALUE -= 11;
		}
		else {
			HAND_VALUE -= (temp.getValue()).get(0);
		}
		
		return temp;
	}
	
	
	//dont use
	public void bet(double betAmount) throws Exception {
		if(betAmount <= PLAYER.getMoney()) {
		BET_AMOUNT = betAmount;
		}
		else {
			throw new Exception("The betting amount is greater than the total amount that the player has right now.");
		}
	}
	
	//use to check if you can double
	public Boolean canDouble() {
		if(HAND.size() == 2 && HAND_VALUE != 21) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//use after canDouble()
	public void doubleDown() throws Exception {
		if(BET_AMOUNT * 2 <= PLAYER.getMoney()) {
		BET_AMOUNT = BET_AMOUNT * 2;
		}
		else {
			throw new Exception("The betting amount is greater than the total amount that the player has right now.");
		}
	}
	
	public void allIn() {
		BET_AMOUNT = PLAYER.getMoney();
	}
	
	public void isFinished() {
		this.FINISHED = true;
	}
	public boolean getIsFinished() {
		return FINISHED;
	}
	
	public void win() {
		PLAYER.wonHand(BET_AMOUNT);
	}
	
	public void lose() {
		PLAYER.lostHand(BET_AMOUNT);
	}
	
	public ArrayList<Card> getHand(){
		return HAND;
	}
	
	public int getHandValue() {
		return HAND_VALUE;
	}
	
	public double getBetAmount() {
		return BET_AMOUNT;
	}
	
	public String toString() {
		return "\n\tHand Value: " + getHandValue() + "\n\tBet Amount: " + getBetAmount() + "\n\tCards: " + getHand();
	}
	

}
