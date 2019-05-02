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
  
  public static MoviesByGenre moviesByGenre(int genre) {
    MoviesByGenre moviesAndGenre = null;
    // get the corresponding genre and run script on command line
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
}
