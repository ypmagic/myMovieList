package sparkHandlers;

import java.sql.Connection;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import com.google.gson.Gson;
import database.DatabaseHandler;
import database.DatabaseQuery;
import spark.*;

public class RemoveFromListHandler implements Route {

  private static final Gson GSON = new Gson();

  @Override
  public String handle(Request request, Response response) throws Exception {
    QueryParamsMap qm = request.queryMap();
    int listId = Integer.parseInt(qm.value("listId"));
    String movieId = qm.value("movieId");
    Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
    DatabaseQuery.removeFromList(conn, movieId, listId);
    Map<String, Object> variables = ImmutableMap.of("success", true);
    return GSON.toJson(variables);
  }
}
