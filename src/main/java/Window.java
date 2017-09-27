import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import entitys.Activity;
import entitys.ActivityResponse;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
        final YoutubeSearch youtubeSearch = new YoutubeSearch();
        primaryStage.setHeight(600);
        primaryStage.setWidth(600);
        final Pane root = new Pane();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        final TextField textField = new TextField("UCdIp4tcWOGihEQKYxzSlFaQ");
        textField.setTranslateX(10);
        textField.setTranslateY(20);

        Button button = new Button("Search");
        button.setTranslateX(10);
        button.setTranslateY(40);

        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    youtubeSearch.show(textField.getText());
                } catch (UnirestException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        root.getChildren().addAll(button);
        primaryStage.show();

        }

}
