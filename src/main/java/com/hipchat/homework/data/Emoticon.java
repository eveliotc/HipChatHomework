package com.hipchat.homework.data;

import static com.hipchat.homework.Utils.checkNotNull;

public class Emoticon {
  private final String emoticonName;

  public Emoticon(String emoticonName) {
    checkNotNull(emoticonName, "Emoticon must not be null");
    this.emoticonName = emoticonName;
  }

  public String getEmoticonName() {
    return emoticonName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Emoticon mention = (Emoticon) o;

    if (!emoticonName.equals(mention.emoticonName)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return emoticonName.hashCode();
  }

  @Override
  public String toString() {
    return emoticonName;
  }
}
