package com.hipchat.homework;

import com.hipchat.homework.Tokenizer.MetadataFinder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TitleFinder implements MetadataFinder {

  @Override
  @SuppressWarnings("unchecked")
  public String find(String url) {
    try {
      Document doc = Jsoup.connect(url).get();
      return doc.title();
    } catch (Exception e) {
      // dayum
    }
    return null;
  }
}
