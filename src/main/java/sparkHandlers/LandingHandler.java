package sparkHandlers;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import static com.google.common.collect.Lists.newArrayList;

import api.MovieAPI;
import movie.Movie;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class LandingHandler implements TemplateViewRoute {
  
    @Override
    public ModelAndView handle(Request request, Response response) {
      // first try sending a static list of movies
      Movie m1 = MovieAPI.searchById("tt0083658");
      Movie m2 = MovieAPI.searchById("tt1856101");
      Movie m3 = MovieAPI.searchById("tt7428594");
      Movie m4 = MovieAPI.searchById("tt1080585");
      Movie m5 = MovieAPI.searchById("tt0126817");
      List<Movie> movies = newArrayList(m1, m2, m3, m4, m5);
      Map<String, Object> variables = ImmutableMap.of("title",
                  "Home", "moviesTop", movies, "moviesTopMid", movies,
                  "moviesBotMid", movies, "moviesBot", movies);
      return new ModelAndView(variables, "landing.ftl");
    }
}
