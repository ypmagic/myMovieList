package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import api.MovieAPI;
import api.PosterAPI;
import movie.Movie;
import user.User;

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
        // get poster url
        String posterURL = PosterAPI.getImage(movieId);
        // set the movie temporarily without genres
        m = new Movie(movieId, title, year, rated, runTime, 
            new ArrayList<String>(), plot, awards, imdbRating, imdbVotes,
            posterURL);
      }
      rs.close();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Something wrong with getting movie.");
    }
    // if the movie does not exist in the database, use movie api
    if (m == null) {
      m = MovieAPI.searchById(movieId);
      insertMovie(conn, m);
    }
    // get all genres that this movie has
    List<String> genres = getGenres(conn, m.getImdbID());
    m.setGenre(genres);
    return m;
  }
  
  public static List<String> getGenres(Connection conn, String movieId) {
    // first get all the genre id's
    String query = "SELECT genreId FROM genreMovies WHERE movieId = ?";
    List<Integer> genreId = new ArrayList<>();
    try {
      PreparedStatement prep;
      prep = conn.prepareStatement(query);
      prep.setString(1, movieId);
      ResultSet rs = prep.executeQuery();
      while (rs.next()) {
        int genre = rs.getInt(1);
        genreId.add(genre);
      }
      rs.close();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Something wrong with getting genre IDs.");
    }
    // now get all the names of genres through genre table
    String query2 = "SELECT genre FROM genres WHERE genreId = ?;";
    List<String> genreNames = new ArrayList<>();
    for (int genre : genreId) {
      try {
        PreparedStatement prep;
        prep = conn.prepareStatement(query2);
        prep.setInt(1, genre);
        ResultSet rs = prep.executeQuery();
        while (rs.next()) {
          String genreName = rs.getString(1);
          genreNames.add(genreName);
        }
        rs.close();
        prep.close();
      } catch (SQLException e) {
        System.out.println("ERROR: Something wrong with getting genre names");
      }
    }
    return genreNames;
  }
  
  /**
   * This query method returns the specific movie
   * @param conn Database SQL connection
   * @return A movie object with all data from database
   */
  public static void insertMovie(Connection conn, Movie m) {
    String query = "INSERT INTO movies VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    String query2 = "INSERT INTO genreMovies VALUES (?, ?);";
    String query3 = "INSERT INTO genres VALUES (?, ?);";
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
    List<String> genres = m.getGenre();
    for (String genre : genres) {
      // unique id for each genre
      int hash = Objects.hashCode(genre);
      try {
        PreparedStatement prep;
        // insert each genre into the genre table
        if (!genreExists(conn, hash)) {
          prep = conn.prepareStatement(query3);
          prep.setInt(1, hash);
          prep.setString(2, genre);
          prep.execute();
          prep.close();
        }
        // associate each movie with genre in genreMovies table
        PreparedStatement prep2;
        prep2 = conn.prepareStatement(query2);
        prep2.setString(1, imdbId);
        prep2.setInt(2, hash);
        prep2.execute();
        prep2.close();
      } catch (SQLException e) {
        System.out.println("ERROR: Something wrong with inserting genres.");
      }
    }
    try {
      // insert each movie into the movies table
      PreparedStatement prep;
      prep = conn.prepareStatement(query);
      prep.setString(1, imdbId);
      prep.setString(2, title);
      prep.setInt(3, year);
      prep.setString(4, rated);
      prep.setString(5, runTime);
      prep.setString(6, plot);
      prep.setString(7, awards);
      prep.setDouble(8, imdbRating);
      prep.setString(9, imdbVotes);
      prep.execute();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Something wrong with inserting movie.");
    }
  }
  
  public static boolean genreExists(Connection conn, int hash) {
    boolean ret = true;
    String query = "SELECT COUNT(*) FROM genres WHERE genreId = ?;";
    try {
      PreparedStatement prep = conn.prepareStatement(query);
      prep.setInt(1, hash);
      ResultSet rs = prep.executeQuery();
      while(rs.next()) {
        int num = rs.getInt(1);
        if (num == 0) {
          ret = false;
        }
      }
      rs.close();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Counting genres failed.");
    }
    return ret;
  }

  public static Boolean validLogin(Connection conn, String login) {
    Boolean ret = false;
    String query = "SELECT COUNT(*) FROM users WHERE login = ?;";
    try {
      PreparedStatement prep = conn.prepareStatement(query);
      prep.setString(1, login);
      ResultSet rs = prep.executeQuery();
      while(rs.next()) {
        int num = rs.getInt(1);
        if (num == 0) {
          ret = true;
        }
      }
      rs.close();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Finding login failed.");
    }
    return ret;
  }

  public static Boolean authenticateUser(Connection conn, String login, String pw) {
      Boolean toReturn = false;
	  String query = "SELECT COUNT(*) FROM users WHERE login = ? AND password = ?;";
	  try {
	      PreparedStatement prep = conn.prepareStatement(query);
	      prep.setString(1, login);
	      prep.setString(2, pw);
	      ResultSet rs = prep.executeQuery();
	      while (rs.next()) {
	    	  if (rs.getInt(1) == 1) {
	    		  toReturn = true;
	    	  }
	      }
	      rs.close();
	      prep.close();
	  } catch (SQLException e) {
		  System.out.println("invalid credentials");
	  }
	  return toReturn;
  }
  
  public static void insertNewUser(Connection conn, User u) {
    String login = u.getLogin();
    String password = u.getPassword();

    String query = "INSERT INTO users VALUES (?, ?);";
    try {
      PreparedStatement prep = conn.prepareStatement(query);
      prep.setString(1, login);
      prep.setString(2, password);
      prep.executeUpdate();
      prep.close();
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("ERROR: Something wrong with inserting user.");
    }
  }
  
  public static String getListFromId(Connection conn, String id) {
	  String query = "SELECT movies FROM lists WHERE url = ?;";
	  try {
		  PreparedStatement prep = conn.prepareStatement(query);
		  prep.setString(1, id);
		  ResultSet rs = prep.executeQuery();
		  String toReturn = "";
		  while (rs.next()) {
			  toReturn = rs.getString(1);
		  }
		  return toReturn;
	  } catch (SQLException e) {
		  return "No list";
	  }
  }

//  public static void insertGenre(Connection conn, String genre) {
//    String query = "INSERT INTO genres VALUES(?, ?);";
//    try {
//      PreparedStatement prep = conn.prepareStatement(query);
//      int hash = Objects.hash(genre);
//      prep.setInt(1, hash);
//      prep.setString(2, genre);
//      prep.execute();
//      prep.close();
//    } catch (SQLException e) {
//      System.out.println("ERROR: Something wrong with inserting genre.");
//    }
//  }
}
