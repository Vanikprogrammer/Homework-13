package entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by 1 on 25.09.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Snippet {
    public String publishedAt;
    public String title;
}
