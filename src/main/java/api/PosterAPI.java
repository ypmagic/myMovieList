package api;

public class PosterAPI {

  private static final String url = "http://img.omdbapi.com/";
  private static final String key = "&apikey=5ac72dbc";
  
  public static String imageURL(String imdbId) {
    return url + "?i=" + imdbId + "&h=600" + key;
  }
}
