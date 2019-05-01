package sparkHandlers;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
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
        MovieList list = new MovieList(listId);
        // return all the information
        Map<String, Object> variables = new ImmutableMap.Builder<String, Object>()
                .put("title", "List")
                .put("list", list).build();
        return new ModelAndView(variables, "list.ftl");
    }

}
