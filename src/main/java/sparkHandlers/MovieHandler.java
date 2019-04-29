package sparkHandlers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import api.MovieAPI;
import database.DatabaseHandler;
import database.DatabaseQuery;
import movie.Movie;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

/**
 * Handler for the /m/:movieId route for GUI.
 * @author ypark29
 */
public class MovieHandler implements TemplateViewRoute {
  
  Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();

  @Override
  public ModelAndView handle(Request req, Response res) {
    String codedID = req.params("movieID");
    String movieID = "";
    try {
      movieID = URLDecoder.decode(codedID.replace("+", "%2B"), "UTF-8")
          .replace("%2B", "+");
    } catch (UnsupportedEncodingException e) {
      System.out.println("ERROR: Encoding for GUI failed.");
    }
    Movie m = getMovie(movieID);
    if (m.equals(null)) {
      m = MovieAPI.searchById(movieID);
      DatabaseQuery.insertMovie(conn, m);
    }
    // retrieve name of actor and the list of films they were in
    Map<String, Object> variables = ImmutableMap.of("movie",
        m, "title", m.getTitle());
    return new ModelAndView(variables, "movie.ftl");
  }
  
  private Movie getMovie(String movieId) {
    return DatabaseQuery.getMovie(conn, movieId);
  }
}
