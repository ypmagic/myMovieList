package sparkHandlers;

import java.sql.Connection;
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

public class ListViewHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request request, Response response) throws Exception {
        System.out.println("REACHED");
        int listId = Integer.parseInt(request.params("listId"));
        Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
        List<String> movieIds = DatabaseQuery.getMoviesForListId(conn, listId);
        // if the list of movie ID's is empty, return success: false
        System.out.println(movieIds);
        if (movieIds == null) {
          Map<String, Object> variables = new ImmutableMap.Builder<String, Object>()
              .put("title", "List")
              .put("list", null).build();
          return new ModelAndView(variables, "list.ftl");
        }
        // if not, get the list of movies for this particular list
        MovieList list = new MovieList(listId);
        // return all the information
        Map<String, Object> variables = new ImmutableMap.Builder<String, Object>()
                .put("title", "List – " + list.getName())
                .put("list", list).build();
        return new ModelAndView(variables, "list.ftl");
    }

}
