package codes;

import java.util.ArrayList;

import javafx.scene.image.Image;

public class Card {
	private String NAME = "";
	private ArrayList<Integer> VALUE = new ArrayList<Integer>();
	private String SUIT = "";
	private Image image;
	
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
		
		String filename = name + "_of_" + suit + ".png";
		image = new Image("/Cards/"+filename);
		
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
	
	public Image getCardImage() {
		return image;
	}
	
	public String toString() {
		return getSuit() + ": " + getName() + "...value is: " + getValue(); 
	}
	

}