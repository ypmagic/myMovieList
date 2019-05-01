package sparkHandlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
      Set<Integer> randoms = new HashSet<>();
      while (randoms.size() < 4) {
        // first get a random genre
        Random r = new Random();
        int random = r.nextInt(18);
        randoms.add(random);
      }
      Iterator<Integer> i = randoms.iterator();
      List<MoviesByGenre> movies = new ArrayList<>();
      while (i.hasNext()) {
        int random = i.next();
        movies.add(Recommender.moviesByGenre(random));
      }
      MoviesByGenre movies1 = movies.get(0);
      MoviesByGenre movies2 = movies.get(1);
      MoviesByGenre movies3 = movies.get(2);
      MoviesByGenre movies4 = movies.get(3);
      
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
