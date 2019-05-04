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
import util.Bigram;

public class RatedHandler implements TemplateViewRoute {

	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
	      String username = request.session().attribute("username");
	      if (username == null) {
	        response.redirect("/login");
	        return null;
	      }
	      Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
	      List<Bigram<String, String>> imdbIds = DatabaseQuery.getRatings(conn, username);
	      List<Bigram<Movie, String>> movies = new ArrayList<>();
	      for (Bigram id : imdbIds) {
	        Movie m = DatabaseQuery.getMovie(conn, (String) id.getLeft());
	        movies.add(new Bigram<>(m, (String) id.getRight()));
	      }
	      // return all the information
	      Map<String, Object> variables = new ImmutableMap.Builder<String, Object>()
	              .put("title", "Rated Movies")
	              .put("list", movies).build();
	      return new ModelAndView(variables, "rated.ftl");
	}

}
