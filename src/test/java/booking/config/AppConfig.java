package booking.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/app.properties"
})
public interface AppConfig extends Config {

    @Key("web.url")
    String webUrl();

    @Key("user.default.login")
    String defaultLogin();

    @Key("user.default.password")
    String defaultPassword();

    @Key("api.url")
    String apiUrl();

    @Key("api.path")
    String apiPath();
}
