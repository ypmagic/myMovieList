package utilTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import util.Bigram;

public class BigramTests {
	@Test
	public void constructor() {
		Bigram<String, String> b = new Bigram("Hello", "World");
		assertNotNull(b);
	}
	
	@Test
	public void getters() {
		Bigram<String, Integer> b = new Bigram("Hello", new Integer(3));
		assertEquals(b.getLeft(), "Hello");
		assertEquals(b.getRight(), new Integer(3));
	}
}
