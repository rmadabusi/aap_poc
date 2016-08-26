package com.adoptapet.utilities.constants;

public class ApplicationConstants {

    public static final String   INDEX_MAPPING_CONF_JSON = "{ \"dynamic_templates\": [{ \"all\": { \"path_match\": \"*\", \"mapping\": { \"index_name\": \"{name}\",\"index\": \"not_analyzed\" } } }] }";
    public static final String   FILE_PATH               = "inputs/";
    public static final String   FILE_FORMAT             = ".csv";
    public static final String   INDEX_NAME              = "metadata";
    public static final String[] TYPES                   = { "clan", "color", "family" };
    public static final int      DEFAULT_MAX_RESULT      = 10000;

}
