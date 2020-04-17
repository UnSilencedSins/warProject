package war1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GroupOfCardsTests extends GroupOfCards {

	@Test
	void testIsEmpty1() {
		assertEquals(true, isEmpty()); //Good Test. Will return Successful
	}
	
	@Test
	void testIsEmpty2() {
		assertEquals(false, isEmpty()); //Bad Test. Will return failure
	}
	
	@Test
	void testIsEmpty3() {
		Card test = new Card();//This test adds a card to the cards ArrayList and ensure that the isEmpty Method still returns correctly
		cards.add(test);
		System.out.println("Test Card added");
		assertEquals(false, isEmpty());
	}

}
