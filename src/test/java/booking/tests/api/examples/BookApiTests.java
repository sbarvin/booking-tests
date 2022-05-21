package booking.tests.api.examples;

import booking.api.model.examples.BookRequest;
import booking.api.model.examples.BookResponse;
import io.qameta.allure.restassured.AllureRestAssured;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class BookApiTests {
    @BeforeAll
    static void prediction() {
        baseURI = "https://demo.api-platform.com";
    }

    @Tag("API")
    @Test
    @DisplayName("Создание книги")
    void createBookWithoutReviewTest() {

        BookRequest bookRequest = new BookRequest();

        bookRequest.setIsbn("9780143105428");
        bookRequest.setAuthor("Austen, Jane");
        bookRequest.setTitle("Pride and Prejudice: (Penguin Classics Deluxe Edition)");
        bookRequest.setDescription("Desc");
        bookRequest.setPublicationDate("2009-01-01T00:00:00+00:00");

        BookResponse bookResponse = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(bookRequest)
                .log().body()
                .post("/books")
                .then()
                .statusCode(201)
                .log().status()
                .log().body()
                .extract().as(BookResponse.class);

       assertThat(bookResponse.getAuthor()).isEqualTo("Austen, Jane");
    }

    @Tag("API")
    @Test
    @DisplayName("Обновление информации о книге")
    void updateBookTest() {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setAuthor(null);

        BookResponse bookResponse = given()
                .filter(new AllureRestAssured())
                .contentType("application/merge-patch+json")
                .body(bookRequest)
                .log().body()
                .patch("/books/{id}", "a9e9aa3c-675a-4dfe-92b8-7c3d92cc8085")
                .then()
                .statusCode(200)
                .log().status()
                .log().body()
                .extract().as(BookResponse.class);

        assertThat(bookResponse.getAuthor()).isEqualTo("Barvin Sergey2");
    }

}
