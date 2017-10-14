package entitys;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by 1 on 08.10.2017.
 */
public class YoutubeSearch {
    private static String URL = "http://www.youtube.com/embed/";
    private static String AUTO_PLAY = "?autoplay=1";

    private ActivityResponse getActivity(String channelId) throws ExecutionException, InterruptedException {
        Future<HttpResponse<ActivityResponse>> response = Unirest.get("https://www.googleapis.com/youtube/v3/activities")
                .queryString("part", "snippet")
                .queryString("channelId", channelId)
                .queryString("maxResults", "1")
                .queryString("key", "AIzaSyANXgqU8UyAJMKSRM78PcUWTSR5r6aapAU")
                .asObjectAsync(ActivityResponse.class);

        ActivityResponse activityResponse = response.get().getBody();
        return activityResponse;
    }
public void getActiv(String channelId) throws ExecutionException, InterruptedException, UnirestException {
        HttpResponse<String> response = Unirest.get("https://www.googleapis.com/youtube/v3/activities")
                .queryString("part", "snippet")
                .queryString("channelId", channelId)
                .queryString("maxResults", "1")
                .queryString("key", "AIzaSyANXgqU8UyAJMKSRM78PcUWTSR5r6aapAU")
                .asString();

        String activityResponse = response.getBody();
    System.out.println(activityResponse);

    }

    public void show(String channelId) throws ExecutionException, InterruptedException {

        for (int i = 0; i < getActivity(channelId).items.size(); i++){
            Activity activity = getActivity(channelId).items.get(i);
            System.out.println("Название видео " + activity.snippet.title);
            System.out.println("Название канала " + activity.snippet.thumbnails.channelTitle);
            System.out.println("Дата публикации " + activity.snippet.publishedAt);
        }
    }

    public String getVideoId(String channelId, int i) throws ExecutionException, InterruptedException {
        Activity activity = getActivity(channelId).items.get(i);
        String videoId = activity.contentDetails.upload.videoId;
        return videoId;
    }
    public int getItemSize(String channelId, int i) throws ExecutionException, InterruptedException {
        return getActivity(channelId).items.size();
    }
}
