package war1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerDeckTests extends PlayerDeck {

	@Test
	void testRestock1() {
		assertEquals(false, restock());//Good Test. Should run successfully
	}
	
	@Test
	void testRestock2() {
		assertEquals(true, restock());//Bad Test. Should fail on run
	}
	
	@Test
	void testRestock3() {
		GroupOfCards testDiscard = new GroupOfCards();	//create a test discard that is the same as the normal discard
		testDiscard = getDiscards();
	
		if(testDiscard.isEmpty()) {//runs the test the same way that the restock method runs to ensure that it runs correctly
			assertEquals(false, restock());
		} else if (!testDiscard.isEmpty()) {
			assertEquals(true, restock());
		}
		
	}
}
