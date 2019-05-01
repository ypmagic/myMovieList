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

public class AddWatchLaterHandler implements Route {
  
  private static final Gson GSON = new Gson();

  @Override
  public String handle(Request req, Response res) throws Exception {
      boolean success = false;
      QueryParamsMap qm = req.queryMap();
      String imdbId = qm.value("imdbId");
      String username = req.session().attribute("username");
      
      Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
      if (!DatabaseQuery.watchLaterContains(conn, username, imdbId)) {
        DatabaseQuery.insertIntoWatchLater(conn, username, imdbId);
        success = true;
      }
      Map<String, Object> variables = ImmutableMap.of("success", success);
      return GSON.toJson(variables);
  }
}
