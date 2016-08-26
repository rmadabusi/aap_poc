/**
 * Project Name: pet_clan_service
 * Package Name: com.adoptapet.clan..api.constants
 * Class Name: ApplicationConstants.java
 * Description:
 *
 *
 * 
 * 
 *
 * Copyright to Treselle
 */
package com.adoptapet.clan.constants;

/**
 * Constants Class to declare all Constant Variables
 * 
 * @author User
 *
 */
public class ApplicationConstants {

    /**
     * Prefix Variable to be used in the URL
     */
    public static final String API_PREFIX_STRING         = "/v3/*";

    /**
     * Context Class
     */
    public static final String CONTEXT_INITIALIZER_CLASS = "org.springframework.web.context.ContextLoaderListener";

    /**
     * Properties file constant
     */
    public static final String PROPERTY_FILE             = "/application.properties";

    /** Response Fields */
    public static final String RESPONSE                  = "response";
    public static final String ERROR                     = "error";
    public static final String MSG                       = "msg";
    public static final String CODE                      = "code";
    public static final String DETAILS                   = "details";
    public static final String STATUS                    = "status";
    public static final String INITIALIZED               = "initialized";
    public static final String DB_HEALTHY                = "db_healthy";
    /** Response Fields */

    /** ElasticSearch default values */
    public static final String ES_DEFUALT_HOST           = "localhost";
    public static final String ES_DEFUALT_PORT           = "9300";
    public static final String ES_DEFUALT_CLUSTER_NAME   = "elasticsearch";
    /** ElasticSearch default values */

    /** ElasticSearch property key */
    public static final String ES_HOST_PROPERTY          = "es.hostname";
    public static final String ES_PORT_PROPERTY          = "es.port";
    public static final String ES_CLUSTER_NAME_PROPERTY  = "es.cluster.name";
    /** ElasticSearch property key */

}