/**
 * Project Name: pet_clan_service
 * Package Name: com.adoptapet.clan..api.service.impl
 * Class Name: PetUtilitiesService.java
 * Description:
 * 
 * 
 * 
 * 
 * 
 * Copyright to Treselle
 */
package com.adoptapet.clan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.adoptapet.clan.constants.ApplicationConstants;
import com.adoptapet.clan.service.IPetClanService;
import com.adoptapet.utilities.dao.IPetUtilitiesDao;
import com.adoptapet.utilities.dao.impl.PetUtilitiesDao;
import com.adoptapet.utilities.utils.PropertyUtil;

@Service
public class PetClanService implements IPetClanService {

    private IPetUtilitiesDao petUtilitiesDao;

    /**
     * PetClanService constructor is used to connect to Elastic Search
     * Database keeping the 2nd arguments as default. PetUtilitiesDao java class
     * is used to connect to Elastic Search
     */
    public PetClanService() {
        String hostName = PropertyUtil.getValue(ApplicationConstants.ES_HOST_PROPERTY, ApplicationConstants.ES_DEFUALT_HOST);
        int port = Integer.parseInt(PropertyUtil.getValue(ApplicationConstants.ES_PORT_PROPERTY, ApplicationConstants.ES_DEFUALT_PORT));
        String clusterName = PropertyUtil.getValue(ApplicationConstants.ES_CLUSTER_NAME_PROPERTY, ApplicationConstants.ES_DEFUALT_CLUSTER_NAME);
        try {
            this.petUtilitiesDao = new PetUtilitiesDao(hostName, port, clusterName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.adoptapet.clan..api.service.IPetUtilitiesService#getColors(java
     * .util.List)
     */
    @Override
    public Object getClans(List<Integer> ids) {
        return this.petUtilitiesDao.getClanDetails(ids);
    }

    /*
     * (non-Javadoc) getStatus method is used to return true if the service is
     * available. Based on the return value we should know the status of the
     * service
     * 
     * @see com.adoptapet.clan..api.service.IPetUtilitiesService#getStatus()
     */
    @Override
    public Object getStatus() {
        Map<String, Object> response = new HashMap<>();
        response.put(ApplicationConstants.INITIALIZED, true);
        response.put(ApplicationConstants.DB_HEALTHY, this.petUtilitiesDao.getStatus());
        return response;
    }

}
