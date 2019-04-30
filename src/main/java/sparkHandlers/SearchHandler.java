package sparkHandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class SearchHandler implements Route {
	
	private HashMap<String,String> movieToIdMap;
	private final Gson GSON = new Gson();
	
    @Override
    public String handle(Request req, Response res) {

      QueryParamsMap qm = req.queryMap();
      String sentence = qm.value("search");
      System.out.println("it should get here");
      
      // find all titles that contains the search query. 
      // output based on some search criteria
      

      Map<String, Object> variables;
      variables = ImmutableMap.of("suggestions","");
      return GSON.toJson(variables);
    }

}
