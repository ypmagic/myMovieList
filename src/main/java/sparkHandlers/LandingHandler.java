package sparkHandlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import database.DatabaseHandler;
import database.DatabaseQuery;
import movie.Movie;
import recommend.MoviesByGenre;
import recommend.Recommender;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;
import util.Bigram;

public class LandingHandler implements TemplateViewRoute {
  
    @Override
    public ModelAndView handle(Request request, Response response) {
      String username =  request.session().attribute("username");
      System.out.println("USERNAME: " + username);
//      request.session().invalidate();
//      request.session(true).attribute("username", username);

      List<Bigram<String, String>> userLists = new ArrayList<>();

      if (username == null) {
        // TODO: Redirect to login page if username is null
        response.redirect("/login");
        return null;
      } else {
        List<Bigram<Integer, String>> temp = DatabaseQuery.getListsFromUser(DatabaseHandler
                .getDatabaseHandler().getConnection(), username);
        for (Bigram<Integer, String> bi : temp) {
          userLists.add(new Bigram<>(bi.getLeft().toString(),
                  bi.getRight().toString()));
        }
        username = "Hello, " + username;
      }
      // use movies by genre
      Set<Integer> randoms = new HashSet<>();
      while (randoms.size() < 4) {
        // first get MovieTests random genre
        Random r = new Random();
        int random = r.nextInt(12);
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
      Map<Object, Object> variables;
      if (DatabaseQuery.getRatings(DatabaseHandler
                .getDatabaseHandler().getConnection(), request.session().attribute("username")).size() == 0) {   
	      variables = new ImmutableMap.Builder<>()
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
	              .put("userLists", userLists).build();
      } else {
    	    List<Bigram<String, String>> l = DatabaseQuery.getRatings(DatabaseHandler
                    .getDatabaseHandler().getConnection(), request.session().attribute("username"));
    	    List<String> imdbIds = new ArrayList<>();
    	    List<Integer> ratings = new ArrayList<>();
    	    for (Bigram<String, String> b : l) {
    	    		String imdbId = b.getLeft();
    	    		if (imdbId.indexOf("tt00") > -1) {
    	    			imdbIds.add(b.getLeft().substring(4, imdbId.length()));
    	    		} else {
    	    			imdbIds.add(b.getLeft().substring(3, imdbId.length()));
    	    		}
    	    		ratings.add(Integer.parseInt(b.getRight()));
    	    }
    	    List<Movie> recommendedMovies = Recommender.recommend(imdbIds, ratings,"");
    	  	variables = new ImmutableMap.Builder<>()
	              .put("title", "Home")
	              .put("moviesTop", recommendedMovies)
	              .put("moviesTopGenre", "Recommended for You")
	              .put("moviesTopMid", Recommender.recommend(imdbIds, ratings, movies.get(0).getGenre()))
	              .put("moviesTopMidGenre", movies.get(0).getGenre() + " Movies For You")
	              .put("moviesBotMid", Recommender.recommend(imdbIds, ratings, movies.get(1).getGenre()))
	              .put("moviesBotMidGenre", movies.get(1).getGenre() + " Movies For You")
	              .put("moviesBot", Recommender.recommend(imdbIds, ratings, movies.get(2).getGenre()))
	              .put("moviesBotGenre", movies.get(2).getGenre() + " Movies For You")
	              .put("username", username)
	              .put("userLists", userLists).build();
      }
      return new ModelAndView(variables, "landing.ftl");
    }
}
