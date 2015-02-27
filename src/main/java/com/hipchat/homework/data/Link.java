package com.hipchat.homework.data;

import static com.hipchat.homework.Utils.checkNotNull;

public class Link {
  private final String url;
  private final String title;

  public Link(String url) {
    this(url, null);
  }

  public Link(String url, String title) {
    checkNotNull(url, "URL must not be null");
    this.url = url;
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public String getTitle() {
    return title;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Link link = (Link) o;

    if (!url.equals(link.url)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return url.hashCode();
  }

  @Override
  public String toString() {
    return url;
  }
}
