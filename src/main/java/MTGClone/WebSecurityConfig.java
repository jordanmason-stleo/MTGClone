package MTGClone;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Called configure(HttpSecurity http);");
        http.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated().and().formLogin().and()
                .csrf().disable().logout();
    }

    // TODO: Login using SQLDriver.authenticateUser(username, password)
 

}