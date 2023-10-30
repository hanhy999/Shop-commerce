// package com.example.asm.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;


// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//     // @Autowired
//     // UserDetailsService userDetailsService;

//     @Bean
//     public UserDetailsService userDetailsService() {
//         return new ShopUserDetailsService();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public PasswordEncoder PasswordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public DaoAuthenticationProvider authenticationProvider() {
//         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

//         authProvider.setUserDetailsService(userDetailsService());
//         authProvider.setPasswordEncoder(passwordEncoder());

//         return authProvider;
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http
//                 .authorizeRequests()
//                 .antMatchers("/admin/users/**","/admin/dashboard/**",
//                         "/admin/category/**","/admin/brand/**","/admin/product/**").hasAuthority("admin")
//                 .anyRequest().authenticated()
//                 .and()
//                 .formLogin()
//                 .usernameParameter("email")
//                 .loginPage("/shop/login")
//                 .permitAll(); // To change body of generated methods, choose Tools | Templates.
//     }

//     @Override
//     public void configure(WebSecurity web) throws Exception {
//         web.ignoring().antMatchers("/adminss/**"); // To change body of generated methods, choose Tools | Templates.
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.authenticationProvider(authenticationProvider()); 
//     }



// }