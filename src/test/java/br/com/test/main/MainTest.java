package br.com.test.main;


import br.com.code.Logic;
import br.com.test.auxiliary.CustomAnnotation;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import twitter4j.Query;
import twitter4j.QueryResult;
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
        String expectedHandle = handle;
        String actualHandle = myPoster.queryTweetsByHandle(handle);
        assertEquals(expectedHandle,actualHandle);
    }

    @CustomAnnotation
    @CsvSource(value = {"Activision,80"})
    void queryForTweetsTopicBased (String topic, int maxResults) throws TwitterException {
        String expectedTopic = topic;
        int  expectedMaxResults = maxResults;
        Query result = myPoster.queryTweets(topic,maxResults);

        String actualTopic = result.getQuery();
        int actualMaxResults = result.getCount();

        assertEquals(expectedTopic, actualTopic);
        assertEquals(expectedMaxResults,actualMaxResults);
    }

    @CustomAnnotation
    @CsvSource("Activision, 2021-11-18, 80")
    void queryForTweetsDateBased (String topic, String date, int maxResults) throws TwitterException {
        String expectedTopic = topic;
        String expectedDate = date;
        int expectedMaxResults = maxResults;

        Query result = myPoster.queryTweets(topic, date, maxResults);
        String actualTopic = result.getQuery();
        String actualDate = result.getSince();
        int actualMaxResults = result.getCount();

        assertEquals(expectedTopic, actualTopic);
        assertEquals(expectedDate, actualDate);
        assertEquals(expectedMaxResults, actualMaxResults);

    }


}
