package booking.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/app.properties"
})
public interface AppConfig extends Config {

    String webUrl();

    String defaultLogin();

    String defaultPassword();

    String apiUrl();

    String apiPath();
}
