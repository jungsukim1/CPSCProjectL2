package codes;

import java.util.Scanner;

public class Menu {

	
	public void welcome() {
		System.out.println("Welcome to the game of BLACKJACK!"+ "\nSearch up the rules online and enjoy :)\n\n\n");
	}
	
	/**
	 * Asks the user how many players will be playing for the game. It has a limit of 7 players
	 * @return The total amount of players that will be in the game
	 */
	public int askTotalPlayer() {
		boolean playerDone = false;
		int PLAYER_NUMBER = 0;
		while (!playerDone) {
			try {
				System.out.print("How many players are playing? ");
				Scanner playerNumber = new Scanner(System.in);
				PLAYER_NUMBER = playerNumber.nextInt();
				if (PLAYER_NUMBER > 0 && PLAYER_NUMBER < 8) {
					System.out.print("We have " + PLAYER_NUMBER + " player(s) with us today\n");
					playerDone = true;
				}
				else
					throw new ArithmeticException ("Please enter a positive integer between 1-7\n");
			}
			catch(Exception e) {
				System.out.print("Please enter a positive integer between 1-7\n");
			}
		}
		return PLAYER_NUMBER;
	}
	/**
	 * Asks the user for how many decks will be used for the game of blackjack
	 * @return The total amount of decks that will be used
	 */
	public int askDeckNumber() {
		boolean deckDone = false;
		int DECK_NUMBER = 0;
		while (!deckDone) {
			try {
				System.out.print("How many decks are we using? ");
				Scanner deckNumber = new Scanner(System.in);
				DECK_NUMBER = deckNumber.nextInt();
				if (DECK_NUMBER > 0 && DECK_NUMBER != 3 && DECK_NUMBER < 9) {
					//System.out.print("The number of decks we are using is " + DECK_NUMBER + "\n");
					deckDone = true;	
				}
				else 
					throw new ArithmeticException ("Please enter a positive integer between 1-8 with an exception of 3");
			}
			catch(Exception e) {
				System.out.print("Please enter a positive integer between 1-8 with an exception of 3\n");
			}
		}
		return DECK_NUMBER;
	}
	/**
	 * Asks the user how much money they will be using for the game 
	 * @param Takes in a instance of a Player
	 * @return The total amount of money the Player will be playing with
	 */
	public int askMoney() {
		boolean moneyDone = false;
		int MONEY = 0;
		while (!moneyDone) {
			try {
				System.out.print("How much money are you going to be playing with? ");
				Scanner moneyAmount = new Scanner(System.in);
				MONEY = moneyAmount.nextInt();
				if (MONEY > 0) {
					System.out.print("You will playing with $" + MONEY + " today\n");
					moneyDone = true;
				}
				else 
					throw new ArithmeticException ("Please enter a positive integer \n");
			}
			catch(Exception e) {
				System.out.print("Please enter a positive integer \n");
		}
		}
		return MONEY;
	}
	/**
	 * Asks the user for the player's name
	 * @return The name of the player
	 */
	public String askName() {
		Scanner playerName = new Scanner(System.in);
		System.out.print("what is your name? ");
		String NAME = playerName.next();
		System.out.print("Nice to meet you " + NAME + "!\n");
		return NAME;
	}
	/**
	 * Asks the amount of human players that will be playing
	 * @param Takes in the total amount of players in the game
	 * @return Amount of human players
	 */
	
	public int askHumanPlayer(int PLAYER_NUMBER) {
		boolean humanDone = false;
		int HUMAN_NUMBER = 0;
		while (!humanDone) {
			try {
				System.out.print("How many human players are playing? ");
				Scanner humanNumber = new Scanner(System.in);
				HUMAN_NUMBER = humanNumber.nextInt();
				if (HUMAN_NUMBER > 0 && HUMAN_NUMBER < PLAYER_NUMBER) {
					System.out.print("We have " + HUMAN_NUMBER + " player(s) with us today\n");
					humanDone = true;
				}
				else
					throw new ArithmeticException ("Please enter a positive integar between 1-7 and less than the total amount of players\n");
			}
			catch(Exception e) {
				System.out.print("Please enter a positive integar between 1-7 and less than total amount of players\n");
			}
		}
		return HUMAN_NUMBER;
		
	}
	/**
	 * Asks for the amount the player is going to be betting for the round
	 * @param Takes in a instance of a Player
	 * @return amount of money that has been bet
	 */
	public int askInitialBet(Player player) {
		boolean betsDone = false;
		int BETS = 0;
		while (!betsDone) {
			try {
				System.out.print("How much money are you going to be betting for this round? ");
				Scanner betAmount = new Scanner(System.in);
				BETS = betAmount.nextInt();
				if (BETS > 0 && BETS < player.getMoney()) {
					System.out.print("You will be betting $" + BETS + " for this round\n");
					betsDone = true;
				}
				else 
					throw new ArithmeticException ("Please enter a positive integar");
			}
			catch(Exception e) {
				System.out.print("Please enter a positive integar");
		}
		}
		return BETS;
		
	}
	/**
	 * Asks if the player is going to be playing for the round or sit out the round
	 * @param Takes in the instance of a Player
	 * @return true if they want to keep playing and false if they want to sit the round
	 */
	public boolean askPlay(Player player) {
		boolean counter = false;
		boolean FOLD = false;
		String sit;
		while (!counter) {
			try {
				System.out.print("\n"+player.getName() + " would you like to play this round(yes or no)? ");
				Scanner fold1 = new Scanner(System.in);
				sit = fold1.next();
				//System.out.println(sit);
				if (sit.equals("yes")) {
					//System.out.println("You will be playing this round");
					counter = true;
					FOLD = true;					
				}
					else if(sit.equals("no")){
						//System.out.println("You will be sitting this round");
						counter = true;
						FOLD = false;					
				}
					else {
						throw new ArithmeticException ("Please enter yes or no");
					}
			}
				catch(Exception e) {
					System.out.println("Please enter yes or no");
				}
				
			}
		return FOLD;
		}
	/**
	 * Asks the player if they are going to quit the game
	 * @return true if quit and false if stay in game
	 */
	public boolean askQuit() {
		boolean counter = false;
		boolean QUIT = false;
		String leave;
		while (!counter) {
			try {
				System.out.print("\nWould you like to leave the game(yes or no)? ");
				Scanner quit1 = new Scanner(System.in);
				leave = quit1.next();
				if (leave.equals("yes")) {
					System.out.println("Thank you for playing");
					counter = true;
					QUIT = true;
				}
				else if (leave.equals("no")) {
					System.out.println("We will be continuing the game");
					counter = true;
					QUIT = false;
				}
				else {
					throw new ArithmeticException ("Please enter yes or no");
				}
			}
				catch(Exception e) {
					System.out.println("Please enter yes or no");
			}
			
		}
		return QUIT;
		
	}
	/**
	 * Asks the player if they are going to hit or stand
	 * @return true if hit and false if stand
	 */
	public boolean askHitOrStand() {
		boolean counter = false;
		boolean MOVES = false;
		String move;
		while (!counter) {
			try {
				System.out.print("Would you like to hit or stand? ");
				Scanner move1 = new Scanner(System.in);
				move = move1.next();
				if (move.equals("hit")) {
					//System.out.println("HIT");
					counter = true;
					MOVES = true;
				}
				else if (move.equals("stand")) {
					//System.out.println("STAND");
					counter = true;
					MOVES = false;
				}
				else {
					throw new ArithmeticException ("Please enter hit or stand");
				}
			}
			catch(Exception e) {
				System.out.println("Please enter hit or stand");
			}
		}
		return MOVES;
	}
	
	 
	//new ones from daniel
	
	public boolean askSplit(Player player) {
		boolean counter = false;
		boolean FOLD = false;
		String sit;
		while (!counter) {
			try {
				System.out.print(player.getName() + " would you like split your hand(yes or no)? ");
				Scanner fold1 = new Scanner(System.in);
				sit = fold1.next();
				//System.out.println(sit);
				if (sit.equals("yes")) {
					//System.out.println("You will be splitting your hand");
					counter = true;
					FOLD = true;					
				}
					else if(sit.equals("no")){
						//System.out.println("You will not be splitting your hand");
						counter = true;
						FOLD = false;					
				}
					else {
						throw new ArithmeticException ("Please enter yes or no");
					}
			}
				catch(Exception e) {
					System.out.println("Please enter yes or no");
				}
				
			}
		return FOLD;
		}
	
	public int askSplitBet(Player player) {
		boolean betsDone = false;
		String name = player.getName();
		int BETS = 0;
		while (!betsDone) {
			try {
				System.out.print(name + ". How much money are you going to be betting on your split hand? ");
				Scanner betAmount = new Scanner(System.in);
				BETS = betAmount.nextInt();
				if (BETS > 0 && BETS < player.getMoney()) {
					System.out.print("You will be betting $" + BETS + " for this hand\n");
					betsDone = true;
				}
				else 
					throw new ArithmeticException ("Please enter a positive integar");
			}
			catch(Exception e) {
				System.out.print("Please enter a positive integar");
		}
		}
		return BETS;
		
	}
	
	public boolean askDouble(Player player) {
		boolean counter = false;
		boolean FOLD = false;
		String sit;
		while (!counter) {
			try {
				System.out.print(player.getName() + " would you like double down your hand(yes or no)? ");
				Scanner fold1 = new Scanner(System.in);
				sit = fold1.next();
				//System.out.println(sit);
				if (sit.equals("yes")) {
					//System.out.println("You will double down your hand");
					counter = true;
					FOLD = true;					
				}
					else if(sit.equals("no")){
						//System.out.println("You will not double down your hand");
						counter = true;
						FOLD = false;					
				}
					else {
						throw new ArithmeticException ("Please enter yes or no");
					}
			}
				catch(Exception e) {
					System.out.println("Please enter yes or no");
				}
				
			}
		return FOLD;
		}
	
}
