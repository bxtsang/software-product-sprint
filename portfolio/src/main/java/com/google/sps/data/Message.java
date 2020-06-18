package com.google.sps.data;

import com.google.appengine.api.datastore.Entity;

public class Message {
  private final long timestamp;
  private final String message;

  public Message(long timestamp, String message) {
    this.timestamp = timestamp;
    this.message = message;
  }

  public boolean isValid() {
    if (message == null || message.trim() == "") {
      return false;
    }
    return true;
  }

  public Entity toEntity() {
    Entity messageEntity = new Entity("Message");

    messageEntity.setProperty("timestamp", timestamp);
    messageEntity.setProperty("message", message);

    return messageEntity;
  }
}
