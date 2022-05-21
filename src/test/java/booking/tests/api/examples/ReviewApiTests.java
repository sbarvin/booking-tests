package booking.tests.api.examples;

import booking.api.model.examples.ReviewRequest;
import booking.api.model.examples.ReviewResponse;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class ReviewApiTests {
    @BeforeAll
    static void prediction() {
        baseURI = "https://demo.api-platform.com";
    }

    @Tag("API")
    @Test
    @DisplayName("Создание рецензии на книгу")
    void createReviewTest() {
        ReviewRequest reviewRequest = new ReviewRequest();

        reviewRequest.setBook("/books/e904b40c-c020-418d-b51e-2fe0dc997083");
        reviewRequest.setAuthor("Austen, Jane2");
        reviewRequest.setBody("Austen, Jane");
        reviewRequest.setRating(1);
        reviewRequest.setPublicationDate("2009-01-01T00:00:00+00:00");

        ReviewResponse reviewResponse = given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(reviewRequest)
                .log().body()
                .post("/reviews")
                .then()
                .statusCode(201)
                .log().status()
                .log().body()
                .extract().as(ReviewResponse.class);

        assertThat(reviewResponse.getAuthor()).isEqualTo("Austen, Jane2");
    }
}
