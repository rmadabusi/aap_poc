/**
 * Project Name: pet_usagemetric_service
 * Package Name: com.adoptapet.usagemetric.api.service.impl
 * Class Name: PetUsageMetricService.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.usagemetric.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.adoptapet.usagemetric.constants.ApplicationConstants;
import com.adoptapet.usagemetric.service.IPetUsageMetricService;
import com.adoptapet.utilities.dao.IPetUtilitiesDao;
import com.adoptapet.utilities.dao.impl.PetUtilitiesDao;
import com.adoptapet.utilities.utils.PropertyUtil;

@Service
public class PetUsageMetricService implements IPetUsageMetricService {

    public static final String USAGEMETRICS_INDEX_NAME = "usagemetrics";
    public static final String LOG_INDEX_NAME          = "log";
    public static final String DEFAULT_INDEX_TYPE      = "default";

    private IPetUtilitiesDao   petUtilitiesDao;

    /**
     * PetUsageMetricService constructor is used to connect to Elastic Search
     * Database keeping the 2nd arguments as default. PetUtilitiesDao java class
     * is used to connect to Elastic Search
     */
    public PetUsageMetricService() {

        String hostName = PropertyUtil.getValue(ApplicationConstants.ES_HOST_PROPERTY, ApplicationConstants.ES_DEFUALT_HOST);
        int port = Integer.parseInt(PropertyUtil.getValue(ApplicationConstants.ES_PORT_PROPERTY, ApplicationConstants.ES_DEFUALT_PORT));
        String clusterName = PropertyUtil.getValue(ApplicationConstants.ES_CLUSTER_NAME_PROPERTY, ApplicationConstants.ES_DEFUALT_CLUSTER_NAME);
        try {
            this.petUtilitiesDao = new PetUtilitiesDao(hostName, port, clusterName);

            // creating index for required indicies
            this.petUtilitiesDao.createIndex(PetUsageMetricService.USAGEMETRICS_INDEX_NAME, PetUsageMetricService.DEFAULT_INDEX_TYPE);
            this.petUtilitiesDao.createIndex(PetUsageMetricService.LOG_INDEX_NAME, PetUsageMetricService.DEFAULT_INDEX_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to know the status of the service. Returns true when
     * the service is alive.
     * 
     * @return object
     */

    @Override
    public Object getStatus() {
        Map<String, Object> response = new HashMap<>();
        response.put(ApplicationConstants.INITIALIZED, true);
        response.put(ApplicationConstants.DB_HEALTHY, this.petUtilitiesDao.getStatus());
        return response;
    }

    /**
     * This method is designed to insert UsageMetrics provide by the API Gateway
     * 
     * @param map
     * @return
     */
    @Override
    public Object usageMetric(Map<String, Object> map) {
        List<Map<String, Object>> usageMetricsList = new ArrayList<>();
        usageMetricsList.add(map);
        return this.petUtilitiesDao.insertDocuments(PetUsageMetricService.USAGEMETRICS_INDEX_NAME, PetUsageMetricService.DEFAULT_INDEX_TYPE, usageMetricsList);
    }
}
