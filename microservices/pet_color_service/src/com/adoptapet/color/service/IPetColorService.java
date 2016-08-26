/**
 * Project Name: pet_color_service
 * Package Name: com.adoptapet.color..api.service
 * Class Name: IPetUtilitiesService.java
 * Description:
 *
 *


 *
 * Copyright to Treselle
 */
package com.adoptapet.color.service;

import java.util.List;

public interface IPetColorService {

    Object getColors(List<Integer> ids);

    /**
     * @return object Aug 24, 2016
     */
    Object getStatus();
}
