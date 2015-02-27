package com.hipchat.homework;

import com.hipchat.homework.data.Tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hipchat.homework.Tokenizer.Resolver.*;
import static com.hipchat.homework.Utils.checkNotNull;

public class Tokenizer {
  private final Resolver[] resolvers;

  public Tokenizer() {
    this(MENTION, EMOTICON, LINK_WITH_TITLE); // Use this by default
  }

  Tokenizer(Resolver... resolvers) {
    checkNotNull(resolvers, "Regexes must not be null.");
    this.resolvers = resolvers;
  }

  public Tokens resolve(String message) {
    checkNotNull(message, "Message to be resolved must not be null.");

    Tokens.Builder tb = new Tokens.Builder();
    for (Resolver resolver : resolvers) {
      resolver.resolve(message, tb);
    }
    return tb.build();
  }

  static final Pattern MENTION_PATTERN = Pattern.compile("@\\w+");
  static final Pattern EMOTICON_PATTERN = Pattern.compile("\\([A-Za-z0-9]{1,15}\\)");
  static final Pattern LINK_PATTERN = Pattern.compile("http(s)*://.*");
  enum Resolver {
    MENTION(MENTION_PATTERN, null),
    EMOTICON(EMOTICON_PATTERN, null),
    LINK(LINK_PATTERN, null), // Used for testing or scenarios in which title is not needed
    LINK_WITH_TITLE(LINK_PATTERN, new TitleFinder()),
    ;

    final Pattern pattern;
    final MetadataFinder metadataFinder;
    Resolver(Pattern pattern, MetadataFinder metadataFinder) {
      this.pattern = pattern;
      this.metadataFinder = metadataFinder;
    }

    public void resolve(String message, Tokens.Builder tb) {
      Matcher matcher = pattern.matcher(message);
      while (matcher.find()) {
        int start = matcher.start();
        int end = matcher.end();

        String match = message.substring(start, end);
        appendMatch(match, tb);
      }
    }

    private void appendMatch(String match, Tokens.Builder tb) {
      switch (this) {
        case MENTION:
          match = match.substring(1, match.length()); // Remove @
          tb.addMention(match);
          break;
        case EMOTICON:
          match = match.substring(1, match.length() - 1); // Remove parenthesis ()
          tb.addEmoticon(match);
          break;
        case LINK:
          tb.addLink(match);
          break;
        case LINK_WITH_TITLE:
          String title = metadataFinder.find(match);
          tb.addLink(match, title);
          break;
        default:
          throw new UnsupportedOperationException("Do not know to append " + this);
      }
    }
  }

  public interface MetadataFinder {
    <T extends Object> T find(String match);
  }
}
