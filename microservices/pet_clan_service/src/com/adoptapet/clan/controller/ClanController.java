/**
 * Project Name: pet_clan_service
 * Package Name: com.adoptapet.clan..api.controller
 * Class Name: PetUtilitiesController.java
 * Description:
 * 
 * 
 * 
 * 
 * 
 * Copyright to Treselle
 */
package com.adoptapet.clan.controller;

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

import com.adoptapet.clan.constants.ApplicationConstants;
import com.adoptapet.clan.service.IPetClanService;

@RestController
public class ClanController {

    /**
     * LOGGER
     */
    public static final Logger LOG = Logger.getLogger(ClanController.class);

    @Autowired
    private IPetClanService    petClanService;

    /**
     * Clan Service having clan ID as the parameter. This service should display the details of the given clan ID.
     * 
     * @param ids
     * @return
     */
    @RequestMapping(value = "/pets/clans/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public Object getClans(@PathVariable("id") List<Integer> ids) {
        ClanController.LOG.info("/pets/clans/ service initialized for " + ids);
        long start = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put(ApplicationConstants.RESPONSE, this.petClanService.getClans(ids));
        } catch (Exception e) {
            response.put(ApplicationConstants.ERROR, e.getMessage());
            ClanController.LOG.error("Exception occured at /pets/clan/ " + e.getMessage());
        }

        long end = System.currentTimeMillis();
        ClanController.LOG.info("/pets/clans/ service initialized for " + ids + " executed, time taken: " + (end - start));
        return response;
    }

    /**
     * This service is used to get the status of the service. If the service is alive it returns true. Implementation in Utility class.
     * 
     * @return
     */

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public Object getStatus() {
        ClanController.LOG.info("/status service initialized ");
        long start = System.currentTimeMillis();

        Map<String, Object> response = new HashMap<String, Object>();
        try {
            response.put(ApplicationConstants.STATUS, this.petClanService.getStatus());
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put(ApplicationConstants.CODE, 500);
            error.put(ApplicationConstants.MSG, e.getMessage());
            error.put(ApplicationConstants.DETAILS, e.getMessage());
            response.put(ApplicationConstants.ERROR, error);
            ClanController.LOG.error("Exception occured at /status " + e.getMessage());
        }

        long end = System.currentTimeMillis();
        ClanController.LOG.info("/status service initialized executed, time taken: " + (end - start));
        return response;
    }
}
