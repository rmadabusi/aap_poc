/**
 * Project Name: pet_family_service
 * Package Name: com.adoptapet.family.runner
 * Class Name: ServiceRunner.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.family.runner;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.adoptapet.family.constants.ApplicationConstants;
import com.adoptapet.utilities.utils.PropertyUtil;

@SpringBootApplication
@ComponentScan(basePackages = { "com.adoptapet.family.controller", "com.adoptapet.family.service" })
public class ServiceRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRunner.class, args);
    }

    /**
     * Load Properties Files
     */
    public ServiceRunner() {
        try {
            PropertyUtil.init(this.getClass().getResourceAsStream(ApplicationConstants.PROPERTY_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
