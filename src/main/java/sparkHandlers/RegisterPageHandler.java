package sparkHandlers;

import java.sql.Connection;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import database.DatabaseHandler;
import database.DatabaseQuery;
import spark.*;
import user.User;

/**
 * Handler for user page registration
 * @author ypark29
 */
public class RegisterPageHandler implements TemplateViewRoute {

  private static final Gson GSON = new Gson();

  @Override
  public ModelAndView handle(Request req, Response res) {
    Map<String, Object> variables = ImmutableMap.of("title",
            "Register");
    return new ModelAndView(variables, "register.ftl");
  }
}
