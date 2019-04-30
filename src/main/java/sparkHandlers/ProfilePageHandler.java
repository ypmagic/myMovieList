package sparkHandlers;

import com.google.common.collect.ImmutableMap;

import database.DatabaseHandler;
import database.DatabaseQuery;
import movie.MovieList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import spark.*;
import util.Bigram;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfilePageHandler implements TemplateViewRoute {

  @Override
  public ModelAndView handle(Request req, Response res) {


    // for security reasons creates new session on visit to profile
    String username =  req.session().attribute("username");
    req.session().invalidate();
    req.session(true).attribute("username", username);

    if (username == null) {
      // TODO: process no valid session
      res.redirect("/login");
      return null;
    }
    // TODO: query profile information from database using session username
    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
    List<Bigram<Integer, String>> listIds = DatabaseQuery.getListsFromUser(conn, username);

    List<MovieList> ret = new ArrayList<>();

    for (Bigram<Integer, String> lst : listIds) {
      int lstId = lst.getLeft();
      String lstName = lst.getRight();
      List<String> movieIds = DatabaseQuery.getMoviesForListId(conn, lstId);
      ret.add(new MovieList(lstId, username, lstName, movieIds));

    }
    
    Map<String, Object> variables = new ImmutableMap.Builder<String, Object>()
            .put("title", "Profile")
            .put("lists", ret)
            .put("username", username).build();
    return new ModelAndView(variables, "profile.ftl");
  }
}
