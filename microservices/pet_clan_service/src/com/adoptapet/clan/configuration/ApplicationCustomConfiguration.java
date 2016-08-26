/**
 * Project Name: pet_clan_service
 * Package Name: com.adoptapet.clan.api.configuration
 * Class Name: ApplicationCustomConfiguration.java
 * Description:
 * 
 * 
 * 
 * 
 * 
 * Copyright to Treselle
 */
package com.adoptapet.clan.configuration;

import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.DispatcherServlet;

import com.adoptapet.clan.constants.ApplicationConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

public class ApplicationCustomConfiguration {

    /**
     * Create DispatcherServlet for customize the DispatcherServlet
     * 
     * @return
     */
    @Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setContextInitializerClasses(ApplicationConstants.CONTEXT_INITIALIZER_CLASS);
        return servlet;
    }

    /**
     * Customize the ServletRegistrationBean to append contextPath
     * API_PREFIX_STRING with all request mapping
     * 
     * @return
     */
    @Bean
    public ServletRegistrationBean dispatcherServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(this.dispatcherServlet(), ApplicationConstants.API_PREFIX_STRING);
        registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
        return registration;
    }

    /**
     * Bean method to convert JSONObject response to SNAKE_CASE for common
     * response
     * 
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.propertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        return builder;
    }
}
