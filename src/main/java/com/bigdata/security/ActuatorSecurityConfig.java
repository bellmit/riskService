//package com.bigdata.security;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * Desciption SpringSecurity定制SpringBoot-Actuator
// * Create By  li.bo
// * CreateTime 2018/3/14 16:24
// * UpdateTime 2018/3/14 16:24
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Value("${management.context-path}")
//    private String contextPath;
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////
////        http.csrf().disable();
////        http.authorizeRequests()
////                .antMatchers(contextPath.concat("/**")).authenticated()
////                .anyRequest().permitAll()
////                .and().httpBasic();
////    }
//
////    @Override
////    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
////    }
//
//    private static final String[] AUTH_WHITELIST = {
//            // -- swagger ui
//            "/v2/api-docs",
//            "/swagger-resources",
//            "/swagger-resources/**",
//            "/configuration/ui",
//            "/configuration/security",
//            "/swagger-ui.html",
//            "/webjars/**"
//            // other public endpoints of your API may be appended to this array
//    };
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                // ... here goes your custom security configuration
//                        authorizeRequests().
//                antMatchers(AUTH_WHITELIST).permitAll().  // whitelist Swagger UI resources
//                // ... here goes your custom security configuration
//                        antMatchers("/**").authenticated();  // require authentication for any endpoint that's not whitelisted
//    }
//}
