package Text;

import java.util.ArrayList;
import java.util.Scanner;

import codes.Dealer;
import codes.Deck;
import codes.Hand;
import codes.HumanPlayer;
import codes.Player;
import javafx.application.Application;
import javafx.stage.Stage;


public class TextApp extends Application {
	
	
	public static void playRound(ArrayList<Player> players, Dealer dealer, Menu menu ) throws Exception {

		//players are playing
		for(Player player : players) {
			if(player.getPlay()) {
				System.out.println("\n"+player.getName()+" is playing...\n");
				System.out.println("First Card: "+player.getFirstCard());
				System.out.println("Second Card: "+player.getSecondCard());
				System.out.println("Your hand total value: " + player.getHands().get(0).getHandValue());
				if(player.canSplit()) {
					if(menu.askSplit(player)) {
						player.splitHand();
						System.out.println("Your split hand: " + player.getHands().get(1).getHand());
						player.splitHandBet(menu.askSplitBet(player));
						
					}
				}
			for(Hand h : player.getHands()) {
				boolean stand = false;
				while(stand == false) {
					if(h.getHand().size() >= 2) {
						if(h.canDouble()) {
							if(menu.askDouble(player)) {
								h.doubleDown();
							}
						}
						if(menu.askHitOrStand()) {
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
			}
		}
		
		System.out.println("Dealer is now playing...\n");
		System.out.println("Dealer's first Card: " + dealer.getFirstCard());
		System.out.println("Dealer's second Card: " + dealer.getSecondCard());
		dealer.playNow();
		System.out.println("Dealer's Hand: " + dealer.getHand());
		System.out.println("Dealer's Hand value:"+dealer.getHandValue());
		
		for(Player player : players) {
			double wonOrLostAmount = 0;
			if(player.getPlay()) {
				for(Hand h : player.getHands()) {
					if(h.getHandValue() == dealer.getHandValue() ) {
						wonOrLostAmount += 0;
					}
					else if ((h.getHandValue() > dealer.getHandValue() && h.getHandValue() <= 21) 
							|| (dealer.getHandValue() > 21 && h.getHandValue() < 22)) {
						h.win();
						wonOrLostAmount += h.getBetAmount();
					}
					else {
						h.lose();
						wonOrLostAmount -= h.getBetAmount();
					}
				}
				if (wonOrLostAmount == 0) {
					System.out.println(player.getName() + " you did not win or lose money.");
				}
				else if (wonOrLostAmount > 0) {
					System.out.println(player.getName() + " you won $"+ wonOrLostAmount);
				}
				else if (wonOrLostAmount < 0) {
					System.out.println(player.getName() + " you lost $"+ (-1*wonOrLostAmount));
				}
				
			}
			System.out.println(player.toString());
			player.clearHands();
		}
		
		dealer.clearHand();
		
	}
	
	
	public static void main(String []args) throws Exception{
	//setup game
	ArrayList<Player> players = new ArrayList<Player>();
	Menu menu = new Menu();
	menu.welcome();
	Deck deck = new Deck(menu.askDeckNumber());
	Dealer dealer = new Dealer(deck);
	int numPlayers = menu.askTotalPlayer();
	for(int i = 0; i < numPlayers; i++) {
		System.out.print("\nPlayer" + (i+1) + " ");
		players.add(new HumanPlayer(deck, menu.askName(), menu.askMoney()));
	}
	
	//starting game
	System.out.println("\nGame starting...\n");
	
	
	//setup round
	boolean cont = true;
	//asking if fold or no
	//if they do play: setup the hands, ask for initial bet
	while (cont) {
		dealer.setup();
		for(Player player : players) {
			player.playOrNo(menu.askPlay(player));
			if(player.getPlay()) {
				player.setup();
				player.initialBet(menu.askInitialBet(player));
			}
		}
		
		//dealing out the cards
		//player first cards
		for(Player player : players) {
			if(player.getPlay()) {
				player.drawCard();
			}
		}
		//dealer first card
		System.out.println("\nDealer first drew: "+dealer.drawCard());
		//player second cards
		for(Player player : players) {
			if(player.getPlay()) {
				player.drawCard();
			}
		}
		//dealer second card
		System.out.println("Dealer Second card is hidden.\n");
		dealer.drawCard();
		
		playRound(players, dealer, menu);
		
		System.out.println("USED CARDS: \n");
		deck.getUsedDeckInfo();
		
		//check if you need to reshuffle the deck
		if(deck.getPercentageOfUsed() > 50) {
			System.out.println("More than 50% of the deck was used.\nReshuffling deck...\n");
			deck.resetDeck();
		}
		
		if(menu.askQuit()) {
			cont = false;
		}
		
		}
	}


	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}