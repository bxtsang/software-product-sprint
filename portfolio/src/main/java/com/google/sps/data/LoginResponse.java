package com.google.sps.data;

public class LoginResponse {
  private boolean isLoggedIn;
  private String url;

  public void setLoggedIn(boolean isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean getIsLoggedIn() {
    return isLoggedIn;
  }

  public String getUrl() {
    return url;
  }
}
