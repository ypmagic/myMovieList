package recommend;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableMap;

import database.DatabaseHandler;
import database.DatabaseQuery;
import filereader.InputFileReader;
import movie.Movie;

public final class Recommender {
  
  private static final Map<Object, Object> genres = new ImmutableMap.Builder<>()
      .put(0, "Action")
      .put(1, "Adventure")
      .put(2, "Animation")
      .put(3, "Children")
      .put(4, "Comedy")
      .put(5, "Crime")
      .put(6, "Documentary")
      .put(7, "Drama")
      .put(8, "Fantasy")
      .put(9, "Film-Noir")
      .put(10, "Horror")
      .put(11, "Musical")
      .put(12, "Mystery")
      .put(13, "Romance")
      .put(14, "Sci-Fi")
      .put(15, "Thriller")
      .put(16, "War")
      .put(17, "Western")
      .build();
  
  public static MoviesByGenre moviesByGenre() {
    // first get a random genre
    Random r = new Random();
    int random = r.nextInt(18);
    // get the corresponding genre and run script on command line
    String randomGenre = genres.get(random).toString();
    try {
      Process p = Runtime.getRuntime().exec("python3 src/main/java/recommend/sorting.py \"" 
          + randomGenre + "\"");
    } catch (IOException e) {
      System.out.println("ERROR: Something wrong with executing script.");
    }
    // now use buffered reader to read in the imdb id's of all
    // recommended movies by genre
    InputFileReader reader = new InputFileReader("src/main/java/recommend/"
        + "sorted.txt");
    List<List<String>> data = reader.read("\n");
    List<List<String>> five = data.stream()
        .limit(5)
        .collect(Collectors.toList());
    List<String> newData = new ArrayList<>();
    // go through data and add the tt0 in the beginning of each id
    for (List<String> line : five) {
      // there is only one thing in each line
      String imdbId = line.get(0);
      // add an edited id to the new data list
      int add = 7 - imdbId.length();
      String finalId = "tt";
      for (int i = 0; i < add; i++) {
        finalId += "0";
      }
      finalId += imdbId;
      newData.add(finalId);
    }
    // go through the new data list and get the relevant movie
    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
    List<Movie> movies = new ArrayList<>();
    for (String id : newData) {
      Movie m = DatabaseQuery.getMovie(conn, id);
      movies.add(m);
    }
    // finally return the list of movies
    MoviesByGenre moviesAndGenre = new MoviesByGenre(randomGenre, movies);
    return moviesAndGenre;
  }
  
}
