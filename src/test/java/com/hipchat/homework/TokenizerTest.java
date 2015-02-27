package com.hipchat.homework;

import com.hipchat.homework.data.Emoticon;
import com.hipchat.homework.data.Link;
import com.hipchat.homework.data.Mention;
import com.hipchat.homework.data.Tokens;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;


public class TokenizerTest {
  private Tokenizer tokenizer;

  @Before
  public void setup() {
    tokenizer = new Tokenizer();
  }

  @Test
  public void singleMentionIsResolved() {
    String msg = "@chris you around?";

    Tokens tokens = tokenizer.resolve(msg);
    List<Mention> mentions = tokens.getMentions();

    assertThat(mentions).hasSize(1);
    assertThat(mentions).contains(new Mention("chris"));
  }

  @Test
  public void emoticonsAreResolved() {
    String msg =  "Good morning! (megusta) (coffee)";

    Tokens tokens = tokenizer.resolve(msg);
    List<Emoticon> emoticons = tokens.getEmoticons();

    assertThat(emoticons).hasSize(2);
    assertThat(emoticons).containsAllOf(new Emoticon("megusta"), new Emoticon("coffee"));
  }

  @Test
  public void singleLinkIsResolved() {
    String msg = "Olympics are starting soon; http://www.nbcolympics.com";

    Tokens tokens = tokenizer.resolve(msg);
    List<Link> links = tokens.getLinks();

    assertThat(links).hasSize(1);
    assertThat(links).contains(new Link("http://www.nbcolympics.com"));
  }

  @Test
  public void mentionsEmoticonsAndLinksAreResolved() {
    String msg = "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016";

    Tokens tokens = tokenizer.resolve(msg);
    List<Mention> mentions = tokens.getMentions();
    List<Emoticon> emoticons = tokens.getEmoticons();
    List<Link> links = tokens.getLinks();


    assertThat(mentions).hasSize(2);
    assertThat(mentions).containsAllOf(new Mention("bob"), new Mention("john"));

    assertThat(emoticons).hasSize(1);
    assertThat(emoticons).contains(new Emoticon("success"));

    assertThat(links).hasSize(1);
    assertThat(links).contains(new Link("https://twitter.com/jdorfman/status/430511497475670016"));
  }
}
