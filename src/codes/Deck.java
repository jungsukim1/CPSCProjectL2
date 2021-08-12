package codes;

import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	
	private ArrayList<Card> CARDS = new ArrayList<>();
	private ArrayList<Card> USED_CARDS = new ArrayList<>(); //dynamic array list used to store cards that have been used
	private int numberOfDecks = 0; //number of decks used
	
	/**
	 * Constructor
	 * @param number (number of decks (52 cards each) you want in the main deck to play with)
	 * @throws Exception (Constructor argument must be greater than zero.)
	 */
	public Deck(int number) throws Exception {
		addDeck(number);
		shuffleDeck();
	}

	/**
	 * This method adds how ever many decks depending on the parameter
	 * @param howMany (how many decks you want to add in int)
	 * @throws Exception (if the parameter is less than or equal to zero, error.)
	 */
	public void addDeck(int howMany) throws Exception {
		ArrayList<String> SUIT = new ArrayList<>();
		ArrayList<String> ELEMENT = new ArrayList<>();
		//Adding to elements list
		ELEMENT.add("Ace");
		ELEMENT.add("Two");
		ELEMENT.add("Three");
		ELEMENT.add("Four");
		ELEMENT.add("Five");
		ELEMENT.add("Six");
		ELEMENT.add("Seven");
		ELEMENT.add("Eight");
		ELEMENT.add("Nine");
		ELEMENT.add("Ten");
		ELEMENT.add("Jack");
		ELEMENT.add("Queen");
		ELEMENT.add("King");
				
		//Adding to suit list
		SUIT.add("Heart");
		SUIT.add("Spade");
		SUIT.add("Clover");
		SUIT.add("Diamond");
		
		if(howMany > 0) {
		
			//Adding how many decks are added
			numberOfDecks += howMany;
		
			for(int j = 0; j< howMany; j++) {
				for(String suit : SUIT) {
					for(int i=0; i<13; i++) {
						if(i < 10) {
							CARDS.add(new Card(ELEMENT.get(i), suit, i+1));
						}
						else {
							CARDS.add(new Card(ELEMENT.get(i), suit, 10));
						}
					}
				}
			}	
		}
		else {
			throw new Exception("Argument must be greater than 0.");
		}
	}
	
	/**
	 * This method picks the first card in the array and then adds the card into USED pile array
	 * @return returns the card that the user drew
	 */
	public Card drawCard() {
		Card temp = CARDS.get(0);
		USED_CARDS.add(temp);
		CARDS.remove(0);
		
		return temp;	
	}
	
	/**
	 * This method adds the used pile of cards into the main using cards AND then shuffles the deck
	 */
	public void resetDeck() {
		CARDS.addAll(USED_CARDS);
		USED_CARDS.clear();
		shuffleDeck();
	}
	
	
	/**
	 * This method shuffles the Deck
	 */
	private void shuffleDeck() {
		Collections.shuffle(CARDS);
		Collections.shuffle(CARDS);
		Collections.shuffle(CARDS);
		Collections.shuffle(CARDS);
		Collections.shuffle(CARDS);
	}
	
	/**
	 * This method returns a double. Which is the percentage of used cards compared to the total amount of cards in the initial deck.
	 * @return double (percentage of used cards)
	 */
	public double getPercentageOfUsed() {
		int usedCards = USED_CARDS.size();
		int totalCards = USED_CARDS.size() + CARDS.size();
		double percent = (((double)usedCards) / totalCards) * 100;
		return percent;
	}
	
	/**
	 * This method returns the amount of 52 card decks being used for the main playing deck
	 * @return int (number of 52 card decks being used for the main deck)
	 */
	public int getNumberOfDecks() {
		return numberOfDecks;
	}
	
	/**
	 * This method prints out all the cards in the deck that have not been used
	 */
	public void getDeckInfo() {
		for (Card card : CARDS) {
			System.out.println(card.toString());
		}
	}
	
	/**
	 * This method prints out all the cards in the deck that HAVE been used
	 */
	public void getUsedDeckInfo() {
		for (Card card : USED_CARDS) {
			System.out.println(card.toString());
		}
	}
	
}
