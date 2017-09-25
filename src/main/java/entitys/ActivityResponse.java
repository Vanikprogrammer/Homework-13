package entitys;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by 1 on 25.09.2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityResponse {
    public List<Activity> items;
}
