/**
 * Project Name: pet_color_service
 * Package Name: com.adoptapet.color..api.service.impl
 * Class Name: PetUtilitiesService.java
 * Description:
 *
 *
 *
 * 
 * 
 * Copyright to Treselle
 */
package com.adoptapet.color.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.adoptapet.color.constants.ApplicationConstants;
import com.adoptapet.color.service.IPetColorService;
import com.adoptapet.utilities.dao.IPetUtilitiesDao;
import com.adoptapet.utilities.dao.impl.PetUtilitiesDao;
import com.adoptapet.utilities.utils.PropertyUtil;

@Service
public class PetColorService implements IPetColorService {

    private IPetUtilitiesDao petUtilitiesDao;

    /**
     * PetColorService constructor is used to connect to Elastic Search
     * Database keeping the 2nd arguments as default. PetUtilitiesDao java class
     * is used to connect to Elastic Search
     */
    public PetColorService() {
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
     * com.adoptapet.color..api.service.IPetUtilitiesService#getColors(java
     * .util.List)
     * 
     * Aug 24, 2016
     */
    @Override
    public Object getColors(List<Integer> ids) {
        return this.petUtilitiesDao.getColors(ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.adoptapet.color..api.service.IPetUtilitiesService#getStatus()
     * 
     * Aug 24, 2016
     */
    @Override
    public Object getStatus() {
        Map<String, Object> response = new HashMap<>();
        response.put(ApplicationConstants.INITIALIZED, true);
        response.put(ApplicationConstants.DB_HEALTHY, this.petUtilitiesDao.getStatus());
        return response;
    }

}
