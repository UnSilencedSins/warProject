package project;

import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 * @author megha,2020
 */
public class Player 
{
	private String playerID; //the unique ID for this player
	private PlayerDeck deck;
	private boolean validPlayer;

	/**
	 * A constructor that allows you to set the player's unique ID
	 * @param name the unique ID to assign to this player.
	 */
	public Player(String name)
	{
		playerID= name;
		validPlayer = true;
		deck = new PlayerDeck();
	}

	public Player() {
		validPlayer = false;
		deck = new PlayerDeck();
	}

	/**
	 * @return the playerID
	 */
	public String getPlayerID() 
	{
		return playerID;
	}

	/**
	 * Ensure that the playerID is unique
	 * @param givenID the playerID to set
	 */
	public void setPlayerID(String givenID) 
	{
		playerID = givenID;
	}

	/**
	 * The method to be instantiated when you subclass the Player class
	 * with your specific type of Player and filled in with logic to play your game.
	 */
	public Card playCard(){
		if(!deck.isEmpty()) return deck.remove(0);
		else {
			restock();
			if(deck.isEmpty()) {
				validPlayer = false;
				return null;
			}
			else return deck.remove(0);
		}
	}

	public void setDeck(PlayerDeck deck){
		this.deck = deck;
	}

	public ArrayList<Card> getCards() {
		return deck.showCards();
	}


	public void gainCard(Card card) {
		card.setOwner(this);
		deck.getDiscards().addCard(card);
	}

	public void restock() {
		deck.restock();
	}

	public boolean isValidPlayer() {
		return validPlayer;
	}
	
	public int cardCount() {
		return getCards().size() + deck.getDiscards().showCards().size();
	}

}
