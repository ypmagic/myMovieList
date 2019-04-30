package recommend;

import java.util.List;

import movie.Movie;

public class MoviesByGenre {
  
  private String genre;
  private List<Movie> movies;

  public MoviesByGenre(String genre, List<Movie> movies) {
    this.genre = genre;
    this.movies = movies;
  }
  
  public String getGenre() {
    return this.genre;
  }
  
  public List<Movie> getMovies() {
    return this.movies;
  }
}
