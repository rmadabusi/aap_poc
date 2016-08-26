/**
 * Project Name: pet_utilities_common
 * Package Name: com.adoptapet.utilities.dao
 * Class Name: IPetUtilitiesDao.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.utilities.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * IPetUtilitiesDao Interface designed to get details methods from ES.
 * 
 * @author Treselle Systems
 * @version 1.0
 * @since 1.0 (the version of the package this class was first added to)
 */

public interface IPetUtilitiesDao {

    /**
     * Create Index for given name.
     * 
     * @param index
     * @return object
     * 
     */
    Object createIndex(String index, String... types);

    /**
     * Delete Index for given name.
     * 
     * @param index
     * @return object
     * 
     */
    Object deleteIndex(String... index);

    /**
     * Get list of Families details for given family ids
     * 
     * @param ids
     * @return
     * 
     */
    Object getClanDetails(List<Integer> ids);

    /**
     * Get list of color details for given color ids
     * 
     * @param ids
     * @return object
     * 
     */
    Object getColors(List<Integer> ids);

    /**
     * Get list of Families details for given family ids
     * 
     * @param ids
     * @return
     * 
     */
    Object getFamilies(List<Integer> ids);

    /**
     * Get status of Elastic Search
     * 
     * @return object Aug 24, 2016
     */
    Object getStatus();

    /**
     * Load list of documents into Index for given name for given type.
     * 
     * @param index
     * @param type
     * @param documents
     * @return
     * 
     */
    Object insertDocuments(String index, String type, Collection<Map<String, Object>> documents);

}
