package booking.api.model.examples;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookResponse {
    private String id;
    private String isbn;
    private String title;
    private String description;
    private String author;
    private String publicationDate;
    private List<ReviewResponse> reviews;
    private String cover;
    private String archivedAt;
}
//{
//    "@context": "/contexts/Book",
//    "@id": "/books/c6c53fd3-c011-4375-ae03-a0a9b97698a2",
//    "@type": "https://schema.org/Book",
//    "id": "c6c53fd3-c011-4375-ae03-a0a9b97698a2",
//    "isbn": "9780143105428",
//    "title": "Pride and Prejudice: (Penguin Classics Deluxe Edition)",
//    "description": "Desc",
//    "author": "Austen, Jane",
//    "publicationDate": "2009-01-01T00:00:00+00:00",
//    "reviews": [
//
//    ]
//}
