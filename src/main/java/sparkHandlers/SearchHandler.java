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
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class SearchHandler implements Route {
	
	private HashMap<String,String> movieToIdMap;
	private final Gson GSON = new Gson();
	
	public SearchHandler() {
		Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
		movieToIdMap = DatabaseQuery.getMovieToImdb(conn);
	}
	
    @Override
    public String handle(Request req, Response res) {

      QueryParamsMap qm = req.queryMap();
      String sentence = qm.value("search");
      List<String> imdbIds = new ArrayList<String>();
      List<Movie> movies = new ArrayList<>();
      // find all titles that contains the search query. 
      // output based on some search criteria
      Map<String, Object> variables;
      
      for (Map.Entry<String, String> movie: movieToIdMap.entrySet()) {
    	  	String movieTitle = movie.getValue();
    	  	if (movieTitle.toLowerCase().indexOf(sentence) >= 0) {
    	  		imdbIds.add(imdbIds.size(),movie.getKey());
    	  	} 	  
      }
      
      for (String imdbId : imdbIds) {
  	  	Movie m = MovieAPI.searchById(imdbId);
  	  	movies.add(m);
      }
      variables = ImmutableMap.of("suggestions","");
      return GSON.toJson(variables);
    }

}
