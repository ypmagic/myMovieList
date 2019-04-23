package sparkHandlers;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.TemplateViewRoute;

public class RegisterPageHandler implements TemplateViewRoute {

  @Override
  public ModelAndView handle(Request req, Response res) {
    Map<String, Object> variables = ImmutableMap.of("title", "Register");
    return new ModelAndView(variables, "register.ftl");
  }
}
