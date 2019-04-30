package movie;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import database.DatabaseHandler;
import database.DatabaseQuery;
import util.Bigram;

public class MovieList {
	private int id;
	private String curator;
	private String name;
	private List<String> movies;
	
	public MovieList(int id, String curator, String name, List<String> movies) {
		this.id = id;
		this.curator = curator;
		this.name = name;
		this.movies = movies;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCurator() {
		return curator;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Bigram<String, String>> getMovies() {
		List<Bigram<String, String>> l = new ArrayList<>();
		Connection conn = DatabaseHandler.getDatabaseHandler().getConnection();
		for (String movieId : movies) {
			l.add(new Bigram<String, String>(movieId, DatabaseQuery.getMovie(conn, movieId).getTitle()));
		}
		return l;
	}
}
