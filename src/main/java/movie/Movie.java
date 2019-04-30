package movie;

import java.util.List;
import java.util.Objects;

public class Movie {
  
  private String imdbID;
  private String title;
  private int year;
  private String rated;
  private String runTime;
  private List<String> genre;
  private String plot;
  private String awards;
  private double imdbRating;
  private String imdbVotes;
  private String img;
  
  public Movie(String imdbID, String title, int year) {
    this.imdbID = imdbID;
    this.title = title;
    this.year = year;
  }
  
  public Movie(String imdbID, String title, int year, String rated,
      String runTime, List<String> genre, String plot, String awards,
      double imdbRating, String imdbVotes, String imgLink) {
    this.imdbID = imdbID;
    this.title = title;
    this.year = year;
    this.rated = rated;
    this.runTime = runTime;
    this.genre = genre;
    this.plot = plot;
    this.awards = awards;
    this.imdbRating = imdbRating;
    this.imdbVotes = imdbVotes;
    this.img = imgLink;
  }

  public String getImdbID() {
    return imdbID;
  }

  public void setImdbID(String imdbID) {
    this.imdbID = imdbID;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getRated() {
    return rated;
  }

  public void setRated(String rated) {
    this.rated = rated;
  }

  public String getRunTime() {
    return runTime;
  }

  public void setRunTime(String runTime) {
    this.runTime = runTime;
  }

  public List<String> getGenre() {
    return genre;
  }

  public void setGenre(List<String> genre) {
    this.genre = genre;
  }

  public String getPlot() {
    return plot;
  }

  public void setPlot(String plot) {
    this.plot = plot;
  }

  public String getAwards() {
    return awards;
  }

  public void setAwards(String awards) {
    this.awards = awards;
  }

  public double getImdbRating() {
    return imdbRating;
  }

  public void setImdbRating(double imdbRating) {
    this.imdbRating = imdbRating;
  }

  public String getImdbVotes() {
    return imdbVotes;
  }

  public void setImdbVotes(String imdbVotes) {
    this.imdbVotes = imdbVotes;
  }
  
  public String getImg() {
    return img;
  }
  
  public void setImg(String imgLink) {
    this.img = imgLink;
  }
  
  @Override
  public String toString() {
    return "{imdbID: " + this.getImdbID() + ", title: " + this.getTitle()
      + ", img: " + (this.img != null) + "}";
  }
  
  @Override
  public int hashCode() {
    return Objects.hashCode(imdbID);
  }
  
  @Override
  public boolean equals(Object o) {
    Movie m2 = (Movie) o;
    if (o == this) {
      return true;
    }
    if (!(o instanceof Movie)) {
      return false;
    }
    return this.hashCode() == m2.hashCode();
  }
}
