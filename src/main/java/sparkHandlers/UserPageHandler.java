package sparkHandlers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import database.DatabaseHandler;
import database.DatabaseQuery;
import movie.MovieList;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import util.Bigram;

public class UserPageHandler implements TemplateViewRoute {
	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
	    String login = request.params("user");
	    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
	    List<Bigram<Integer, String>> listIds = DatabaseQuery.getListsFromUser(conn, login);

	    List<MovieList> ret = new ArrayList<>();

	    for (Bigram<Integer, String> lst : listIds) {
	      int lstId = lst.getLeft();
	      String lstName = lst.getRight();
	      List<String> movieIds = DatabaseQuery.getMoviesForListId(conn, lstId);
	      ret.add(new MovieList(lstId, login, lstName, movieIds));

	    }
	    
	    Map<String, Object> variables = new ImmutableMap.Builder<String, Object>()
	            .put("title", "Profile")
	            .put("lists", ret)
	            .put("username", login).build();
	    return new ModelAndView(variables, "user.ftl");
	}

}
