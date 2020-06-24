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
import com.google.sps.data.LoginResponse;

@WebServlet("/login")
public class UserServlet extends HttpServlet {
  private static Gson gson = new Gson();
  private static UserService userService = UserServiceFactory.getUserService();
  private LoginResponse responseObj = new LoginResponse();
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    if (userService.isUserLoggedIn()) {
      responseObj.setLoggedIn(true);
      responseObj.setUrl(userService.createLogoutURL("/"));
    } else {
      responseObj.setLoggedIn(false);
      responseObj.setUrl(userService.createLoginURL("/"));
    }

    String json = gson.toJson(responseObj);
    response.setContentType("text/json");
    response.getWriter().println(json);
  }
}
