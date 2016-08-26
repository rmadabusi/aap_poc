/**
 * Project Name: pet_clan_service
 * Package Name: com.adoptapet.clan..api.service
 * Class Name: IPetUtilitiesService.java
 * Description:
 * 
 * 
 * 
 * Copyright to Treselle
 */
package com.adoptapet.clan.service;

import java.util.List;

public interface IPetClanService {
    /**
     * This method gets the family details for the given family id.
     * 
     * @param ids
     * @return
     */
    Object getClans(List<Integer> ids);

    /**
     * This method is used to get Status of the service.
     * 
     * @return object
     * 
     */
    Object getStatus();
}
