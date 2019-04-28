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

/**
 * Handler for user registration
 * @author ypark29
 */
public class RegisterHandler implements Route {

  private static final Gson GSON = new Gson();

  @Override
  public String handle(Request req, Response res) {
    boolean success = true;
    // Parse the request from the Javascript and get the different fields.
    QueryParamsMap qm = req.queryMap();
    String userName = qm.value("username");
    String password = qm.value("password");
    User u = new User(userName, password);

    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();

    // checks if user in database
    if (DatabaseQuery.validLogin(conn, userName)) {
      success = false;
    } else {
      DatabaseQuery.insertNewUser(conn, u);
    }

    Map<String, Object> variables = ImmutableMap.of("success", success);
    return GSON.toJson(variables);
  }
}
  