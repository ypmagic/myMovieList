package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import movie.Movie;

public final class DatabaseQuery {
  
  /**
   * private constructor.
   */
  private DatabaseQuery() {
  }

  /**
   * This query method returns the specific movie
   * @param conn Database SQL connection
   * @return A movie object with all data from database
   */
  public static Movie getMovie(Connection conn, String movieId) {
    Movie m = null;
    String query = "SELECT * FROM movies WHERE imdbId = ?";
    try {
      PreparedStatement prep;
      prep = conn.prepareStatement(query);
      prep.setString(1, movieId);
      ResultSet rs = prep.executeQuery();
      if (rs.next()) {
        String title = rs.getString(2);
        int year = rs.getInt(3);
        String rated = rs.getString(4);
        String runTime = rs.getString(5);
        String plot = rs.getString(6);
        String awards = rs.getString(7);
        double imdbRating = rs.getDouble(8);
        String imdbVotes = rs.getString(9);
        // need to get genre from genres table
        // later implement this
        m = new Movie(movieId, title, year, rated, runTime, 
            new ArrayList<String>(),plot, awards, imdbRating, imdbVotes);
      }
      rs.close();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Something wrong with getting movie.");
    }
    return m;
  }
  
  /**
   * This query method returns the specific movie
   * @param conn Database SQL connection
   * @return A movie object with all data from database
   */
  public static void insertMovie(Connection conn, Movie m) {
    String query = "INSERT INTO movies VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    // getting specific movie data
    String imdbId = m.getImdbID();
    String title = m.getTitle();
    int year = m.getYear();
    String rated = m.getRated();
    String runTime = m.getRunTime();
    String plot = m.getPlot();
    String awards = m.getAwards();
    double imdbRating = m.getImdbRating();
    String imdbVotes = m.getImdbVotes();
    // TODO: EDIT THIS LATER BC WE NEED TO INSERT GENRE INTO GENRE TABLE!!!
    try {
      PreparedStatement prep;
      prep = conn.prepareStatement(query);
      prep.setString(1, imdbId);
      prep.setString(2, title);
      prep.setInt(3, year);
      prep.setString(4, rated);
      prep.setString(5, runTime);
      prep.setString(5, plot);
      prep.setString(6, awards);
      prep.setDouble(7, imdbRating);
      prep.setString(8, imdbVotes);
      prep.execute();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Something wrong with inserting movie.");
    }
  }

}
