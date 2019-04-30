package sparkHandlers;

import java.sql.Connection;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import database.DatabaseHandler;
import database.DatabaseQuery;
import spark.*;

public class LoginAttemptHandler implements Route {
  
	private static final Gson GSON = new Gson();
	
	@Override
	public String handle(Request request, Response response) throws Exception {
	    boolean success = true;
	    QueryParamsMap qm = request.queryMap();
	    String userName = qm.value("username");
	    String password = qm.value("password");
	    System.out.println(userName + " "+ password);
	    //TODO: if username/password are invalid, set success false
	    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
	    if (!DatabaseQuery.authenticateUser(conn, userName, password)) {
	    	success = false;
	    } else {
	    	// creates a session
				request.session(true).attribute("username", userName);
			}

	    Map<String, Object> variables = ImmutableMap.of("success", success);
	    return GSON.toJson(variables);
	}
}
