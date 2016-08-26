/**
 * Project Name: pet_family_service
 * Package Name: com.adoptapet.family..api.service
 * Class Name: IPetUtilitiesService.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.family.service;

import java.util.List;

public interface IPetFamilyService {
    /**
     * This method gets the family details for the given family id.
     * 
     * @param ids
     * @return
     */
    Object getFamilies(List<Integer> ids);

    /**
     * This method is used to get Status of the service.
     * 
     * @return object
     * 
     */
    Object getStatus();
}
