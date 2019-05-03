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

public class InsertRatingHandler implements Route {

	private static final Gson GSON = new Gson();

	@Override
	public String handle(Request request, Response response) throws Exception {
		QueryParamsMap qm = request.queryMap();
		String login = request.session().attribute("username");
		String imdbId = qm.value("id");
		int rating = Integer.parseInt(qm.value("rating"));
		Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
		if (DatabaseQuery.checkRating(conn, login, imdbId)) {
			DatabaseQuery.updateRating(conn, login, imdbId, rating);
		} else {
			DatabaseQuery.insertRating(conn, login, imdbId, rating);
		}
		Map<String, Object> variables = ImmutableMap.of("success", true);
		return GSON.toJson(variables);
	}
}
