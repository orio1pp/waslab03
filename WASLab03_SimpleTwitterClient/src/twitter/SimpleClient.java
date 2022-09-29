package twitter;


import java.util.List;
import java.util.Map;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.Query.Unit;
import twitter4j.QueryResult;
import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class SimpleClient {

    public static void main(String[] args) throws Exception {
        final Twitter twitter = new TwitterFactory().getInstance();
        int bcnCode = 753692;
       
        /* get top 10 trending topics in bcn */
        Trend[] trendsBCN = twitter.getPlaceTrends(bcnCode).getTrends();
        int maxLen = trendsBCN.length;
        if (maxLen > 10) maxLen = 10;
        //for (int timer = 0; timer < 10000; ++timer);
		double lat = 41.3870154;
		double lon = 2.1700471;
		GeoLocation geolocation = new GeoLocation(lat, lon);
		Unit units = Query.KILOMETERS;
		Query catTrends;
        for (int i = 0; i < maxLen; ++i) {
            System.out.println("Trending topic: "+ trendsBCN[i].getName());
    		catTrends = new Query( trendsBCN[i].getName());
    		catTrends.setGeoCode(geolocation, 5.0, units);
    		catTrends.setResultType(Query.RECENT);
            QueryResult query = twitter.search(catTrends);

            int count = 0;
            boolean found = false;

            while (query.hasNext() && !found) {
                List<Status> tweets = query.getTweets();
                for (int j = 0; j < tweets.size() && count < 5; ++j) {
                    Status tweet = tweets.get(j);
                    if (true /* pl cat */) {
                        System.out.println("    - " + tweet.getUser().getName() + " (@" + 
                                tweet.getUser().getScreenName() + "): " + 
                                tweet.getText());
                        count++;
                    }
                }
                if (count == 5) {
                    count = 0;
                    found = true;
                }
                query = twitter.search(query.nextQuery());
            }
        }
    }
 
}