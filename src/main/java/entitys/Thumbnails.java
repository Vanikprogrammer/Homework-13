package entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by 1 on 27.09.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Thumbnails {
    public String channelTitle;
}
