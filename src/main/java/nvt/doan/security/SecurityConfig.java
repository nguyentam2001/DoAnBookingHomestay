package nvt.doan.security;


import lombok.AllArgsConstructor;
import nvt.doan.service.account.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private final AccountService accountService;
    private final AuthenticationEntryPointCustom authenticationEntryPointCustom;
    private final AuthorizationFilterCustom authorizationFilterCustom;
    private final AccessDeniedHandlerCustom accessDeniedHandlerCustom;



    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/css/**","/fonts/**","/images/**","/js/**","/api/v1/booking/**")
                .permitAll()
                .antMatchers("/view/users/index","/view/users/search","/view/users/search/rooms","/view/register","/api/v1/auth/**")//user
                .permitAll()
                .antMatchers("/api/v1/auth/**","/pay/**","/api/v1/promotion/**","/api/v1/user/**")
                .permitAll()
                .antMatchers("/api/v1/**")
                .hasRole("ADMIN")
                .antMatchers("/view/users/favourites","/api/v1/users/pay/**",
                        "/api/v1/users/rate/**","/api/v1/users/booking/**",
                        "/api/v1/users/account/**","/api/v1/dashboard/**").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/admin")
                .defaultSuccessUrl("/view/users/index")
                .permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/admin")
                .and()
                .logout()
                .logoutUrl("/logout")
//                    .logoutUrl("/logout-handle")
                .invalidateHttpSession(true)
                .deleteCookies("JWT_COOKIE", "JSESSIONID")
//                    .logoutSuccessHandler((new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK)))
                .logoutSuccessUrl("/admin")
                .permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authorizationFilterCustom, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("targetUrl");
        successHandler.setDefaultTargetUrl("/");
        return successHandler;
    }

}
