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
    String usersTable = "CREATE TABLE IF NOT EXISTS users ("
        + " userId INTEGER,"
        + " username TEXT,"
        + " pw TEXT,"
        + " PRIMARY KEY(\"userId\")"
        + ");";
    try {
      PreparedStatement moviePrep = conn.prepareStatement(movieTable);
      moviePrep.execute();
      moviePrep.close();
      PreparedStatement userPrep = conn.prepareStatement(usersTable);
      userPrep.execute();
      userPrep.close();
    } catch (SQLException e) {
      System.out.println("ERROR: Creating movie table failed.");
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
  
  public Connection getConnection() {
    return conn;
  }
}
