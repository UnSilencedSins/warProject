package war1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class PlayerTests extends Player {
	

	@Test
	void testIsValidPlayer1() {
		assertEquals(false, isValidPlayer()); //Good Test. Will return successful
	}
	
	@Test
	void testIsValidPlayer2() {
		assertEquals(true, isValidPlayer()); //Bad Test. Will return failure
	}
	
	@Test
	void testCardCount1() {
		assertEquals(0, cardCount());//Good Test. Will return Successful
	}
	
	@Test
	void testCardCount2() {
		assertEquals(100, cardCount()); // Bad Test. Will return Failure
	}
	
	@Test 
	void testCardCount3() {
		assertEquals(.01, cardCount());// Test should fail. Testing an exception but looking for a double in an int
	}

}
