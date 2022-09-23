package twitter;


import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Date;

import twitter4j.JSONObject;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class SimpleClient {

	public static void main(String[] args) throws Exception {
		final Twitter twitter = new TwitterFactory().getInstance();
		long tweetID = twitter.getUserTimeline("fib_was").get(0).getId();
		System.out.println(tweetID);
		twitter.retweetStatus(tweetID);
		
		/*
		Date now = new Date();
		String latestStatus = "Hey @fib_was, we've just completed task #4 [timestamp: "+now+"]";
		Status status = twitter.updateStatus(latestStatus);
		System.out.println("Successfully updated the status to: " + status.getText());*/  
	}
}
