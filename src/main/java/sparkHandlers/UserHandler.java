package sparkHandlers;

import java.sql.Connection;

import database.DatabaseHandler;
import database.DatabaseQuery;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class UserHandler implements TemplateViewRoute {
	@Override
	public ModelAndView handle(Request request, Response response) throws Exception {
	    int login = Integer.parseInt(request.params("userID"));
	    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();	    
		return null;
	}

}
