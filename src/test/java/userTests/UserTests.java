package userTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import user.User;

public class UserTests {
	@Test
	public void constructor() {
		User u = new User("dwang", "dwang");
		assertNotNull(u);
	}
	
	@Test
	public void authen() {
		User u = new User("daniel", "dwang");
		assertEquals(u.verifyPassword("dwang"), true);
		assertEquals(u.verifyPassword("dnlwng"), false);
	}
	
	@Test
	public void getters() {
		User u = new User("daniel", "dwang");
		assertEquals(u.getLogin(), "daniel");
		assertEquals(u.getPassword(), "dwang");
	}
}
