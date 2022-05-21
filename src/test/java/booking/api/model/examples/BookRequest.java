package booking.api.model.examples;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookRequest {

    private String isbn;
    private String title;
    private String description;
    private String author;
    private String publicationDate;
    private List<ReviewRequest> reviews;
}

//{
//  "isbn": "string",
//  "title": "string",
//  "description": "string",
//  "author": "string",
//  "publicationDate": "2022-05-15T06:25:21.623Z",
//  "reviews": [
//    {
//      "body": "string",
//      "rating": 0,
//      "book": "string",
//      "author": "string",
//      "publicationDate": "2022-05-15T06:25:21.623Z"
//    }
//  ],
//  "cover": "string",
//  "archivedAt": "2022-05-15T06:25:21.623Z"
//}