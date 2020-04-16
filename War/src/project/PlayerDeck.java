

package project;

import java.util.ArrayList;

/** This class is the deck for the player
 *
 * @author Jeremy Laing
 */
public class PlayerDeck extends GroupOfCards{

	private GroupOfCards discards;

	public PlayerDeck() {
		discards = new GroupOfCards();
	}

	public boolean restock(){
		if(discards.isEmpty()) {
			return false;
		}
		else {
			this.addCards(discards.showCards());
			discards.showCards().clear();
			this.shuffle();
			return true;
		}
	}

	public Card remove(int index){
		return cards.remove(0);
	}

	public GroupOfCards getDiscards() {
		return discards;
	}
}
