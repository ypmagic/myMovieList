package recommend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import database.DatabaseHandler;
import database.DatabaseQuery;
import movie.Movie;

public final class Recommender {
  
  private static final Map<Object, Object> genres = new ImmutableMap.Builder<>()
      .put(0, "Action")
      .put(1, "Adventure")
      .put(2, "Animation")
      .put(3, "Children")
      .put(4, "Comedy")
      .put(5, "Crime")
      .put(6, "Drama")
      .put(7, "Fantasy")
      .put(8, "Mystery")
      .put(9, "Romance")
      .put(10, "Sci-Fi")
      .put(11, "Thriller")
      .put(12, "War")
      .build();
  
  public static MoviesByGenre moviesByGenre(int genre) {
    MoviesByGenre moviesAndGenre = null;
    // get the correspondiwng genre and run script on command line
    String randomGenre = genres.get(genre).toString();
    try {
      // list of data imdb ids
      List<String> data = new ArrayList<>();
      ProcessBuilder pr = new ProcessBuilder("/Library/Frameworks/Python.framework/Versions/3.6/bin/python3", "src/"
          + "main/java/sorter/sorting.py", randomGenre);
      Process p = pr.start();
      p.waitFor();
      InputStream is = p.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String imdbId = null;
      while ((imdbId = reader.readLine()) != null) {
        int add = 7 - imdbId.length();
        String finalId = "tt";
        for (int i = 0; i < add; i++) {
          finalId += "0";
        }
        finalId += imdbId;
        if (!finalId.equals("tt000None")) {
          data.add(finalId);
        }
      }
      // go through the new data list and get the relevant movie
      Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
      List<Movie> movies = new ArrayList<>();
      System.out.println(data.size());
      for (String id : data) {
        System.out.println(id);
        Movie m = DatabaseQuery.getMovie(conn, id);
        movies.add(m);
        System.out.println(m.toString());
      }
      // finally return the list of movies
      moviesAndGenre = new MoviesByGenre(randomGenre, movies);
    } catch (IOException | InterruptedException e) {
      System.out.println("ERROR: Something wrong with executing script.");
    }
    return moviesAndGenre;
  }
  
  public static List<Movie> recommend(List<String> inputMovies, 
      List<Integer> ratings, String genre) {
	  System.out.println("should be recommending");
    String inputMoviesString = inputMovies.toString();
    inputMoviesString = inputMoviesString.substring(1, 
        inputMoviesString.length() - 1);
    String ratingsString = ratings.toString();
    ratingsString = ratingsString.substring(1,
        ratingsString.length() - 1);
    ProcessBuilder pr = new ProcessBuilder("/Library/Frameworks/Python.framework/Versions/3.6/bin/python3", "src/"
        + "main/java/recommend/recommend.py", inputMoviesString,
        ratingsString, genre);
    List<String> data = new ArrayList<>();
    List<Movie> movies = new ArrayList<>();
    try {
      Process p = pr.start();
      p.waitFor();
      InputStream is = p.getInputStream();
      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
      String imdbId = null;
      while ((imdbId = reader.readLine()) != null) {
        int add = 7 - imdbId.length();
        String finalId = "tt";
        for (int i = 0; i < add; i++) {
          finalId += "0";
        }
        finalId += imdbId;
        if (!finalId.equals("tt000None")) {
          data.add(finalId);
        }
      }
      // go through the new data list and get the relevant movie
      System.out.println("DATA SIZE: " + data.size());
      Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
      //System.out.println(data.size());
      for (String id : data) {
        System.out.println(id);
        Movie m = DatabaseQuery.getMovie(conn, id);
        movies.add(m);
        System.out.println(m.toString());
      }
    } catch (IOException | InterruptedException e) {
      System.out.println("ERROR: Something wrong with executing script.");
    }
    return movies;
  }
}
