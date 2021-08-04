package codes;

import java.util.ArrayList;

public class Card {
	private String NAME = "";
	private ArrayList<Integer> VALUE = new ArrayList<Integer>();
	private String SUIT = "";
	
	/**
	 * 
	 * @param name (name of the card. Ex. Jack, Queen, etc.)
	 * @param suit (suit of the card. Ex. Heart, Diamond, etc.)
	 * @param value (value of the card. Ex. 2, 3, 4, etc.)
	 */
	public Card(String name, String suit, int value) {
		this.SUIT = suit;
		this.NAME = name;
		if(name == "Ace") {
			this.VALUE.add(1);
			this.VALUE.add(11);
		}
		else {
			this.VALUE.add(value);
		}
	}
	
	public ArrayList<Integer> getValue(){
		return VALUE;
	}
	
	public String getSuit() { 
		return SUIT;
	}
	
	public String getName() {
		return NAME;
	}
	
	public String toString() {
		return getSuit() + ": " + getName() + "...value is: " + getValue(); 
	}
	

}