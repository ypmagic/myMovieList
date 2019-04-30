package sparkHandlers;

import com.google.common.collect.ImmutableMap;
import spark.*;

import java.util.Map;

public class ProfilePageHandler implements TemplateViewRoute {

  @Override
  public ModelAndView handle(Request req, Response res) {
    // for security reasons creates new session on visit to profile
    String username =  req.session().attribute("username");
    req.session().invalidate();
    req.session(true).attribute("username", username);

    if (username == null) {
      // TODO: process no valid session
      res.redirect("/login");
      return null;
    }
    // TODO: query profile information from database using session username
    Map<String, Object> variables = ImmutableMap.of("title",
        "Profile", "username", username);
    return new ModelAndView(variables, "profile.ftl");
  }
}
