/**
 * Project Name: pet_utilities_common
 * Package Name: com.adoptapet.utilities.loader
 * Class Name: MetaDataLoader.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.utilities.loader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import com.adoptapet.utilities.constants.ApplicationConstants;
import com.adoptapet.utilities.dao.IPetUtilitiesDao;
import com.adoptapet.utilities.dao.impl.PetUtilitiesDao;
import com.opencsv.CSVReader;

/**
 * MetaDataLoader class used for DB CRUD opertions.
 * 
 * @author Treselle Systems
 * @version 1.0
 * @since 1.0 (the version of the package this class was first added to)
 */

public class MetaDataLoader {

    private static final Logger LOG = Logger.getLogger(MetaDataLoader.class);

    /**
     * @param args
     * 
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        MetaDataLoader.LOG.info("Initialize MetaDataLoader");
        try {
            MetaDataLoader loader = new MetaDataLoader();
            String hostName = "localhost";
            int port = 9300;
            String clusterName = "elasticsearch";
            IPetUtilitiesDao dao = new PetUtilitiesDao(hostName, port, clusterName);

            dao.deleteIndex(ApplicationConstants.INDEX_NAME);
            dao.createIndex(ApplicationConstants.INDEX_NAME, ApplicationConstants.TYPES);
            for (String type : ApplicationConstants.TYPES) {
                Set<Map<String, Object>> documents = loader.readValues(ApplicationConstants.FILE_PATH + type + ApplicationConstants.FILE_FORMAT);
                dao.insertDocuments(ApplicationConstants.INDEX_NAME, type, documents);
            }

            List<Integer> list = new ArrayList<>();
            list.add(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        MetaDataLoader.LOG.info("completed MetaDataLoader, time taken: " + (end - start));
    }

    /**
     * Read key/value pair values from CSV file.
     * 
     * @param type
     * @return Set - list of key/values.
     * 
     */
    public Set<Map<String, Object>> readValues(String fileName) {
        Set<Map<String, Object>> values = new HashSet<Map<String, Object>>();
        Map<String, Object> attributeContainer;

        try {
            // File reading using csv reader
            CSVReader clans = new CSVReader(new FileReader(fileName), ',', '"');
            List<String[]> csvDatas = clans.readAll();
            clans.close();
            // header reading
            String[] headerKeys = csvDatas.get(0);
            csvDatas.remove(0);

            for (String[] clanValues : csvDatas) {
                attributeContainer = new HashMap<String, Object>();
                for (int index = 0; index < clanValues.length; index++) {
                    if (headerKeys[index].endsWith("_id")) {
                        try {
                            attributeContainer.put(headerKeys[index], Integer.parseInt(clanValues[index]));
                        } catch (Exception e) {
                            attributeContainer.put(headerKeys[index], clanValues[index]);
                        }
                    } else if (headerKeys[index].equals("active_p") && clanValues[index].equals("t")) {
                        attributeContainer.put(headerKeys[index], true);
                    } else {
                        attributeContainer.put(headerKeys[index], clanValues[index]);
                    }
                }
                values.add(attributeContainer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return values;
    }

}
