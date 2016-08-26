/**
 * Project Name: pet_usagemetric_service
 * Package Name: com.adoptapet.usagemetric.api.service
 * Class Name: IPetUsageMetricService.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.usagemetric.service;

import java.util.Map;

public interface IPetUsageMetricService {
    /**
     * This method is used to know the status of the service. Returns true when
     * the service is alive.
     * 
     * @return object
     */
    Object getStatus();

    /**
     * This method is designed to insert UsageMetrics provide by the API Gateway
     * 
     * @param map
     * @return
     */
    Object usageMetric(Map<String, Object> map);

}
