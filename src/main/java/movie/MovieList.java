package movie;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import user.User;

public class MovieList {
	private int id;
	private User curator;
	private String name;
	private List<Movie> movies;
	private static final Gson GSON = new Gson();
	
	public MovieList(User curator, String name) {
		this.curator = curator;
		this.name = name;
		movies = new ArrayList<>();
	}
	
	public void addMovie(Movie m) {
		movies.add(m);
	}
}
