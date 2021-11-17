package br.com.test.main;


import br.com.code.Logic;
import br.com.test.auxiliary.CustomAnnotation;
import org.junit.jupiter.params.provider.ValueSource;
import twitter4j.TwitterException;

import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTest {

    private static PrintStream printer;
    Logic myPoster = new Logic(printer);

    @CustomAnnotation
    @ValueSource(strings = "Hello, world! I'm testing the Twitter4J Library for Java!")
    void postATweet(String message) throws TwitterException, IOException{
        String requiredMessage = message;
        String actualMessage = myPoster.tweetSomething(message);
        assertEquals(requiredMessage,actualMessage);
    }

    @CustomAnnotation
    @ValueSource(strings = "Twitter")
    void searchForUsersTweets (String handle) throws IOException, TwitterException {
        int expectedPages = 200;
        int totalPages = myPoster.fetchUserTweets(handle);
        assertEquals(expectedPages,totalPages);
    }

    @CustomAnnotation
    @ValueSource(strings = "Twitter")
    void showTweets(String handle)throws TwitterException, IOException{
        int expectedTweets = 1998;
        int actualTweets = myPoster.queryTweetsByHandle(handle);
        assertEquals(expectedTweets,actualTweets);
    }


}
