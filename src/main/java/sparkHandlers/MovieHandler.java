package sparkHandlers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

/**
 * Handler for the /m/:movieId route for GUI.
 * @author ypark29
 */
public class MovieHandler implements TemplateViewRoute {

  private String movieName;

  @Override
  public ModelAndView handle(Request req, Response res) {
    String codedID = req.params("movieID");
    String movieID = "";
    try {
      movieID = URLDecoder.decode(codedID.replace("+", "%2B"), "UTF-8")
          .replace("%2B", "+");
    } catch (UnsupportedEncodingException e) {
      System.out.println("ERROR: Encoding for GUI failed.");
    }
    // retrieve name of actor and the list of films they were in
    Map<String, Object> variables = ImmutableMap.of("title",
        "Actor - " + this.movieName);
    return new ModelAndView(variables, "movie.ftl");
  }
}
