package nvt.doan.security;


import lombok.AllArgsConstructor;
import nvt.doan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    @Autowired
    AccountService accountService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //use jdbc authentication ... oh year!!!
        auth.userDetailsService(accountService);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("api/v1/auth","/css/**","/fonts/**","/images/**","/js/**")
                .permitAll()
                .antMatchers("/view/**","/api/v1/**")
                .hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/admin")
                .permitAll()
                .and()
                .csrf().disable();
    }
}
