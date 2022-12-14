package ru.levelp.at.taf.configuration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.ConfigFactory;

@LoadPolicy(LoadType.MERGE)
@Sources({
    "classpath:env/${env}.properties",
    "system:properties",
    "system:env"
})
public interface WebSiteConfiguration extends Config {

    static WebSiteConfiguration getInstance() {
        return ConfigFactory.create(WebSiteConfiguration.class);
    }

    @Key("web.site.url")
    String url();

    @Key("web.site.username")
    String username();

    @Key("web.site.password")
    String password();

    @Key("selenium.wait.millis")
    Long waitTimeout();
}
