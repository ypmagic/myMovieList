package api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class PosterAPI {

  private static final String url = "http://img.omdbapi.com/";
  private static final String key = "&apikey=5ac72dbc";
  
  public static String getImage(String imdbId) {
    String fullUrl =  url + "?i=" + imdbId + "&h=600" + key;
    boolean success = isValid(fullUrl);
    if (success) {
      return fullUrl;
    }
    return null;
  }
  
  private static boolean isValid(String fullUrl) {
    URL u = null;
    int code = 0;
    try {
      u = new URL (fullUrl);
      HttpURLConnection huc =  (HttpURLConnection) u.openConnection();
      huc.setRequestMethod("GET"); 
      huc.connect(); 
      code = huc.getResponseCode();
    } catch (IOException e) {
      System.out.println("ERROR: Something wrong with checking poster URL.");
    } 
    if (code == 404) {
      return false;
    }
    return true;
  }
}
