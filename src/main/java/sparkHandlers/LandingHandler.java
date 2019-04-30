package sparkHandlers;

import java.util.Map;
import com.google.common.collect.ImmutableMap;
import recommend.MoviesByGenre;
import recommend.Recommender;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class LandingHandler implements TemplateViewRoute {
  
    @Override
    public ModelAndView handle(Request request, Response response) {
      String username =  request.session().attribute("username");
      request.session().invalidate();
      request.session(true).attribute("username", username);
      if (username == null) {
        username = "";
      } else {
        username = "Hello, " + username;
      }
      // use movies by genre
      MoviesByGenre movies1 = Recommender.moviesByGenre();
      MoviesByGenre movies2 = Recommender.moviesByGenre();
      MoviesByGenre movies3 = Recommender.moviesByGenre();
      MoviesByGenre movies4 = Recommender.moviesByGenre();
      Map<Object, Object> variables = new ImmutableMap.Builder<>()
          .put("title", "Home")
          .put("moviesTop", movies1.getMovies())
          .put("moviesTopGenre", movies1.getGenre() + " Movies")
          .put("moviesTopMid", movies2.getMovies())
          .put("moviesTopMidGenre", movies2.getGenre() + " Movies")
          .put("moviesBotMid", movies3.getMovies())
          .put("moviesBotMidGenre", movies3.getGenre() + " Movies")
          .put("moviesBot", movies4.getMovies())
          .put("moviesBotGenre", movies4.getGenre() + " Movies")
          .put("username", username)
          .build();
      return new ModelAndView(variables, "landing.ftl");
    }
}
