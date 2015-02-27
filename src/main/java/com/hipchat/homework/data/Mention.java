package com.hipchat.homework.data;

import static com.hipchat.homework.Utils.checkNotNull;

public class Mention {
  private final String username;

  public Mention(String username) {
    checkNotNull(username, "Username must not be null");
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Mention mention = (Mention) o;

    if (!username.equals(mention.username)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return username.hashCode();
  }

  @Override
  public String toString() {
    return username;
  }
}
