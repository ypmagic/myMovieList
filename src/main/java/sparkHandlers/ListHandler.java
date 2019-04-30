package sparkHandlers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import database.DatabaseHandler;
import database.DatabaseQuery;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class ListHandler implements TemplateViewRoute {
	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
//	    String listID = request.params("listID");
//	    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
//	    String curator = DatabaseQuery.getCuratorFromId(conn, listID);
//	    String name = DatabaseQuery.getNameFromId(conn, listID);
//	    String listString = DatabaseQuery.getListFromId(conn, listID);
//	    String[] list = listString.split(" ");
//	    List<Bigram> movies = new ArrayList<>();
//	    for (String id : list) {
//	    	movies.add(new Bigram(id, DatabaseQuery.getMovie(conn, id).getTitle()));
//	    }
//	    Map<String, Object> variables = new ImmutableMap.Builder<String, Object>().put("curator",
//	            curator).put("title", name).put("name", name).put("movies", movies).build();
//	    return new ModelAndView(variables, "list.ftl");
		return null;
		//fix later
	}
}
