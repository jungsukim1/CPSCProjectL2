package data;

import java.io.IOException;

import codes.AiPlayer;
import codes.Dealer;
import codes.Deck;
import codes.Hand;
import javafx.application.Application;
import javafx.stage.Stage;

public class AiTester extends Application{

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CSVReader csv = new CSVReader("DS");
		Probability prob = new Probability(csv.getDOUBLESTAND());
		
		
		Deck deck = new Deck(2);
		Dealer dealer = new Dealer(deck);
		AiPlayer player = new AiPlayer(deck, "Jarvis", 1000000);
		
		player.setup();
		
		player.drawCard();
		System.out.println("Dealer first Card: " + dealer.drawCard());
		player.drawCard();
		System.out.println("Dealer second Card: " + dealer.drawCard());
		
		
		System.out.println("\n"+player.getName()+" is playing...\n");
		System.out.println("First Card: "+player.getFirstCard());
		System.out.println("Second Card: "+player.getSecondCard());
		System.out.println("Your hand total value: " + player.getHands().get(0).getHandValue());
		if(player.canSplit()) {
			if(prob.move(player.getHands().get(0), dealer).equals("SP")) {
				System.out.println("\t\t\tAI MOVE IS: "+ prob.move(player.getHands().get(0), dealer));
				player.splitHand();
				System.out.println("Your split hand: " + player.getHands().get(1).getHand());	
			}
		}

		for(Hand h : player.getHands()) {
			boolean stand = false;
			boolean doubled = true;
			while(stand == false) {
				if(h.getHand().size() >= 2) {
					if(h.canDouble()) {
						if(prob.move(h, dealer).equals("D") || prob.move(h, dealer).equals("Ds")) {
							System.out.println("\t\t\tAI MOVE IS: "+ prob.move(h, dealer));
							doubled = false;
							h.doubleDown();
							System.out.println("You hit: " + h.hit());
							System.out.println("Your hand: " + h.getHand());
							System.out.println("Your hand total value: " + h.getHandValue());
							if (h.getHandValue() > 21) {
								System.out.println("You Lost!");
								stand = true;
							}
							else if (h.getHandValue() == 21) {
								System.out.println("BLACKJACK!");
							}
						}
					}
					if(prob.move(h, dealer).equals("H") && doubled) {
						System.out.println("\t\t\tAI MOVE IS: "+ prob.move(h, dealer));
						System.out.println("You hit: " + h.hit());
						System.out.println("Your hand: " + h.getHand());
						System.out.println("Your hand total value: " + h.getHandValue());
						if (h.getHandValue() > 21) {
							System.out.println("You Lost!");
							stand = true;
						}
						else if (h.getHandValue() == 21) {
							System.out.println("BLACKJACK!");
							stand = true;
						}
					}
					else {
						System.out.println("\t\t\tAI MOVE IS: "+ prob.move(h, dealer));
						if(prob.move(h, dealer).equals("D")) {
							System.out.println("DOUBLE AND HIT");
							System.out.println("You hit: " + h.hit());
							System.out.println("Your hand: " + h.getHand());
							System.out.println("Your hand total value: " + h.getHandValue());
							if (h.getHandValue() > 21) {
								System.out.println("You Lost!");
								stand = true;
							}
							else if (h.getHandValue() == 21) {
								System.out.println("BLACKJACK!");
							}
						}
						
						stand = true;
					}
				}
				else if(h.getHand().size() == 1) {
					h.hit();
					System.out.println("Your hand now: "+h.getHand());
					System.out.println("Your hand total value: " + h.getHandValue());
				}
			}
		}
		
		System.out.println(player.toString());
		
		
		

		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
