package ru.levelp.at.taf.configuration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

@LoadPolicy(LoadType.MERGE)
@Sources({
    "classpath:env/${env}-api.properties",
    "system:properties",
    "system:env"
})
public interface ApiConfiguration extends Config {

    static ApiConfiguration getInstance() {
        return ConfigFactory.create(ApiConfiguration.class);
    }

    @Key("api.url")
    String url();

    @Key("api.version")
    String version();

    @Key("api.key")
    String key();

    @Key("api.token")
    String token();
}
