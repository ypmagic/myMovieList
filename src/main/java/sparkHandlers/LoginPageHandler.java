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
    req.session().invalidate();

    Map<String, Object> variables = ImmutableMap.of("title",
                "Login", "exists", false);
    return new ModelAndView(variables, "login.ftl");
  }
}
