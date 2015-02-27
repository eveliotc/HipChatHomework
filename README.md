HipChat Homework
================

My homework for the lovely HipChat team.

To run just use `./gradlew runSamples` like

```
> ./gradlew runSamples                                                                                                                                                  :compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:runSamples
Input: "@chris you around?"
Return (string):
{
  "mentions" : [ "chris" ]
}

Input: "Good morning! (megusta) (coffee)"
Return (string):
{
  "emoticons" : [ "megusta", "coffee" ]
}

Input: "Olympics are starting soon; http://www.nbcolympics.com"
Return (string):
{
  "links" : [ {
    "url" : "http://www.nbcolympics.com",
    "title" : "NBC Olympics | Home of the 2016 Olympic Games in Rio"
  } ]
}

Input: "@bob @john (success) such a cool feature; https://twitter.com/jdorfman/status/430511497475670016"
Return (string):
{
  "mentions" : [ "bob", "john" ],
  "emoticons" : [ "success" ],
  "links" : [ {
    "url" : "https://twitter.com/jdorfman/status/430511497475670016",
    "title" : "Justin Dorfman on Twitter: \"nice @littlebigdetail from @HipChat (shows hex colors when pasted in chat). http://t.co/7cI6Gjy5pq\""
  } ]
}


BUILD SUCCESSFUL

Total time: 2.802 secs
```