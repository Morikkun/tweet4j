package br.com.code;

import twitter4j.*;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class Logic {
    private Twitter twitter;
    private PrintStream consolePrinter;
    List<Status> statusList;

    /**
     * @param console
     * The constructor receives a PrintStream as a parameter to exhibit the returned contents on the main execution class
     * The Twitter object is initialized with a factory and the Array List that stores the status as well
     */
    public Logic(PrintStream console) {
        twitter = TwitterFactory.getSingleton();
        statusList = new ArrayList<Status>();
    }

    /**
     *
     * @param message
     * @throws TwitterException
     * @throws IOException
     *
     * The method receives the desired message to be posted as a parameter and throws a Twitter exception in case twitter messes up
     * and an IO in case the input/output messes up
     */
    public String tweetSomething(String message) throws TwitterException, IOException {
        Status status = twitter.updateStatus(message);
        System.out.println("Status update with success. Actual status is [" + status.getText() + "]");
        return status.getText();
    }

    /**
     *
     * @param handle
     * @throws TwitterException
     * @throws IOException
     *
     * Fetches the tweets for a specified handle name, which is a parameter in this method.
     */
    public int fetchUserTweets (String handle) throws TwitterException, IOException{
        Paging page = new Paging(1,200);
        int p = 1;
        while (p <= 10){
            page.setPage(p);
            statusList.addAll(twitter.getUserTimeline(handle, page));
            p++;
        }
        return page.getCount();
    }

    /**
     *
     * @param handle
     * @throws TwitterException
     * @throws IOException
     *
     * Fetches and outputs the tweets for the given user handle
     */
    public String queryTweetsByHandle(String handle) throws TwitterException, IOException{
        statusList.clear();
        fetchUserTweets(handle);
        int counter = statusList.size();

        while (counter > 0){
            counter--;
            System.out.println("Tweet #" + counter + ": " + statusList.get(counter).getText());
        }
        return handle;
    }

    public Query queryTweets (String topic, int maxResults) throws TwitterException {
        Query query = new Query(topic);
        query.setCount(maxResults);
        QueryResult result = twitter.search(query);

        int counter = 0;
        System.out.println("Total tweets #" + result.getTweets().size());

            for(Status tweet : result.getTweets()){
                counter++;
                System.out.println("Tweet #" + counter + " by user @" + tweet.getUser().getName()
                + "Content: \n" + tweet.getText() + "\n");
        }
        return query;
    }

    public Query queryTweets (String topic, String date, int maxResults) throws TwitterException {
        Query query  = new Query(topic);
        query.setSince(date);
        query.setCount(maxResults);

            int counter = 0;
            QueryResult result = twitter.search(query);
            System.out.println("Total tweets #" + result.getTweets().size());

            for(Status tweet : result.getTweets()){
                counter++;
                System.out.println("Tweet #" + counter + " by user @" + tweet.getUser().getName()
                        + "Content: \n" + tweet.getText() + "\n");
            }


        return query;
    }
}
