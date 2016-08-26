/**
 * Project Name: pet_usagemetric_service
 * Package Name: com.adoptapet.usagemetric.api.runner
 * Class Name: ServiceRunner.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.usagemetric.runner;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.adoptapet.utilities.utils.PropertyUtil;

@SpringBootApplication
@ComponentScan(basePackages = { "com.adoptapet.usagemetric.controller", "com.adoptapet.usagemetric.service" })
public class ServiceRunner {

    public static final String PROPERTY_FILE = "/application.properties";

    public static void main(String[] args) {
        SpringApplication.run(ServiceRunner.class, args);
    }

    public ServiceRunner() {
        try {
            PropertyUtil.init(ServiceRunner.class.getResourceAsStream(ServiceRunner.PROPERTY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
