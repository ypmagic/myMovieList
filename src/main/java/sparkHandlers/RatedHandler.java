package sparkHandlers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import database.DatabaseHandler;
import database.DatabaseQuery;
import movie.Movie;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class RatedHandler implements TemplateViewRoute {

	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
	      String username = request.session().attribute("username");
	      if (username == null) {
	        response.redirect("/login");
	        return null;
	      }
	      Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
	      List<String> imdbIds = DatabaseQuery.getRatedMovies(conn, username);
	      List<Movie> movies = new ArrayList<>();
	      for (String id : imdbIds) {
	        Movie m = DatabaseQuery.getMovie(conn, id);
	        movies.add(m);
	      }
	      // return all the information
	      Map<String, Object> variables = new ImmutableMap.Builder<String, Object>()
	              .put("title", "Rated Movies")
	              .put("list", movies).build();
	      return new ModelAndView(variables, "rated.ftl");
	}

}
