package sparkHandlers;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class ListHandler implements TemplateViewRoute {
	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
	    String listID = request.params("listID");
	    Map<String, Object> variables = ImmutableMap.of("content",
	            listID, "title", "list");
	    
	    return new ModelAndView(variables, "list.ftl");
	}

}
