package sparkHandlers;

import java.sql.Connection;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import database.DatabaseHandler;
import database.DatabaseQuery;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import user.User;

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
    // insert user into the database TODO: if not already in
    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
    DatabaseQuery.insertNewUser(conn, u);
    // TODO: if already in database, switch success off.
    Map<String, Object> variables = ImmutableMap.of("success", success);
    return GSON.toJson(variables);
  }
}
