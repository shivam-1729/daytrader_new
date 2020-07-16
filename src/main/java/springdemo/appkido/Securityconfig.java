package springdemo.appkido;

import org.springframework.context.annotation.Configuration ;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class Securityconfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Set your configuration on the auth object
        auth.inMemoryAuthentication()
                .withUser("johndoe")
                .password("{noop}johndoe")
                .roles("USER")
                .and()
                .withUser("administrator")
                .password("{noop}administrator")
                .roles("ADMIN");
    }

    // @Bean
    // public PasswordEncoder getPasswordEncoder() {
    //     return NoOpPasswordEncoder.getInstance();
    // }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/configure.html").hasRole("ADMIN")
                .antMatchers("/web_prmtv.html").hasAnyRole("ADMIN","USER")
                .antMatchers("/docs/tradeFAQ.html").hasAnyRole("ADMIN","USER")
                .antMatchers("/").hasAnyRole("USER","ADMIN")
                .and().formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/index.html", true)
                .and()
                .logout()
                .permitAll()
                .and()
                .headers().frameOptions().sameOrigin();

    }
}