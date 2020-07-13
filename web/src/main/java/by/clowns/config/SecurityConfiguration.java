package by.clowns.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new EncodingFilter(), ChannelProcessingFilter.class);
        http.authorizeRequests()
                .antMatchers("/cars", "/taxi/register", "/truck/register", "/bus/register", "/request/save").authenticated()
                .antMatchers("/users", "/region/register").hasAuthority("ADMIN")
                .anyRequest().permitAll()
            .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/myLogin")
                .defaultSuccessUrl("/")
            .and()
                .csrf().disable();
    }

}
