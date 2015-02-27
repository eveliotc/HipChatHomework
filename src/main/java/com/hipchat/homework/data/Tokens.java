package com.hipchat.homework.data;

import java.util.ArrayList;
import java.util.List;

import static com.hipchat.homework.Utils.checkNotNull;

public class Tokens {
  private final List<Mention> mentions;
  private final List<Emoticon> emoticons;
  private final List<Link> links;

  private Tokens(List<Mention> mentions, List<Emoticon> emoticons, List<Link> links) {
    this.mentions = mentions;
    this.emoticons = emoticons;
    this.links = links;
  }

  public List<Mention> getMentions() {
    return mentions;
  }

  public List<Emoticon> getEmoticons() {
    return emoticons;
  }

  public List<Link> getLinks() {
    return links;
  }

  public static class Builder {
    List<Mention> mentions;
    List<Emoticon> emoticons;
    List<Link> links;

    public Tokens build() {
      return new Tokens(mentions, emoticons, links);
    }

    public void add(Mention mention) {
      checkNotNull(mention, "Cannot add null mention.");
      if (mentions == null) {
        mentions = new ArrayList<Mention>();
      }
      mentions.add(mention);
    }

    public void add(Emoticon emoticon) {
      checkNotNull(emoticon, "Cannot add null link.");
      if (emoticons == null) {
        emoticons = new ArrayList<Emoticon>();
      }
      emoticons.add(emoticon);
    }

    public void add(Link link) {
      checkNotNull(link, "Cannot add null link.");
      if (links == null) {
        links = new ArrayList<Link>();
      }
      links.add(link);
    }

    public void addMention(String mention) {
      add(new Mention(mention));
    }

    public void addEmoticon(String emoticon) {
      add(new Emoticon(emoticon));
    }

    public void addLink(String link) {
      add(new Link(link));
    }

    public void addLink(String link, String title) {
      add(new Link(link, title));
    }
  }
}
