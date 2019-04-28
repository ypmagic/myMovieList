package edu.brown.cs.ap99dwang66ekang5ypark29.sparkHandlers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class ListHandler implements TemplateViewRoute {
	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
	    String listID = request.params("listID");
	    
		return null;
	}

}
