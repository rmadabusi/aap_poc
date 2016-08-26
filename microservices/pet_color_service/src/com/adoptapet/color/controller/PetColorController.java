/**
 * Project Name: pet_color_service
 * Package Name: com.adoptapet.color..api.controller
 * Class Name: PetUtilitiesController.java
 * Description:
 *
 *
 *
 * 
 * 
 * Copyright to Treselle
 */
package com.adoptapet.color.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.adoptapet.color.constants.ApplicationConstants;
import com.adoptapet.color.service.IPetColorService;

@RestController
public class PetColorController {

    /**
     * LOGGER
     */
    public static final Logger LOG = Logger.getLogger(PetColorController.class);

    @Autowired
    private IPetColorService   petColorService;

    /**
     * Pet Color Service having Color ID as the parameter. This service should
     * display the details of the given Color ID.
     * 
     * @param ids
     * @return
     */

    @RequestMapping(value = "/pets/colors/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public Object getColors(@PathVariable("id") List<Integer> ids) {
        PetColorController.LOG.info("/pets/colors/ service initialized for " + ids);
        long start = System.currentTimeMillis();

        System.err.println(">>>>  " + ids);

        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put(ApplicationConstants.RESPONSE, this.petColorService.getColors(ids));
        } catch (Exception e) {
            response.put(ApplicationConstants.ERROR, e.getMessage());
            PetColorController.LOG.error("Exception occured at /pets/colors/ " + e.getMessage());
        }

        long end = System.currentTimeMillis();
        PetColorController.LOG.info("/pets/colors/ service initialized for " + ids + " executed, time taken: " + (end - start));
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
        PetColorController.LOG.info("/status service initialized ");
        long start = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put(ApplicationConstants.STATUS, this.petColorService.getStatus());
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put(ApplicationConstants.CODE, 500);
            error.put(ApplicationConstants.MSG, e.getMessage());
            error.put(ApplicationConstants.DETAILS, e.getMessage());
            response.put(ApplicationConstants.ERROR, error);
            PetColorController.LOG.error("Exception occured at /status " + e.getMessage());
        }

        long end = System.currentTimeMillis();
        PetColorController.LOG.info("/status service initialized executed, time taken: " + (end - start));
        return response;
    }
}
