package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import movie.Movie;

/**
 * Handles queries into the movie database and inserts retrieved movies
 * into the database
 * @author ypark29
 */
public final class DatabaseHandler {
  
  private static DatabaseHandler movie;
  private static Connection conn;

  private DatabaseHandler() {
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
      return;
    }
    this.makeMoviesTable();
    this.makeUserTable();
    this.makeUserMoviesTable();
  }

  /**
   * Creates a table for movies.
   */
  private void makeMoviesTable() {
    // if the movies table does not exist, create a movies table
    String query = "CREATE TABLE IF NOT EXISTS movies ("
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
      PreparedStatement prep = conn.prepareStatement(query);
      prep.execute();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Creating movie table failed.");
    }
  }

  /**
   * Creates a table for users.
   */
  private void makeUserTable() {
    String query = "CREATE TABLE IF NOT EXISTS users ("
            + " login TEXT,"
            + " password TEXT,"
            + " PRIMARY KEY(\"login\"));";
    try {
      PreparedStatement prep = conn.prepareStatement(query);
      prep.execute();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Creating user table failed.");
    }
  }

  /**
   * Creates a table for movies users have seen.
   */
  private void makeUserMoviesTable() {
    String query = "CREATE TABLE IF NOT EXISTS userMovies ("
            + " login TEXT,"
            + " imdbId TEXT);";
    try {
      PreparedStatement prep = conn.prepareStatement(query);
      prep.execute();
      prep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Creating user table failed.");
    }
  }

  /**
   * Static method that will initialize the database handler.
   * @return Database handler
   */
  public static DatabaseHandler getDatabaseHandler() {
    if (DatabaseHandler.movie == null) {
      DatabaseHandler.movie = new DatabaseHandler();
    }
    return DatabaseHandler.movie;
  }
  
  /**
   * This query method adds a new movie into the database.
   * @return List of street names
   */
  public static List<String> insertNewMovie(Movie m) {
    // stuff here
    return new ArrayList<String>();
  }

}
