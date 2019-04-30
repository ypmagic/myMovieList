package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import movie.Movie;

public class MovieAPI {
  
  private static final String url = "http://private.omdbapi.com/";
  private static final String key = "&apikey=5ac72dbc";
  private static final JsonParser parser = new JsonParser();
  
  public static void main(String[] args) {
    // testing search
    List<Movie> m = MovieAPI.search("Blade Runner");
    System.out.println(m.toString());
    System.out.println(m.size());
    // testing search by IMDB id
    Movie m1 = MovieAPI.searchById("tt0081505");
    System.out.println(m1.toString());
    // testing search by title
    Movie m2 = MovieAPI.searchByTitle("Toy Story");
    System.out.println(m2.toString());
    // test equality
    System.out.println(m1.equals(m2)); // true
    System.out.println(m.get(0).equals(m2)); // false
  }
  
  private static String sendRequest(String params) {
    URL finalURL = null;
    HttpURLConnection conn = null;
    StringBuffer response = new StringBuffer();
    try {
      finalURL = new URL(url + params + key);
    } catch (MalformedURLException e) {
      System.out.println("ERROR: Malformed URL.");
    }
    try {
      conn = (HttpURLConnection) finalURL.openConnection();
      BufferedReader in = new BufferedReader(
          new InputStreamReader(conn.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      in.close();
    } catch (IOException e) {
      System.out.println("ERROR: Connection refused.");
    }
    return response.toString();
  }
  
  public static List<Movie> search(String req) {
    // encoding url
    String encodedReq = null;
    try {
      encodedReq = URLEncoder.encode(req, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      System.out.println("ERROR: URL Encoding failed.");
    }
    List<Movie> movies = new ArrayList<>();
    int numResults = 0;
    double numPages = 0;
    // initial request to get the number of movies there are in this
    // search request
    String init = sendRequest("?s=" + encodedReq);
    JsonObject initRes = (JsonObject) parser.parse(init);
    // first check if there was a valid response
    if (initRes.get("Response").getAsString().equals("True")) {
      numResults = initRes.get("totalResults").getAsInt();
      // if there are more than 10 results, there must be additional pages
      if (numResults > 10) {
        numPages = Math.ceil(numResults / 10);
      }
      // add the current request's search results to the list of movies
      JsonArray results = initRes.getAsJsonArray("Search");
      for (JsonElement el : results) {
        JsonObject obj = (JsonObject) el;
        String imdbId = obj.get("imdbID").getAsString();
        String title = obj.get("Title").getAsString();
        String yearAsString = obj.get("Year").getAsString();
        String[] split = yearAsString.split("-");
        int year = Integer.parseInt(split[0]);
        Movie m = new Movie(imdbId, title, year);
        // adding the movie to the list
        movies.add(m);
      }
      // if we have more than one page, request those pages
      if (numPages > 1) {
        // looping through all the pages
        for (int i = 2; i <= numPages; i++) {
          String res = sendRequest("?s=" + encodedReq + "&page=" + i);
          JsonObject resObj = (JsonObject) parser.parse(res);
          JsonArray nextResults = resObj.getAsJsonArray("Search");
          // again, loop through the array of search results and add as movie
          for (JsonElement el : nextResults) {
            JsonObject obj = (JsonObject) el;
            String imdbId = obj.get("imdbID").getAsString();
            String title = obj.get("Title").getAsString();
            int year = obj.get("Year").getAsInt();
            Movie m = new Movie(imdbId, title, year);
            // adding the movie to the list
            movies.add(m);
          }
        }
      }
    }
    // finally, return the list of movies
    return movies;
  }
  
  public static Movie searchByTitle(String req) {
    // encoding url
    String encodedReq = null;
    try {
      encodedReq = URLEncoder.encode(req, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      System.out.println("ERROR: URL Encoding failed.");
    }
    Movie m = null;
    String res = sendRequest("?t=" + encodedReq);
    JsonObject resObj = (JsonObject) parser.parse(res);
    // check if there was a valid response
    if (resObj.get("Response").getAsString().equals("True")) {
      String imdbId = resObj.get("imdbID").getAsString();
      String title = resObj.get("Title").getAsString();
      String yearAsString = resObj.get("Year").getAsString();
      String[] split = yearAsString.split("-");
      int year = Integer.parseInt(split[0]);
      String rated = resObj.get("Rated").getAsString();
      String runTime = resObj.get("Runtime").getAsString();
      String genre = resObj.get("Genre").getAsString();
      // split genre into an array and create a list from it
      List<String> genreList = Arrays.asList(genre.split(", "));
      String plot = resObj.get("Plot").getAsString();
      String awards = resObj.get("Awards").getAsString();
      double imdbRating = resObj.get("imdbRating").getAsDouble();
      String imdbVotes = resObj.get("imdbVotes").getAsString();
      // get poster url
      String posterURL = PosterAPI.getImage(imdbId);
      // creating a new movie from this
      m = new Movie(imdbId, title, year, rated, runTime, genreList, plot,
          awards, imdbRating, imdbVotes, posterURL);
    }
    return m;
  }
  
  public static Movie searchById(String req) {
    Movie m = null;
    String res = sendRequest("?i=" + req);
    JsonObject resObj = (JsonObject) parser.parse(res);
    // check if there was a valid response
    if (resObj.get("Response").getAsString().equals("True")) {
      String imdbId = resObj.get("imdbID").getAsString();
      String title = resObj.get("Title").getAsString();
      String yearAsString = resObj.get("Year").getAsString();
      String[] split = yearAsString.split("–");
      int year = Integer.parseInt(split[0]);
      String rated = resObj.get("Rated").getAsString();
      String runTime = resObj.get("Runtime").getAsString();
      String genre = resObj.get("Genre").getAsString();
      // split genre into an array and create a list from it
      List<String> genreList = Arrays.asList(genre.split(", "));
      String plot = resObj.get("Plot").getAsString();
      String awards = resObj.get("Awards").getAsString();
      double imdbRating = resObj.get("imdbRating").getAsDouble();
      String imdbVotes = resObj.get("imdbVotes").getAsString();
      // get poster url
      String posterURL = PosterAPI.getImage(imdbId);
      // creating a new movie from this
      m = new Movie(imdbId, title, year, rated, runTime, genreList, plot,
          awards, imdbRating, imdbVotes, posterURL);
    }
    return m;
  }
}
