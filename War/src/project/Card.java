/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package project;

/**
 * @author megha, 2020
 */
public class Card 
{
    public enum Suit {HEARTS, DIAMONDS, SPADES, CLUBS};
    public enum Value {ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING}

    private Suit suit;
    private Value value;
    private Player owner;
    
    public Card() {
    	suit = null;
    	value = null;
    }
    
    public Card(Suit suit, Value value) {
    	this.suit = suit;
    	this.value = value;
    }

    @Override
    public String toString(){
        
        return value + " of " + suit;
    }

    public int compareTo(Card card, boolean aceIsHigh){
    	//return -1 if i am larger
    	//return 0 if we tie
    	//return 1 if i am smaller
    	
    	if(card.value.ordinal() > this.value.ordinal()) {
    		if(this.value.ordinal() == 0 && aceIsHigh == true) return -1;
    		return 1;
    	}
    	else if(card.value.ordinal() < this.value.ordinal()) {
    		if(card.value.ordinal() == 0 && aceIsHigh == true) return 1;
    		return -1;
    	}
        return 0;
    }

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	public void setOwner(Player player) {
		owner = player;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public boolean equals(Card card) {
		if(this.value.ordinal() == card.value.ordinal() && this.suit.ordinal() == card.suit.ordinal()) return true;
		//end if
		
		return false;
	}//equals method
    
}

