package movieTests;

import movie.MovieList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MovieListTests {

  private MovieList movl;
  private List<String> movies;

  @Before
  public void setup() {
    movies = new ArrayList<>(Arrays.asList("Poltergeist", "The Exorcist"));
    movl = new MovieList(1, "elliot", "horror", movies);
  }

  @Test
  public void testGetID() {
    assert(movl.getId() == 1);
  }

  @Test
  public void testGetCurator() {
    assert(movl.getCurator().equals("elliot"));
  }

  @Test
  public void testGetName() {
    assert(movl.getName().equals("horror"));
  }

  @Test
  public void testGetMovies() {
    assert(movl.getMovies().equals(movies));
  }
}
