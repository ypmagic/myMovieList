package edu.brown.cs.ap99dwang66ekang5ypark29.user;


import java.util.List;

import edu.brown.cs.ap99dwang66ekang5ypark29.movie.Movie;

public class User {

  private String password;
  private String login;
  private List<Movie> movies;

  public User(String login, String password) {
    this.password = password;
    this.login = login;
  }

  public Boolean verifyPassword(String attempt) {
    if (attempt.equals(this.password)) {
      return true;
    }
    return false;
  }

  public String getLogin() {
    return this.login;
  }

  public String getPassword() {
    return this.password;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }

  public void addMovie(Movie movie) {
    this.movies.add(movie);
  }
}
