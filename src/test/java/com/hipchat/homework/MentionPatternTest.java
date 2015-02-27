package com.hipchat.homework;

import org.junit.Test;

import java.util.regex.Matcher;

import static com.google.common.truth.Truth.assertWithMessage;
import static com.hipchat.homework.Tokenizer.MENTION_PATTERN;

public class MentionPatternTest {
  @Test
  public void providedMentionsMatch() {
    String[] someUrls = {
        "@chris",
        "@bob",
        "@john",
        "@all",
    };
    for (String url : someUrls) {
      Matcher matcher = MENTION_PATTERN.matcher(url);
      assertWithMessage(url).that(matcher.matches()).isTrue();
    }
  }

  @Test
  public void invalidMentionsDoNotMatch() {
    String[] someUrls = {
        "chris", // missing @
        "@", // no username
        "@ ", // no username again
        "sirhc@", // srorrim|mirrors
    };
    for (String url : someUrls) {
      Matcher matcher = MENTION_PATTERN.matcher(url);
      assertWithMessage(url).that(matcher.matches()).isFalse();
    }
  }

}
