package sparkHandlers;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class LoginPageHandler implements TemplateViewRoute {
  
  @Override
  public ModelAndView handle(Request req, Response res) {
    boolean exists = true;
    String username =  req.session().attribute("username");
    req.session().invalidate();
    req.session(true).attribute("username", username);
    if (username == null) {
      exists = false;
    }
    Map<String, Object> variables = ImmutableMap.of("title",
                "Login", "exists", exists);
    return new ModelAndView(variables, "login.ftl");
  }
}
