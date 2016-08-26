/**
 * Project Name: pet_utilities_common
 * Package Name: com.adoptapet.utilities.utils
 * Class Name: PropertyUtil.java
 * Description:
 *
 *
 * 
 * 
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

/**
 * PropertyUtil Utilities error descriptions and other common methods implementation.
 * 
 * @author Treselle Systems
 * @version 1.0
 * @since 1.0 (the version of the package this class was first added to)
 */
public class PropertyUtil {

    private static final Logger LOG   = Logger.getLogger(PropertyUtil.class);
    private static Properties   props = new Properties();                     ;

    static {
        try {
            PropertyUtil.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clear() {
        PropertyUtil.props.clear();
    }

    public static String getValue(String key) {
        return PropertyUtil.getValue(key, StringConstants.EMPTY);
    }

    /**
     * Return value from property if value is not valid, return defaultValue.
     * 
     * @param key
     * @param defaultValue
     * @return Apr 11, 2016
     */
    public static String getValue(String key, String defaultValue) {
        if (PropertyUtil.props.keySet().size() == 0) {
            try {
                PropertyUtil.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String value = PropertyUtil.props.getProperty(key, defaultValue);
        if (!StringUtils.isValidString(value)) {
            value = defaultValue;
        }
        return value.trim();
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
            PropertyUtil.props.load(PropertyUtil.class.getResourceAsStream("/default.properties"));
        } catch (Exception e) {
            PropertyUtil.LOG.info("unable to load default properties");
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
        PropertyUtil.LOG.info("loading...." + propertyLoc);
        PropertyUtil.props.load(new FileInputStream(propertyLoc));
    }

    /**
     * Load all the file with extension of .properties.
     * 
     * @throws FileNotFoundException
     * @throws IOException
     *             Aug 11, 2015
     */
    public static void init(InputStream is) throws IOException {
        PropertyUtil.LOG.info("Initialize property loading.." + is);
        if (is == null) {
            throw new IOException("InputStream is unll");
        }
        PropertyUtil.props.load(is);
    }

}
