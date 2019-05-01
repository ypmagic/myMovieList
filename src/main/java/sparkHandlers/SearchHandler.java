package sparkHandlers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import api.MovieAPI;
import database.DatabaseHandler;
import database.DatabaseQuery;
import movie.Movie;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;

public class SearchHandler implements TemplateViewRoute {
	
    @Override
    public ModelAndView handle(Request req, Response res) {
      QueryParamsMap qm = req.queryMap();
      String sentence = qm.value("search");
      List<Movie> movies = MovieAPI.search(sentence);
      // find all titles that contains the search query. 
      // output based on some search criteria

      Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
      List<String> users = DatabaseQuery.searchUsers(conn, sentence);

      Map<String, Object> variables;
      variables = ImmutableMap.of("title", "Search Results", 
          "movies", movies, "numMovResults", movies.size(),
              "users", users, "numUserResults", users.size());
      return new ModelAndView(variables,"movie_search.ftl");
    }
}
