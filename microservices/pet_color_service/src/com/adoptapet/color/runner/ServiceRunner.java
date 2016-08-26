/**
 * Project Name: pet_color_service
 * Package Name: com.adoptapet.color.runner
 * Class Name: ServiceRunner.java
 * Description:
 *
 *
 *
 * 
 * 
 * Copyright to Treselle
 */
package com.adoptapet.color.runner;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.adoptapet.color.constants.ApplicationConstants;
import com.adoptapet.utilities.utils.PropertyUtil;

@SpringBootApplication
@ComponentScan(basePackages = { "com.adoptapet.color.controller", "com.adoptapet.color.service" })
public class ServiceRunner {

    public ServiceRunner() {
        try {
            PropertyUtil.init(ServiceRunner.class.getResourceAsStream(ApplicationConstants.PROPERTY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceRunner.class, args);
    }

}
