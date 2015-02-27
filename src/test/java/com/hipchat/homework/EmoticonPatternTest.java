package com.hipchat.homework;

import org.junit.Test;

import java.util.regex.Matcher;

import static com.google.common.truth.Truth.assertWithMessage;
import static com.hipchat.homework.Tokenizer.EMOTICON_PATTERN;

public class EmoticonPatternTest {
  @Test
  public void officialEmoticonsMatch() {
    String[] officialEmoticons = {"(allthethings)", "(android)", "(areyoukiddingme)", "(arrington)", "(arya)", "(ashton)", "(atlassian)", "(awesome)", "(awthanks)", "(aww)", "(awwyiss)", "(awyeah)", "(badass)", "(badjokeeel)", "(badpokerface)", "(badtime)", "(basket)", "(beer)", "(bicepleft)", "(bicepright)", "(bitbucket)", "(boom)", "(borat)", "(branch)", "(bumble)", "(bunny)", "(cadbury)", "(cake)", "(candycorn)", "(carl)", "(caruso)", "(catchemall)", "(ceilingcat)", "(celeryman)", "(cereal)", "(cerealspit)", "(challengeaccepted)", "(chef)", "(chewie)", "(chocobunny)", "(chompy)", "(chucknorris)", "(clarence)", "(coffee)", "(confluence)", "(content)", "(continue)", "(cookie)", "(cornelius)", "(corpsethumb)", "(daenerys)", "(dance)", "(dealwithit)", "(derp)", "(disappear)", "(disapproval)", "(doge)", "(doh)", "(donotwant)", "(dosequis)", "(downvote)", "(drevil)", "(drool)", "(ducreux)", "(dumb)", "(evilburns)", "(excellent)", "(facepalm)", "(failed)", "(feelsbadman)", "(feelsgoodman)", "(finn)", "(fireworks)", "(firstworldproblems)", "(fonzie)", "(foreveralone)", "(freddie)", "(fry)", "(ftfy)", "(fu)", "(fuckyeah)", "(fwp)", "(gangnamstyle)", "(gates)", "(ghost)", "(giggity)", "(goldstar)", "(goodnews)", "(greenbeer)", "(grumpycat)", "(gtfo)", "(haha)", "(haveaseat)", "(heart)", "(heygirl)", "(hipchat)", "(hipster)", "(hodor)", "(huehue)", "(hugefan)", "(huh)", "(ilied)", "(indeed)", "(iseewhatyoudidthere)", "(itsatrap)", "(jackie)", "(jaime)", "(jake)", "(jira)", "(jobs)", "(joffrey)", "(jonsnow)", "(kennypowers)", "(krang)", "(kwanzaa)", "(lincoln)", "(lol)", "(lolwut)", "(megusta)", "(meh)", "(menorah)", "(mindblown)", "(motherofgod)", "(ned)", "(nextgendev)", "(nice)", "(ninja)", "(noidea)", "(notbad)", "(nothingtodohere)", "(notit)", "(notsureif)", "(notsureifgusta)", "(obama)", "(ohcrap)", "(ohgodwhy)", "(ohmy)", "(okay)", "(omg)", "(orly)", "(paddlin)", "(pbr)", "(philosoraptor)", "(pingpong)", "(pirate)", "(pokerface)", "(poo)", "(present)", "(pumpkin)", "(rageguy)", "(rainicorn)", "(rebeccablack)", "(reddit)", "(rockon)", "(romney)", "(rudolph)", "(sadpanda)", "(sadtroll)", "(salute)", "(samuel)", "(santa)", "(sap)", "(scumbag)", "(seomoz)", "(shamrock)", "(shrug)", "(skyrim)", "(standup)", "(stare)", "(stash)", "(success)", "(successful)", "(sweetjesus)", "(tableflip)", "(taco)", "(taft)", "(tea)", "(thatthing)", "(theyregreat)", "(toodamnhigh)", "(tree)", "(troll)", "(truestory)", "(trump)", "(turkey)", "(twss)", "(tyrion)", "(tywin)", "(unacceptable)", "(unknown)", "(upvote)", "(vote)", "(waiting)", "(washington)", "(wat)", "(whoa)", "(whynotboth)", "(wtf)", "(yey)", "(yodawg)", "(youdontsay)", "(yougotitdude)", "(yuno)", "(zoidberg)"};
    for (String emoticon : officialEmoticons) {
      Matcher matcher = EMOTICON_PATTERN.matcher(emoticon);
      assertWithMessage(emoticon).that(matcher.matches()).isTrue();
    }
  }

  @Test
  public void invalidEmoticonsDoNotMatch() {
    String[] invalidEmoticons = {
        "()", // No body
        "(", // No right parenthesis
        "(asd", // No right parenthesis with body
        ")", // No left parenthesis
        "asd)", // No left parenthesis with body
        "(as d)", // Space
        "(1234567890123456)", // > 15
    };
    for (String emoticon : invalidEmoticons) {
      Matcher matcher = EMOTICON_PATTERN.matcher(emoticon);
      assertWithMessage(emoticon).that(matcher.matches()).isFalse();
    }
  }
}
