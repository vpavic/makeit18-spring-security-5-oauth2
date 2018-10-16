package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoResourceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoResourceServerApplication.class, args);
    }

    @GetMapping(path = "/")
    public Jwt getJwt(@AuthenticationPrincipal Jwt jwt) {
        return jwt;
    }

    @GetMapping(path = "/greet")
    public String getGreet() {
        return "Hello MakeIT 2018!";
    }

}
