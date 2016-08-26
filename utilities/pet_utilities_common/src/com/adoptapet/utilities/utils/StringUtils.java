/**
 * Project Name: pet_utilities_common
 * Package Name: com.adoptapet.utilities.utils
 * Class Name: StringUtils.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.utilities.utils;

public class StringUtils {

    /**
     * Validate the string with not null, is not empty
     * 
     * @param str
     * @return
     */
    public static boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }

}
