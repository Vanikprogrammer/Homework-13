import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import entitys.Activity;
import entitys.ActivityResponse;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by 1 on 27.09.2017.
 */
public class YoutubeSearch {

    private ActivityResponse getActivityResponse(String channelId) throws UnirestException, ExecutionException, InterruptedException {
        Future<HttpResponse<ActivityResponse>> response = Unirest.get("https://www.googleapis.com/youtube/v3/activities")
                .queryString("part", "snippet")
                .queryString("channelId", channelId)
                .queryString("maxResults", "1")
                .queryString("key", "AIzaSyCT5uQTJSRDdTZJXVDm30wUsii3oNNa11Q")
                .asObjectAsync(ActivityResponse.class);

        return response.get().getBody();

    }
    public void show(String channelId) throws InterruptedException, ExecutionException, UnirestException {
        for(int i = 0; i < getActivityResponse(channelId).items.size(); i++){
            Activity activity = getActivityResponse(channelId).items.get(i);
            System.out.println(activity.snippet.publishedAt);
            System.out.println(activity.snippet.title);
            System.out.println(activity.snippet.thumbnails.channelTitle);
        }

    }
}
