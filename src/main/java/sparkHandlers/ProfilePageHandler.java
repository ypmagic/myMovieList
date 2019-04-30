package sparkHandlers;

import com.google.common.collect.ImmutableMap;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

import java.util.Map;

public class ProfilePageHandler implements TemplateViewRoute {

  @Override
  public ModelAndView handle(Request req, Response res) {
    Map<String, Object> variables = ImmutableMap.of("title",
            "Profile");

    String username =  req.session().attribute("username");
    if (username == null) {
      // TODO: process no valid session
    }
    System.out.println(username);

    // TODO: query profile information from database using session username

    return new ModelAndView(variables, "profile.ftl");
  }
}
