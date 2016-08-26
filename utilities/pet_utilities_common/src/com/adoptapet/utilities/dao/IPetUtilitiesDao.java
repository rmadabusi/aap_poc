/**
 * Project Name: pet_utilities_common
 * Package Name: com.adoptapet.utilities.dao
 * Class Name: IPetUtilitiesDao.java
 * Description:
 *
 *
 * Created Date:Aug 24, 2016
 * Modified Date:Aug 24, 2016
 *
 * Copyright to Treselle
 */
package com.adoptapet.utilities.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IPetUtilitiesDao {

    /**
     * Get status of Elastic Search
     * 
     * @return object
     *         Aug 24, 2016
     */
    Object getStatus();

    /**
     * Create Index for given name.
     * 
     * @param index
     * @return object
     *         Aug 24, 2016
     */
    Object createIndex(String index, String... types);

    /**
     * Delete Index for given name.
     * 
     * @param index
     * @return object
     *         Aug 24, 2016
     */
    Object deleteIndex(String... index);

    /**
     * Load list of documents into Index for given name for given type.
     * 
     * @param index
     * @param type
     * @param documents
     * @return
     *         Aug 24, 2016
     */
    Object insertDocuments(String index, String type, Collection<Map<String, Object>> documents);

    /**
     * Get list of color details for given color ids
     * 
     * @param ids
     * @return object
     *         Aug 24, 2016
     */
    Object getColors(List<Integer> ids);

    /**
     * Get list of Families details for given family ids
     * 
     * @param ids
     * @return
     *         Aug 24, 2016
     */
    Object getFamilies(List<Integer> ids);

    /**
     * Get list of Families details for given family ids
     * 
     * @param ids
     * @return
     *         Aug 24, 2016
     */
    Object getClanDetails(List<Integer> ids);

}
