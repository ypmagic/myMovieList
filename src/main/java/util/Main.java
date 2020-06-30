package util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import cli.CommandProcessor;
import database.DatabaseHandler;
import freemarker.template.Configuration;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;
import sparkHandlers.*;

/*
 * The Main class of our project. This is where execution begins.
 *
 * @author ypark29
 */
public final class Main {

  private static final int DEFAULT_PORT = 4567;
  private CommandProcessor cli = new CommandProcessor();

  /**
   * The initial method called when execution begins.
   *
   * @param args
   *          An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }
  
  static int getHerokuAssignedPort() {
    ProcessBuilder processBuilder = new ProcessBuilder();
    if (processBuilder.environment().get("PORT") != null) {
        return Integer.parseInt(processBuilder.environment().get("PORT"));
    }
    return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
 }

  private void run() {
    // Parse command line arguments
    OptionParser parser = new OptionParser();
    parser.accepts("gui");
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
    .defaultsTo(DEFAULT_PORT);
    OptionSet options = parser.parse(args);
    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }
    runREPL();
  }

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration();
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n",
          templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  private void runSparkServer(int port) {
    Spark.port(getHerokuAssignedPort()); 
    Spark.externalStaticFileLocation("src/main/resources/static");
    Spark.exception(Exception.class, new ExceptionPrinter());

    FreeMarkerEngine freeMarker = createEngine();

    // Setup Spark Routes here
    Spark.get("m/:movieID", new MovieHandler(), freeMarker);
    Spark.post("/list", new ListHandler());
    Spark.get("u/:user", new UserPageHandler(), freeMarker);
    Spark.get("l/:listID", new ListViewHandler(), freeMarker);
    Spark.post("/register", new RegisterHandler());
    Spark.get("/login", new LoginPageHandler(), freeMarker);
    Spark.post("/loginattempt", new LoginAttemptHandler());
    Spark.get("/register", new RegisterPageHandler(), freeMarker);
    Spark.get("/home", new LandingHandler(), freeMarker);
    Spark.get("/profile", new ProfilePageHandler(), freeMarker);
    Spark.get("/", new LoginPageHandler(), freeMarker);
    Spark.post("/moviesearch", new SearchHandler(),freeMarker);
    Spark.post("/addToList", new AddToListHandler());
    Spark.post("/removeFromList", new RemoveFromListHandler());
    Spark.post("/addWatchLater", new AddWatchLaterHandler());
    Spark.get("/watchlater", new WatchLaterHandler(), freeMarker);
    Spark.post("/insertrating", new InsertRatingHandler());
    Spark.post("/deleteList", new DeleteListHandler());
    Spark.get("/rated", new RatedHandler(), freeMarker);
    Spark.post("/removeWatchLater", new RemoveWatchLaterHandler());
    Spark.post("/removeRatedMovie", new RemoveRatedMovieHandler());
    // Setup database
    DatabaseHandler.getDatabaseHandler();
  }

  /**
   * Display an error page when an exception occurs in the server.
   *
   * @author ypark29
   */
  private static class ExceptionPrinter implements ExceptionHandler {
    @Override
    public void handle(Exception e, Request req, Response res) {
      res.status(500);
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }

  /**
   * Calls the REPL.
   */
  private void runREPL() {
    this.cli.loop();
  }
}
