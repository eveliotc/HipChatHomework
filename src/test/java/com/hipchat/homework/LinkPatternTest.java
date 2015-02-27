package com.hipchat.homework;

import org.junit.Test;

import java.util.regex.Matcher;

import static com.google.common.truth.Truth.assertWithMessage;
import static com.hipchat.homework.Tokenizer.LINK_PATTERN;

public class LinkPatternTest {
  @Test
  public void providedUrlsMatch() {
    String[] someUrls = {
        "http://www.nbcolympics.com",
        "https://twitter.com/jdorfman/status/430511497475670016"
    };
    for (String url : someUrls) {
      Matcher matcher = LINK_PATTERN.matcher(url);
      assertWithMessage(url).that(matcher.matches()).isTrue();
    }
  }

  @Test
  public void invalidUrlsDoNotMatch() {
    String[] someUrls = {
        "www.nbcolympics.com", // missing protocol/schema
        "hi", // there
    };
    for (String url : someUrls) {
      Matcher matcher = LINK_PATTERN.matcher(url);
      assertWithMessage(url).that(matcher.matches()).isFalse();
    }
  }

}
