package com.qorakol.ilm.ziyo.config;

import com.qorakol.ilm.ziyo.constant.SecurityConstants;
//import com.qorakol.ilm.ziyo.security.AuthenticationFilter;
import com.qorakol.ilm.ziyo.security.AuthorizationFilter;
import com.qorakol.ilm.ziyo.service.interfaces.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import(SwaggerConfig.class)
public class WebConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthService authService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Autowired
    public WebConfiguration(AuthService authService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authService = authService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        I resolved this issue by configuring the HSTS header as follows:
        http.headers().httpStrictTransportSecurity()
                .maxAgeInSeconds(0)
                .includeSubDomains(true);
        http
                .csrf().disable()

                .authorizeRequests()

                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/auth/check_login")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/auth/image")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/auth/login")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/static/**")
                .permitAll()
                .antMatchers("/static/only_post")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/a23d_m23_i23n/add_image")
                .permitAll()
//               .antMatchers( "/users/**").hasRole(ApplicationUserRole.USER.name())

                .anyRequest()
                .authenticated()
                .and()

//                .cors()
//                .and()

                .cors().disable()

//                .addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(bCryptPasswordEncoder);
    }

//    public AuthenticationFilter getAuthenticationFilter() throws Exception{
//        final AuthenticationFilter filter=new AuthenticationFilter(authenticationManager(), authService);
//        filter.setFilterProcessesUrl("/auth/login");
////        filter.setPostOnly(true);
//        return filter;
//    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Collections.singletonList("*")); // <-- you may change "*"
//        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
//        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList(
//                "Accept", "Origin", "Content-Type", "Depth", "User-Agent", "If-Modified-Since,",
//                "Cache-Control", "Authorization", "X-Req", "X-File-Size", "X-Requested-With", "X-File-Name"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
