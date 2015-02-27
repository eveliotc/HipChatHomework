package com.hipchat.homework;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hipchat.homework.data.Emoticon;
import com.hipchat.homework.data.Link;
import com.hipchat.homework.data.Mention;
import com.hipchat.homework.data.Tokens;

import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;

/**
 * @hide
 */
public class Utils {

  public static void checkNotNull(Object obj, String msg) {
    if (obj == null) {
      throw new NullPointerException(msg);
    }
  }

  static final ObjectMapper MAPPER = new ObjectMapper();

  public static void dump(String input, Tokens tokens, PrintStream ps, boolean prettyPrint) throws IOException {
    ps.println("Input: \"" + input + "\"");
    ps.println("Return (string):");
    ObjectWriter writer = MAPPER.writer();
    if (prettyPrint) {
      writer = writer.with(INDENT_OUTPUT);
    }
    String strOut = writer.writeValueAsString(Output.from(tokens)); // Not using writeValue as it seems to close stream
    ps.println(strOut);
  }

  // Formatted output for pretty printing
  @JsonInclude(NON_NULL)
  public static class Output {
    public final List<String> mentions;
    public final List<String> emoticons;
    public final List<Link> links;

    Output(List<String> mentions, List<String> emoticons, List<Link> links) {
      this.mentions = mentions;
      this.emoticons = emoticons;
      this.links = links;
    }

    static Output from(Tokens tokens) {
      List<String> mentions = null;

      List<Mention> mentionList = tokens.getMentions();
      if (mentionList != null) {
        mentions = new LinkedList<String>();
        for (Mention mention : mentionList) {
          mentions.add(mention.getUsername());
        }
      }

      List<String> emoticons = null;
      List<Emoticon> emoticonsList = tokens.getEmoticons();
      if (emoticonsList != null) {
        emoticons = new LinkedList<String>();
        for (Emoticon emoticon : emoticonsList) {
          emoticons.add(emoticon.getEmoticonName());
        }
      }

      return new Output(mentions, emoticons, tokens.getLinks());
    }
  }
}
