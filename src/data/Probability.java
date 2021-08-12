package data;

import java.util.ArrayList;
import java.util.Arrays;

import codes.Card;
import codes.Dealer;
import codes.Hand;

public class Probability {
	private ArrayList<String[]> TABLE;
	
	
	public Probability(ArrayList<String[]> table) {
		this.TABLE = table;
	}
	public int convert(Hand hand) {
		ArrayList<Card> Cards = hand.getHand(); 
		int size = Cards.size();
		int value = hand.getHandValue();
		Card firstCard = Cards.get(0);
		Card secondCard = Cards.get(1);
		
		//checking if there is an ace in the hand (used for later)
		boolean hasAce = false;
		boolean doubled = true;
		
		for (Card card : Cards) {
			if(card.getName().equals("Ace")) {
				hasAce = true;
			}
		}
		
		String rowName = "";
		 //checking for double
		if (size == 2 && firstCard.getValue().get(0) == secondCard.getValue().get(0)) {
			doubled = false;
			if(firstCard.getName() == "Ace") {
				rowName = firstCard.getName() + "+"+ secondCard.getName();
			}
			else {
				rowName = String.valueOf(firstCard.getValue().get(0)) + "+" + String.valueOf(secondCard.getValue().get(0));
			}
		}
		
		if (hasAce && doubled) {
			
			int y = value%10;
    		
    		if (y == 0){
    		    y = 10;
    		}
    		else if(y==1){
    		    y = 11;
    		}
    		int z = y-1;
    		
    		if(z == 1) {
    			rowName = "12";
    		}
    		else {
    			rowName = "Ace+"+String.valueOf(z);
    		}
		}
		
		else if (!hasAce && doubled) {
			rowName = String.valueOf(value);
		}
		
		int counter = 0;
		int rowNum = 0;
		for (String[] row : TABLE) {
			if(row[0].equals(rowName)) {
				rowNum = counter;
			}
			counter ++;
		}
		return rowNum;
		//checking for Ace
		
		
	}
	/**
	 * This method searches for the index number in the chart according to the faced up dealer card and returns int number
	 * @param cardName (Face up Dealer Card)
	 * @return
	 */
	public int searchDealerCard(Card card) {
		String[] dealerCardsRow = TABLE.get(0);
		int index = Arrays.asList(dealerCardsRow).indexOf(card.getName());
		return index;
	}
	/**
	 * This method searches for the row number (Excluding Ace combinations and splits), according to players cards and returns int number
	 * @param i (First Card)
	 * @param o (Second Card)
	 * @return
	 */
	public int searchRow(String i, String o) {
		int card1 = 0;
		int card2 = 0;
		int counter = 0;
		int counter1 = 0;
		ArrayList<String> hand = new ArrayList<String>();
		hand.add(i);
		hand.add(o);
		if (i.equals(o)) {
			hand.add(0, i + "+" + o);
		} else if (hand.contains("Ace") != false) {
			int Ace = hand.indexOf("Ace");
			hand.remove(Ace);
			hand.add(0, "Ace");
			hand.add(0, hand.get(0) + "+" + hand.get(1));
		} else {
			card1 = Integer.valueOf(i);
			card2 = Integer.valueOf(o);
			hand.add(0, String.valueOf(card1 + card2));
		}
		for (String[] row : TABLE) {
			if(row[0].equals(hand.get(0))) {
				counter1 = counter;
			}
			counter ++;
		}
		return counter1;
	}
	/**
	 * This method searches for the specific element according to the players card and dealer's face up card, returning a string of what the AI s
	 * @param row Row number (Player Card)
	 * @param column Column number (Dealer Card)
	 * @return "S", "H", "SP", "D", "Ds"
	 */
	public String searchElement(int row, int column) {
		String[] t = TABLE.get(row);
		String move = t[column];
		return move;
	}
	
	public String move(Hand playHand, Dealer dealer) {
		int DealerCard = searchDealerCard(dealer.getFirstCard());
		int PlayerCard = convert(playHand);
		String move = searchElement(PlayerCard, DealerCard);
		return move;
	}
	
}
	

