package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@RestController
public class DemoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoClientApplication.class, args);
    }

    @GetMapping(path = "/")
    public AuthenticatedPrincipal getPrincipal(@AuthenticationPrincipal AuthenticatedPrincipal principal) {
        return principal;
    }

    @Autowired
    private WebClient webClient;

    @GetMapping(path = "/repos")
    public String getRepos() {
        return this.webClient.get()
                .uri("https://api.github.com/user/repos")
                .attributes(ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId("github"))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

}
