import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import entitys.Activity;
import entitys.ActivityResponse;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by 1 on 25.09.2017.
 */
public class Window extends Application {


    private static void initApplication() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public static void main(String[] args) throws UnirestException {
        initApplication();
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        getActivity("UCdIp4tcWOGihEQKYxzSlFaQ");

        }
    private void getActivity(String channelId) throws UnirestException {
        HttpResponse<ActivityResponse> response = (HttpResponse<ActivityResponse>) Unirest.get("https://www.googleapis.com/youtube/v3/activities")
                .queryString("part", "contentDetails")
                .queryString("channelId", channelId)
                .queryString("maxResults", "1")
                .queryString("key", "AIzaSyCT5uQTJSRDdTZJXVDm30wUsii3oNNa11Q")
                .asObjectAsync(ActivityResponse.class);

        ActivityResponse activityResponse = response.getBody();
        for(int i = 0; i < activityResponse.items.size(); i++){
            Activity activity = activityResponse.items.get(i);
            System.out.println(activity.snippet.publishedAt);
            System.out.println(activity.snippet.title);
        }

    }
}
