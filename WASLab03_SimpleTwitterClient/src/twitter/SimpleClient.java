package twitter;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class SimpleClient {
 	public static boolean checkIsInBarcelona(String name) {
 		return name.equals("Barcelona") || name.equals("Barcelona, España") ||
 				name.equals("Barcelona, Cataluña");
 	}
	 public static void main(String[] args) throws Exception {

	        final Twitter twitter = new TwitterFactory().getInstance();
	        int bcnCode = 753692;

	        /* get 10 1st trend top in bcn */
	        for(int timer=0; timer<100000000; timer++);
	        Trend[] trendsBCN = twitter.getPlaceTrends(bcnCode).getTrends();
	        for(int timer=0; timer<100000000; timer++);
	        for (int i = 0; i < 10; ++i) {
	        	//System.out.println(trendsBCN[i]);
	        	QueryResult tweets = twitter.search(new Query (trendsBCN[i].getName()));
	        	for(int timer=0; timer<100000000; timer++);
	        	int numFound = 0;
	        	boolean found = false;
	        	while(tweets.hasNext() && !found) {
	            	List<Status> status = tweets.getTweets();
	            	for(int j = 0; j<15 && !found; j++) {
	            		Status tweet = status.get(j);
	            		String nameCity = status.get(j).getUser().getLocation();
	            		//System.out.println(nameCity);
	            		if(checkIsInBarcelona(nameCity)) {
	                        System.out.println("    - " + tweet.getUser().getName() + " (@" + 
	                                tweet.getUser().getScreenName() + "): " + 
	                                tweet.getText());
	            			numFound++;
		            		if(numFound == 5) found = true;
	            		}
	            	}
	            	
	            	//System.out.println(i + " " + trendsBCN[i].getName() + trendsBCN[i].toString());
	            }
	        	found = false;
	        	numFound = 0;
	        }
	      }

}
