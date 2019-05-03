package sparkHandlers;

import java.sql.Connection;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import com.google.gson.Gson;
import database.DatabaseHandler;
import database.DatabaseQuery;
import spark.*;

public class RemoveWatchLaterHandler implements Route {

  private static final Gson GSON = new Gson();

  @Override
  public String handle(Request request, Response response) throws Exception {
    QueryParamsMap qm = request.queryMap();
    String username = request.session().attribute("username");
    String movieId = qm.value("movieId");
    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
    DatabaseQuery.deleteFromWatchLater(conn, username, movieId);
    Map<String, Object> variables = ImmutableMap.of("success", true);
    return GSON.toJson(variables);
  }
}
