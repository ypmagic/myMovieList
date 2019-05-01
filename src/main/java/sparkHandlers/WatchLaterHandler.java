package sparkHandlers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import database.DatabaseHandler;
import database.DatabaseQuery;
import movie.Movie;
import movie.MovieList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class WatchLaterHandler implements TemplateViewRoute {
  
    @Override
    public ModelAndView handle(Request req, Response res) throws Exception {
      String username = req.session().attribute("username");
      if (username == null) {
        res.redirect("/login");
        return null;
      }
      Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
      List<String> imdbIds = DatabaseQuery.getWatchLaterList(conn, username);
      List<Movie> movies = new ArrayList<>();
      for (String id : imdbIds) {
        Movie m = DatabaseQuery.getMovie(conn, id);
        movies.add(m);
      }
      // return all the information
      Map<String, Object> variables = new ImmutableMap.Builder<String, Object>()
              .put("title", "Watch Later")
              .put("list", movies).build();
      return new ModelAndView(variables, "watchlater.ftl");
    }
}
