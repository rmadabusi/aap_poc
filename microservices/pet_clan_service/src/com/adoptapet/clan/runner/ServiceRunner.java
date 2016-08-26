/**
 * Project Name: pet_clan_service
 * Package Name: com.adoptapet.clan.runner
 * Class Name: ServiceRunner.java
 * Description:
 * 
 * 
 * 
 * 
 * 
 * Copyright to Treselle
 */
package com.adoptapet.clan.runner;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.adoptapet.clan.constants.ApplicationConstants;
import com.adoptapet.utilities.utils.PropertyUtil;

@SpringBootApplication
@ComponentScan(basePackages = { "com.adoptapet.clan.configuration", "com.adoptapet.clan.controller", "com.adoptapet.clan.service" })
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
