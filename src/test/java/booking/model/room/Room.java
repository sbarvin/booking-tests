package booking.model.room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {
    private Integer roomid;
    private String roomName;
    private Integer roomPrice;
    private Boolean accessible;
    private String description;
    private String image;
    private String type;
    private List<String> features;
}