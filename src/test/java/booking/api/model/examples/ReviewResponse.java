package booking.api.model.examples;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReviewResponse {
    private String id;
    private String body;
    private String author;
    private int rating;
    private String publicationDate;
    private BookResponse book;
}
