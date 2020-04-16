package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import project.Card.Suit;
import project.Card.Value;

/**
 * @author megha,2020
 */
public class Game 
{
	private Player[] players;// the players of the game
	private ArrayList<Card> gameDeck;//the game deck
	private boolean aceIsHigh;//if aces are high or low
	private int round;

	public Game()
	{
		gameDeck = new ArrayList<>();
		startGame();
	}//game method

	private void startGame() {
		//create the deck
		makeDeck();

		//create the players
		createPlayers();

		//ask about ace
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		String response = "";
		
		//set round to 1
		round = 1;

		while(!valid)
		{
			System.out.println("What do you want the ace card to be?\nHigh or low?");
			response = in.next();

			if(response.equalsIgnoreCase("high")) {
				aceIsHigh = true;
				valid = true;
			}
			else if(response.equalsIgnoreCase("low")) {
				aceIsHigh = false;
				valid = true;
			}
			else System.out.println("Please enter high or low.");
			//end if
		}//end while



		//shuffle the deck
		System.out.println("Ok now lets shuffle the deck.");
		Collections.shuffle(gameDeck);

		//deal the cards
		System.out.println("Dealing the cards...");
		dealCards();
		for(Player player : players) player.restock();
		//end for players

		System.out.println("Ok the game is ready to start. Here we go!");
		System.out.println("");
		System.out.println("");

		//start the game
		play();
	}//startGame method

	private void makeDeck() {
		for(Suit suit : Suit.values()) {
			for(Value value : Value.values()) {
				gameDeck.add(new Card(suit, value));
			}//end for Value.values()
		}//end for Suit.values()
	}//makeDeck method

	private void dealCards() {
		int playerIndex = 0;

		while(!gameDeck.isEmpty()) {
			gameDeck.get(0).setOwner(players[playerIndex]);
			players[playerIndex++].gainCard(gameDeck.remove(0));

			if(playerIndex > players.length - 1) playerIndex = 0;
			//end if
		}//end while
	}//dealCards method

	private void createPlayers() {
		System.out.println("Welcome to War! There can only be four players sadly. Lets start!");
		Scanner in = new Scanner(System.in);

		System.out.println("How many players will be playing?");

		boolean valid = false;
		int num = 0;

		while(!valid) {
			try {
				num = in.nextInt();

				if(num < 5 && num > 1) valid = true;
				else System.out.println("Sorry you need to put a number from 2 - 4.");
				//end if

			}catch(Exception e) {
				System.out.println("Sorry you need to put a number from 2 - 4.");
			}//end try catch
		}//end while

		players = new Player[num];

		System.out.println("Ok. Lets get your names!");

		valid = false;
		String name = "";

		for(int i = 0; i < players.length; i++) {
			while(!valid) {
				System.out.println("Player "+ (i + 1) + " what is your name?");
				try {
					name = in.nextLine();

					if(!name.equals("")) valid = true;
					else System.out.println("Sorry you need to put a name in.");
					//end if

				}catch(Exception e) {
					System.out.println("Sorry you need to put a name in.");
				}//end try catch

				players[i] = new Player(name);				
			}//do while
			valid = false;
			name = "";
		}//end for i

		System.out.print("ok so your names are");
		for(Player player : players) System.out.print(" " + player.getPlayerID());
		//end for players

		System.out.println(".");
	}//createPlayers method

	public Player[] getPlayers() 
	{
		return players;
	}//getPlayers method

	public void setPlayers(Player[] players) 
	{
		this.players = players;
	}//setPlayers method


	public  void play(){
		boolean valid = false;
		Player roundWinner = new Player();

		while(!valid) {
			System.out.println("Enter start when you are ready to start the round.");
			Scanner in = new Scanner(System.in);

			String start = in.next();

			if(start.equalsIgnoreCase("start")) valid = true;
			//end if
		}//end while
		
		System.out.println("Starting round: " + round++ + "\n");
		int activePlayers = 0;

		for(Player player : players) {

			//check if they are still valid
			if(player.isValidPlayer()) {
				activePlayers++;

				//get the card they are playing
				Card temp = player.playCard();

				//make sure they returned a card
				if(temp != null) {
					//they did return a card
					System.out.println(player.getPlayerID() + " plays the " + temp);
					gameDeck.add(temp);
				}
				else {
					//they didnt return a card. they are out
					activePlayers--;
					System.out.println(player.getPlayerID() + " has no more cards to play! They are out!");
				}//end if
			}//end if
		}//end for players

		if(activePlayers == 1) {
			for(Player player : players) {
				if(player.isValidPlayer()) {
					declareWinner(player);
				}//end if
			}//end for players
		}
		else roundWinner = checkCards(gameDeck).getOwner();
		//end if

		System.out.println(roundWinner.getPlayerID() + " won this round! On to the next one!");

		//give the cards to the winner
		for(Card card : gameDeck) {
			roundWinner.gainCard(card);
		}//end for gameDeck

		gameDeck.clear();

		//play next round
		play();
	}//play method

	public  void declareWinner(Player player){
		System.out.println(player.getPlayerID() + " won the game! Good job!\nDo you want to play again?\nYes or no");
		Scanner in = new Scanner(System.in);
		String again = in.nextLine();

		if(again.equalsIgnoreCase("yes")) startGame();
		//end if
	}//declareWinner method

	public Card checkCards(ArrayList<Card> cards){
		Card winningCard = cards.get(0);
		boolean tie = false;

		for(int i = 1; i < cards.size(); i++) {
			int val = winningCard.compareTo(cards.get(i), aceIsHigh);

			if(val == 1) {
				winningCard = cards.get(i);
				tie = false;
			}
			else if(val == 0) tie = true;
			//end if
		}//end for i

		if(tie) {
			System.out.println("There was a tie! lets continue this!\n");
			ArrayList<Player> tiedPlayers = new ArrayList<>();

			for(int i = 0; i < cards.size(); i++) {	

				int val = winningCard.compareTo(cards.get(i), aceIsHigh);

				if(val == 0) tiedPlayers.add(cards.get(i).getOwner());
				//end if
			}//end for i
			
			int cardsToDeal = 4;

			ArrayList<Card> newCards = new ArrayList<>();

			for(Player player : tiedPlayers) {
				if(player.cardCount() < cardsToDeal) cardsToDeal = player.cardCount();
				//end if
			}//end for tiedPlayers

			//get the cards for checking
			for(Player player : tiedPlayers) {
				newCards.add(player.playCard());
				System.out.println(player.getPlayerID() + " played the " + newCards.get(newCards.size() - 1));
			}//end for tiedPlayers

			//get the rest of the hidden cards
			for(Player player : tiedPlayers) {
				for(int x = 1; x < cardsToDeal; x++) {
					newCards.add(player.playCard());
				}//end for x
			}//end for tiedPlayers

			ArrayList<Card> toCheck = new ArrayList<>();
			int index = 0;

			for(Player player : tiedPlayers) toCheck.add(newCards.get(index++));
			//end for tiedPlayers

			winningCard = checkCards(toCheck);
			cards.addAll(newCards);
		}//end if
		
		return winningCard;
	}//checkCards method
}//class
