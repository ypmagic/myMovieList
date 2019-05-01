package sparkHandlers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
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
import user.User;
import util.Bigram;

/**
 * Handler for the /m/:movieId route for GUI.
 * @author ypark29
 */
public class MovieHandler implements TemplateViewRoute {
  
  Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();

  @Override
  public ModelAndView handle(Request req, Response res) {
    // user stuff
    String username =  req.session().attribute("username");
//    req.session().invalidate();
//    req.session(true).attribute("username", username);
    List<Bigram<String, String>> userLists = new ArrayList<>();
    System.out.println(username);
    if (username != null) {
      List<Bigram<Integer, String>> temp = DatabaseQuery.getListsFromUser(DatabaseHandler
              .getDatabaseHandler().getConnection(), username);
      for (Bigram<Integer, String> bi : temp) {
        userLists.add(new Bigram<>(bi.getLeft().toString(),
                bi.getRight().toString()));
      }
    }
    // movie stuff
    String codedID = req.params("movieID");
    String movieID = "";
    try {
      movieID = URLDecoder.decode(codedID.replace("+", "%2B"), "UTF-8")
          .replace("%2B", "+");
    } catch (UnsupportedEncodingException e) {
      System.out.println("ERROR: Encoding for GUI failed.");
    }
    Movie m = getMovie(movieID);
    if (m == null) {
      m = new Movie(null, null, 0);
      Map<String, Object> variables = ImmutableMap.of("movie",
          m, "title", "Invalid");
      return new ModelAndView(variables, "movie.ftl");
    }
    m = MovieAPI.searchById(movieID);
    DatabaseQuery.insertMovie(conn, m);
    // retrieve name of actor and the list of films they were in
    Map<String, Object> variables = ImmutableMap.of("movie",
        m, "title", m.getTitle(), "username", new User(username, ""), 
        "userLists", userLists);
    return new ModelAndView(variables, "movie.ftl");
  }
  
  private Movie getMovie(String movieId) {
    return DatabaseQuery.getMovie(conn, movieId);
  }
}
