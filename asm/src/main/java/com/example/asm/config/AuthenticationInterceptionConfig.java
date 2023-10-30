package com.example.asm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.asm.interceptor.AdminAuthenticationInterceptor;



@Configuration
public class AuthenticationInterceptionConfig implements WebMvcConfigurer {
    @Autowired
    private AdminAuthenticationInterceptor adminAuthenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminAuthenticationInterceptor)
                .addPathPatterns("/dashboard/*", "/cart/checkout/**","/checkout/**","/dashboard/orders/detail/*","/dashboard/accounts/**",
                "/dashboard/categories/**","/dashboard/products/create/**", "/dashboard/products/editAcc/**", "/dashboard/orders/edit/**"
                );
                
    }

    // public void addViewControllers(ViewControllerRegistry registry) {
	// 	registry.addViewController("/shop").setViewName("shop");
	
	// }
}
