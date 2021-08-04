package codes;

import java.util.ArrayList;

public class Dealer {
	private Deck DECK;
	private  ArrayList<Hand> HANDS = new ArrayList<Hand>();

	public Dealer(Deck deck)
	{
		this.DECK = deck;
		setup();
	}
	
	//setup the dealer every new round
	public void setup() {
		Hand hand = new Hand(DECK, this);
		HANDS.add(hand);
	}
	
	/* 
	 * Figures out what to do next basically
	 */
	public void playNow(){	
		
		boolean hold = false;
		while(hold == false) {
		
		if (getHandValue() > 21){
			hold = true;
		}
		else if (getHandValue() == 21){
			hold = true;
		}
		else if (getHandValue() <17)
		{
			System.out.println("Dealer hit: " + HANDS.get(0).hit());
			System.out.println("Dealer hand: " + getHand());
			System.out.println("Dealer hand value: " + getHandValue());
		}
		
		else if(getHandValue() >= 17 && getHandValue() <21) {
			hold = true;
		}
		
	}
}

	//use when drawing out cards
	public Card drawCard() {
		return (HANDS.get(0)).hit();
	}
	
	public Card getFirstCard() {
		Card firstCard = HANDS.get(0).getHand().get(0);
		return firstCard;
	}
	
	public Card getSecondCard() {
		Card secCard = HANDS.get(0).getHand().get(1);
		return secCard;
	}
	
	public void clearHand(){	
		HANDS.clear();
	}
	
	
	public int getHandValue() {
		return HANDS.get(0).getHandValue();
	}
	
	public ArrayList<Card> getHand(){
		return HANDS.get(0).getHand();
	}
	
}