/**
 * Project Name: pet_utilities_common
 * Package Name: com.adoptapet.utilities.utils
 * Class Name: StringUtils.java
 * Description:
 *
 *
 * Created Date:Aug 24, 2016
 * Modified Date:Aug 24, 2016
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
     *         Aug 11, 2015
     */
    public static boolean isValidString(String str) {
        return (str != null) && (!str.trim().isEmpty());
    }

}
