package sparkHandlers;

import com.google.common.collect.ImmutableMap;

import database.DatabaseHandler;
import database.DatabaseQuery;
import movie.MovieList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.sql.Connection;
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
      res.redirect("/home");
      return null;
    }
    // TODO: query 	profile information from database using session username
    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
    List<MovieList> lists = DatabaseQuery.getListsFromUser(conn, username);
    
    Map<String, Object> variables = new ImmutableMap.Builder<String, Object>().put("title", "Profile").put("lists", lists).build();
    return new ModelAndView(variables, "profile.ftl");
  }
}
