package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Handles queries into the movie database and inserts retrieved movies
 * into the database
 * @author ypark29
 */
public final class DatabaseHandler {
  
  private static DatabaseHandler movie;
  private static Connection conn;

  private DatabaseHandler() {
  }

  /**
   * Creates a table for movies.
   */
  private static void makeMoviesTable() {
    // if the movies table does not exist, create a movies table
    String movieTable = "CREATE TABLE IF NOT EXISTS movies ("
        + " imdbId TEXT,"
        + " title TEXT,"
        + " year INTEGER,"
        + " rated TEXT,"
        + " runTime TEXT,"
        + " plot TEXT,"
        + " awards TEXT,"
        + " imdbRating REAL,"
        + " imdbVotes TEXT,"
        + " PRIMARY KEY(\"imdbId\")"
        + ");";
    try {
      PreparedStatement moviePrep = conn.prepareStatement(movieTable);
      moviePrep.executeUpdate();
      moviePrep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Creating movie table failed.");
    }
  }

  /**
   * Creates a table for users.
   */
  private static void makeUserTable() {
    String query = "CREATE TABLE IF NOT EXISTS users ("
            + " login TEXT,"
            + " password TEXT,"
            + " PRIMARY KEY(\"login\"));";
    try {
      PreparedStatement prep = conn.prepareStatement(query);
      prep.executeUpdate();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Creating user table failed.");
    }
  }

  /**
   * Creates a table for movies users have seen.
   */
  private static void makeUserMoviesTable() {
    String query = "CREATE TABLE IF NOT EXISTS userMovies ("
            + " login TEXT,"
            + " imdbId TEXT);"
            + " FOREIGN KEY (\"login\") REFERENCES users(login))";
    try {
      PreparedStatement prep = conn.prepareStatement(query);
      prep.executeUpdate();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Creating user movie table failed.");
    }
  }

  /**
   * Creates a table for genres.
   */
  private static void makeGenreTable() {
    String query = "CREATE TABLE IF NOT EXISTS genres ("
            + " genreId INTEGER,"
            + " genre TEXT,"
            + " PRIMARY KEY(\"genreId\"));";
    try {
      PreparedStatement prep = conn.prepareStatement(query);
      prep.executeUpdate();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Creating genre table failed.");
    }
  }
  
  private static void makeMovieGenreTable() {
    String query = "CREATE TABLE IF NOT EXISTS genreMovies ("
        + " movieId TEXT,"
        + " genreId INTEGER);";
    try {
      PreparedStatement prep = conn.prepareStatement(query);
      prep.executeUpdate();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Creating movie genre table failed.");
    }
  }
  
  private static void makeListTable() {
	  String query = "CREATE TABLE IF NOT EXISTS lists ("
	  		+ " id INTEGER,"
	  		+ " curator TEXT,"
	  		+ " name TEXT,"
	  		+ " PRIMARY KEY (\"id\"),"
	  		+ " FOREIGN KEY (\"curator\") REFERENCES users(login));";
	  try {
		  PreparedStatement prep = conn.prepareStatement(query);
		  prep.execute();
		  prep.close();
	  } catch(SQLException e) {
		  System.out.println("ERROR: Creating lists table failed.");
	  }
  }

  private static void makeListMoviesTable() {
    String query = "CREATE TABLE IF NOT EXISTS listMovies ("
            + " listId INTEGER,"
            + " imdbId TEXT);"
            + " FOREIGN KEY (\"listId\") REFERENCES lists(id));";
    try {
      PreparedStatement prep = conn.prepareStatement(query);
      prep.executeUpdate();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Creating user movie table failed.");
    }
  }

  /**
   * Static method that will initialize the database handler.
   * @return Database handler
   */
  public static DatabaseHandler getDatabaseHandler() {
    if (DatabaseHandler.movie == null) {
      DatabaseHandler.movie = new DatabaseHandler();
      try {
        // this line loads the driver manager class, and must be
        // present for everything else to work properly
        Class.forName("org.sqlite.JDBC");
        String urlToDB = "jdbc:sqlite:data/movies.sqlite3";
        DatabaseHandler.conn = DriverManager.getConnection(urlToDB);
        // these two lines tell the database to enforce foreign
        // keys during operations, and should be present
        Statement stat = conn.createStatement();
        stat.executeUpdate("PRAGMA foreign_keys = ON;");
      } catch (SQLException | ClassNotFoundException e) {
        System.out.println("ERROR: Found no such database.");
      }
      makeMoviesTable();
      makeUserTable();
      makeUserMoviesTable();
      makeGenreTable();
      makeMovieGenreTable();
      makeListTable();
      makeListMoviesTable();
    }
    return DatabaseHandler.movie;
  }
  
  public Connection getConnection() {
    return conn;
  }
}
