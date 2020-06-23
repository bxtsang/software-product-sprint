package com.google.sps.data;

public class LoginResponse {
  private boolean isLoggedIn;
  private String url;

  public LoginResponse(boolean isLoggedIn, String url) {
    this.isLoggedIn = isLoggedIn;
    this.url = url;
  }

  public boolean getIsLoggedIn() {
    return isLoggedIn;
  }

  public String getUrl() {
    return url;
  }
}
