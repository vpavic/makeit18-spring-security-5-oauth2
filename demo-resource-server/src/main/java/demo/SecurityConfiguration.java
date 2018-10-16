package demo;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
            .oauth2ResourceServer()
                .jwt()
                    .jwkSetUri("https://raw.githubusercontent.com/vpavic/makeit18-spring-security-5-oauth2/master/demo-resource-server/etc/jwks.json")
                    .and()
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
            .authorizeRequests()
                .antMatchers("/greet/**").hasAuthority("SCOPE_greet")
                .anyRequest().authenticated();
        // @formatter:on
    }

}
