package booking.api.client;

import booking.config.App;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import lombok.Getter;

import static booking.api.client.CustomLogFilter.customLogFilter;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

@Getter
public class ApiConfig {

    private RequestSpecBuilder requestSpecBuilder;
    private ResponseSpecBuilder responseSpecBuilder;

    private ApiConfig() {
    }

    public static ApiConfig config() {
        return new ApiConfig();
    }

    public ApiConfig build() {
        this.requestSpecBuilder = new RequestSpecBuilder()
                .setContentType(JSON)
                .addFilter(customLogFilter().withCustomTemplates())
                .setBaseUri(App.config.apiUrl())
                .log(ALL);
        this.responseSpecBuilder = new ResponseSpecBuilder().log(ALL);

        return this;
    }
}
