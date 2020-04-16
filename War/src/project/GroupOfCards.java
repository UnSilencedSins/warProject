/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game.
 * HINT, you might want to subclass this more than once.
 * The group of cards has a maximum size attribute which is flexible for reuse.
 * @author megha,2020
 */
public class GroupOfCards 
{
   
    //The group of cards, stored in an ArrayList
    protected ArrayList <Card> cards;
    
    public GroupOfCards() {
    	cards = new ArrayList<>();
    }
    /**
     * A method that will get the group of cards as an ArrayList
     * @return the group of cards.
     */
    public ArrayList<Card> showCards()
    {
        return cards;
    }
    
    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    public boolean isEmpty() {
    	return cards.isEmpty();
    }
    
    public void addCard(Card card){
       
            this.cards.add(card);
        
    }
    
    public void addCards(ArrayList<Card> cards){
      
            this.cards.addAll(cards);
        
    }
}//end class
