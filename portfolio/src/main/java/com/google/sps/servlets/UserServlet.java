package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;

@WebServlet("/login")
public class UserServlet extends HttpServlet {
  private static Gson GSON = new Gson();
  private static UserService userService = UserServiceFactory.getUserService();
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    List<String> data = new ArrayList<>();

    if (userService.isUserLoggedIn()) {
      String logoutUrl = userService.createLogoutURL("/");
      
      data.add("logout");
      data.add(logoutUrl);
    } else {
      String loginUrl = userService.createLoginURL("/");

      data.add("login");
      data.add(loginUrl);
    }

    String json = GSON.toJson(data);
    response.setContentType("text/json");
    response.getWriter().println(json);
  }
}
