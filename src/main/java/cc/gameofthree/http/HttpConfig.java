package cc.gameofthree.http;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HttpConfig {
    private final Environment env;

    public HttpConfig(Environment env) {
        this.env = env;
    }

    public int getArtificialDelayInMs() {
        return Integer.parseInt(env.getProperty("http.artificialDelayInMs", "0"));
    }

    public String getOtherPlayerUrl() {
        return env.getProperty("http.otherPlayerUrl", "http://localhost:" + env.getProperty("server.port"));
    }

    public WebClient webClient() {
        return WebClient.create(getOtherPlayerUrl());
    }
}
