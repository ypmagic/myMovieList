package movieTests;

import movie.Movie;
import org.junit.Before;
import org.junit.Test;

public class MovieTests {

  private Movie movie;

  @Before
  public void setup() {
    movie = new Movie("mov1", "Harry Potter", 2010);
  }

  @Test
  public void testGetImdbID() {
    assert(movie.getImdbID().equals("mov1"));
  }

  @Test
  public void testGetTitle() {
    assert(movie.getTitle().equals("Harry Potter"));
  }

  @Test
  public void testGetYear() {
    assert(movie.getYear() == 2010);
  }
}
