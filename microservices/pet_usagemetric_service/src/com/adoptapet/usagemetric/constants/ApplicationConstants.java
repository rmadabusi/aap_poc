/**
 * Project Name: pet_usagemetric_service
 * Package Name: com.adoptapet.usagemetric.api.constants
 * Class Name: ApplicationConstants.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.usagemetric.constants;

public class ApplicationConstants {

    /**
     * Properties file constant
     */
    public static final String PROPERTY_FILE            = "/application.properties";

    /** Response Fields */
    public static final String RESPONSE                 = "response";
    public static final String ERROR                    = "error";
    public static final String MSG                      = "msg";
    public static final String CODE                     = "code";
    public static final String DETAILS                  = "details";
    public static final String STATUS                   = "status";
    public static final String INITIALIZED              = "initialized";
    public static final String DB_HEALTHY               = "db_healthy";
    /** Response Fields */

    /** ElasticSearch default values */
    public static final String ES_DEFUALT_HOST          = "localhost";
    public static final String ES_DEFUALT_PORT          = "9300";
    public static final String ES_DEFUALT_CLUSTER_NAME  = "elasticsearch";
    /** ElasticSearch default values */

    /** ElasticSearch property key */
    public static final String ES_HOST_PROPERTY         = "es.hostname";
    public static final String ES_PORT_PROPERTY         = "es.port";
    public static final String ES_CLUSTER_NAME_PROPERTY = "es.cluster.name";
    /** ElasticSearch property key */

}