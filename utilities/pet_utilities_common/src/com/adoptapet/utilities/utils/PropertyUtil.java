/**
 * Project Name: pet_utilities_common
 * Package Name: com.adoptapet.utilities.utils
 * Class Name: PropertyUtil.java
 * Description:
 *
 *
 * Created Date:Aug 24, 2016
 * Modified Date:Aug 24, 2016
 *
 * Copyright to Treselle
 */
package com.adoptapet.utilities.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.adoptapet.utilities.constants.StringConstants;

public class PropertyUtil {

    private static final Logger LOG   = Logger.getLogger(PropertyUtil.class);
    private static Properties   props = new Properties();;

    static {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load all default properties.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     *             Aug 11, 2015
     */
    public static void init() throws FileNotFoundException, IOException {
        try {
            props.load(PropertyUtil.class.getResourceAsStream("/default.properties"));
        } catch (Exception e) {
            LOG.info("unable to load default properties");
        }
    }

    /**
     * Load the given property file.
     * 
     * @param propertyLoc
     * @throws IOException
     *             Aug 11, 2015
     */
    public static void init(File propertyLoc) throws IOException {
        if (propertyLoc == null) {
            throw new IOException("InputStream is unll");
        }
        LOG.info("loading...." + propertyLoc);
        props.load(new FileInputStream(propertyLoc));
    }

    /**
     * Load all the file with extension of .properties.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     *             Aug 11, 2015
     */
    public static void init(InputStream is) throws IOException {
        LOG.info("Initialize property loading.." + is);
        if (is == null) {
            throw new IOException("InputStream is unll");
        }
        props.load(is);
    }

    public static String getValue(String key) {
        return getValue(key, StringConstants.EMPTY);
    }

    public static void clear() {
        props.clear();
    }

    /**
     * Return value from property if value is not valid, return defaultValue.
     * 
     * @param key
     * @param defaultValue
     * @return
     *         Apr 11, 2016
     */
    public static String getValue(String key, String defaultValue) {
        if (props.keySet().size() == 0) {
            try {
                init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String value = props.getProperty(key, defaultValue);
        if (!StringUtils.isValidString(value)) {
            value = defaultValue;
        }
        return value.trim();
    }

}
