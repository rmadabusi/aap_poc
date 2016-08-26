/**
 * Project Name: pet_usagemetric_service
 * Package Name: com.adoptapet.usagemetric.api.controller
 * Class Name: PetUsageMetricController.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.usagemetric.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adoptapet.usagemetric.constants.ApplicationConstants;
import com.adoptapet.usagemetric.service.IPetUsageMetricService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class PetUsageMetricController {

    /**
     * LOGGER
     */
    public static final Logger     LOG = Logger.getLogger(PetUsageMetricController.class);

    @Autowired
    private IPetUsageMetricService petUsageMetricService;

    /**
     * Method  usageMetrics is used to store Usage Metrics details from API Gateway.
     * 
     * @param body
     * @return
     * 
     */
    @RequestMapping(value = "/pets/usagemetrics", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    public Object usageMetric(@RequestBody String body) {
        PetUsageMetricController.LOG.info("/pets/usagemetrics/ service initialized for ");
        long start = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<String, Object>();
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> result = new ObjectMapper().readValue(body.toString(), HashMap.class);

            response.put(ApplicationConstants.RESPONSE, this.petUsageMetricService.usageMetric(result));
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put(ApplicationConstants.CODE, 404);
            error.put(ApplicationConstants.MSG, e.getMessage());
            error.put(ApplicationConstants.DETAILS, e.getMessage());
            response.put(ApplicationConstants.ERROR, error);
            PetUsageMetricController.LOG.error("Exception occured at /pets/usagemetric/ " + e.getMessage());
        }

        long end = System.currentTimeMillis();
        PetUsageMetricController.LOG.info("/pets/usagemetric/ service initialized for  executed, time taken: " + (end - start));
        return response;
    }

    /**
     * This service is used to get the status of the service. If the service is
     * alive it returns true. Implementation in Utility class.
     * 
     * @return
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Object getStatus() {
        PetUsageMetricController.LOG.info("/status service initialized ");
        long start = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put(ApplicationConstants.STATUS, this.petUsageMetricService.getStatus());
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put(ApplicationConstants.CODE, 500);
            error.put(ApplicationConstants.MSG, e.getMessage());
            error.put(ApplicationConstants.DETAILS, e.getMessage());
            response.put(ApplicationConstants.ERROR, error);
            PetUsageMetricController.LOG.error("Exception occured at /status " + e.getMessage());
        }

        long end = System.currentTimeMillis();
        PetUsageMetricController.LOG.info("/status service initialized executed, time taken: " + (end - start));
        return response;
    }

}
