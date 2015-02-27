package com.hipchat.homework;

import com.hipchat.homework.data.Tokens;

import java.io.IOException;

import static com.hipchat.homework.Utils.dump;

public class Main {
  public static void main(String[] args) throws IOException {
    Tokenizer tokenizer = new Tokenizer();
    for (String arg : args) {
      Tokens tokens = tokenizer.resolve(arg);
      dump(arg, tokens, System.out, true);
      System.out.println();
    }
  }
}
