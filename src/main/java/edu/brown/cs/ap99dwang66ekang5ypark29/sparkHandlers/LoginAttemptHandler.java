package edu.brown.cs.ap99dwang66ekang5ypark29.sparkHandlers;

import java.sql.Connection;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.ap99dwang66ekang5ypark29.database.DatabaseHandler;
import edu.brown.cs.ap99dwang66ekang5ypark29.database.DatabaseQuery;
import edu.brown.cs.ap99dwang66ekang5ypark29.user.User;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginAttemptHandler implements Route {
	private static final Gson GSON = new Gson();
	@Override
	public Object handle(Request request, Response response) throws Exception {
	    boolean success = true;
	    QueryParamsMap qm = request.queryMap();
	    String userName = qm.value("username");
	    String password = qm.value("password");
	    //TODO: if username/password are invalid, set success false
	    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
	    if (!DatabaseQuery.authenticateUser(conn, userName, password)) {
	    	success = false;
	    }
	    Map<String, Object> variables = ImmutableMap.of("success", success);
	    return GSON.toJson(variables);
	}
}
