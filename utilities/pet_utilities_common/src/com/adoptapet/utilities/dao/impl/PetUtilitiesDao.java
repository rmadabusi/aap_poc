/**
 * Project Name: pet_utilities_common
 * Package Name: com.adoptapet.utilities.dao.impl
 * Class Name: PetUtilitiesDao.java
 * Description:
 *
 *
 * Created Date:Aug 24, 2016
 * Modified Date:Aug 24, 2016
 *
 * Copyright to Treselle
 */
package com.adoptapet.utilities.dao.impl;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHitField;
import org.elasticsearch.search.SearchHits;

import com.adoptapet.utilities.dao.IPetUtilitiesDao;
import com.adoptapet.utilities.utils.StringUtils;

public class PetUtilitiesDao implements IPetUtilitiesDao {

    private static final Logger LOG                     = Logger.getLogger(PetUtilitiesDao.class);

// public static final String INDEX_MAPPING_CONF = "{ \"dynamic_templates\": [{ \"all\": { \"path_match\": \"*\", \"match_mapping_type\": \"string\",
// \"mapping\": { \"type\": \"string\", \"index_name\": \"{name}\",\"index\": \"not_analyzed\" } } }] }";
    public static final String  INDEX_MAPPING_CONF_JSON = "{ \"dynamic_templates\": [{ \"all\": { \"path_match\": \"*\", \"mapping\": { \"index_name\": \"{name}\",\"index\": \"not_analyzed\" } } }] }";
    private Client              client                  = null;

    /**
     * 
     */
    public PetUtilitiesDao(String hostName, int port, String clusterName) throws Exception {
        LOG.info("ElasticSearch Host: " + hostName);
        LOG.info("ElasticSearch Port: " + port);
        LOG.info("ElasticSearch Cluster: " + clusterName);
        LOG.info("ElasticSearch connection establishing");

        if (!StringUtils.isValidString(hostName)) {
            hostName = "localhost";
            LOG.info("Elastic Search Invalid Host, So set to default:  " + hostName);
        }
        if (port <= 0) {
            port = 9300;
            LOG.info("Elastic Search Invalid Port, So set to default:  " + port);
        }
        if (!StringUtils.isValidString(clusterName)) {
            clusterName = "elasticsearch";
            LOG.info("Elastic Search Invalid Cluster, So set to default:  " + clusterName);
        }

        Settings settings = Settings.settingsBuilder().put("cluster.name", clusterName).build();
        this.client = TransportClient.builder().settings(settings).build().addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(hostName, port)));

        LOG.info("ElasticSearch connection established");
    }

    /**
     * Check status of Elastic Search
     * 
     * @return object
     *         Aug 24, 2016
     */
    @Override
    public Object getStatus() {
        boolean flag = false;

        try {
            ClusterHealthRequest request = new ClusterHealthRequest();
            ClusterHealthResponse response = this.client.admin().cluster().health(request).actionGet();
            response.getClusterName();
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * Create Index for given name.
     * 
     * @param index
     * @return object
     *         Aug 24, 2016
     */
    @Override
    public Object createIndex(String index, String... types) {
        long start = System.currentTimeMillis();
        LOG.info("Create index initialized for: " + index);

        if (StringUtils.isValidString(index)) {
            try {
                CreateIndexRequest request = new CreateIndexRequest(index);
                if (types != null) {
                    for (String type : types) {
                        request.mapping(type, INDEX_MAPPING_CONF_JSON);
                    }
                } else {
                    request.mapping("all", INDEX_MAPPING_CONF_JSON);

                }
                LOG.info("Index properties: " + request);
                CreateIndexResponse create = this.client.admin().indices().create(request).actionGet();

                if (!create.isAcknowledged()) {
                    LOG.error("Index wasn't created");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LOG.info("Create index completed for: " + index + " at " + (System.currentTimeMillis() - start) + " ms");

        return null;
    }

    /**
     * Delete Index for given name.
     * 
     * @param index
     * @return object
     *         Aug 24, 2016
     */
    @Override
    public Object deleteIndex(String... index) {
        long start = System.currentTimeMillis();
        LOG.info("Delete index initialized for: " + Arrays.toString(index));

        try {
            DeleteIndexResponse delete = client.admin().indices().delete(new DeleteIndexRequest(index)).actionGet();
            if (!delete.isAcknowledged()) {
                LOG.error("Index wasn't deleted");
            }
            this.client.admin().indices().flush(new FlushRequest(index)).actionGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("Delete index completed for: " + Arrays.toString(index) + " at " + (System.currentTimeMillis() - start) + " ms");

        return null;
    }

    /**
     * Load list of documents into Index for given name for given type.
     * 
     * @param index
     * @param type
     * @param documents
     * @return
     *         Aug 24, 2016
     */
    @Override
    public Object insertDocuments(String index, String type, Collection<Map<String, Object>> documents) {
        Map<String, Object> results = new HashMap<>();

        if (documents.size() > 0) {
            long start = System.currentTimeMillis();
            LOG.info("Intialize documents creation for index: " + index);
            int count = 0;

            BulkRequestBuilder bulkRequest = this.client.prepareBulk();
            for (Map<String, Object> document : documents) {
                bulkRequest.add(this.client.prepareIndex(index, type).setSource(document));

                count++;
                if (count % 5000 == 0) {
                    LOG.info("Total: " + documents.size() + " Current: " + count);
                }
            }

            LOG.info("Bulk insertion started: " + new Date());
            BulkResponse bulkResponse = bulkRequest.get();
            if (bulkResponse.hasFailures()) {
                // process failures by iterating through each bulk response item
                LOG.info("FailureMessage: " + bulkResponse.buildFailureMessage());

            }
            LOG.info("Time taken: " + bulkResponse.getTookInMillis());
            LOG.info("Headers: " + bulkResponse.getHeaders());
            LOG.info("Failures: " + bulkResponse.hasFailures());

            LOG.info("Bulk insertion completed: " + new Date());
            LOG.info("Insertion compelted for: " + index + "\t Total docs: " + documents.size() + "\t at " + (System.currentTimeMillis() - start) + " ms");

            results.put("time_taken", bulkResponse.getTookInMillis());
            results.put("failures", bulkResponse.hasFailures());
            if (bulkResponse.hasFailures()) {
                results.put("message", bulkResponse.buildFailureMessage());
            }
        } else {
            results.put("message", ") doc found");
        }
        results.put("index", index);
        results.put("type", type);
        results.put("total_docs", documents.size());

        return results;
    }

    /**
     * Get list of color details for given color ids
     * 
     * @param ids
     * @return object
     *         Aug 24, 2016
     */
    @Override
    public Object getColors(List<Integer> ids) {
        LOG.info("getColorsDao  for ids: " + ids);
        long start = System.currentTimeMillis();

        SearchRequestBuilder builder = this.client.prepareSearch().addFields("pet_color_id", "public_name");
        builder.setQuery(QueryBuilders.termsQuery("pet_color_id", ids));
        SearchResponse response = builder.execute().actionGet();

        int totalHits = 0;
        Set<Map<String, Object>> results = new HashSet<>();
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            // fields list is given, returns only those. Otherwise return everything
            Map<String, SearchHitField> hitFields = hit.getFields();
            if (hitFields != null) {
                Map<String, Object> result = new HashMap<>();
                result.put("color", hitFields.get("pet_color_id").getValue());
                result.put("name", hitFields.get("public_name").getValue());
                results.add(result);
            }
        }

        LOG.info("Total hits: " + totalHits);
        long end = System.currentTimeMillis();
        LOG.info("getColorsDao  for ids: " + ids + ", Time taken: " + (end - start));

        return results;
    }

    /**
     * Get list of Families details for given family ids
     * 
     * @param ids
     * @return
     *         Aug 24, 2016
     */
    @Override
    public Object getFamilies(List<Integer> ids) {
        LOG.info("getColorsDao  for ids: " + ids);
        long start = System.currentTimeMillis();

        SearchRequestBuilder builder = this.client.prepareSearch().addFields("family_id", "family_name");
        builder.setQuery(QueryBuilders.termsQuery("family_id", ids));
        SearchResponse response = builder.execute().actionGet();

        int totalHits = 0;
        Set<Map<String, Object>> results = new HashSet<>();
        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            // fields list is given, returns only those. Otherwise return everything
            Map<String, SearchHitField> hitFields = hit.getFields();
            if (hitFields != null) {
                Map<String, Object> result = new HashMap<>();
                result.put("family", hitFields.get("family_id").getValue());
                result.put("name", hitFields.get("family_name").getValue());
                results.add(result);
            }
        }

        LOG.info("Total hits: " + totalHits);
        long end = System.currentTimeMillis();
        LOG.info("getColorsDao  for ids: " + ids + ", Time taken: " + (end - start));

        return results;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.adoptapet.utilities.dao.IPetUtilitiesDao#getClanDetails(java.util.List)
     *
     * Aug 25, 2016
     */
    @Override
    public Object getClanDetails(List<Integer> ids) {
        LOG.info("getColorsDao  for ids: " + ids);
        long start = System.currentTimeMillis();

        SearchRequestBuilder builder = this.client.prepareSearch().addFields("clan_id", "family_id", "pet_color_id");
        builder.setQuery(QueryBuilders.termsQuery("clan_id", ids));
        SearchResponse response = builder.execute().actionGet();

        int totalHits = 0;
        Map<String, Map<String, Set<String>>> results = new HashMap<>();

        SearchHits hits = response.getHits();
        for (SearchHit hit : hits.getHits()) {
            // fields list is given, returns only those. Otherwise return everything
            Map<String, SearchHitField> hitFields = hit.getFields();
            if (hitFields != null) {
                String key = null;
                String value = null;
                String clanId = hitFields.get("clan_id").getValue();
                if (hitFields.containsKey("family_id")) {
                    key = "families";
                    value = "family_id";
                } else if (hitFields.containsKey("pet_color_id")) {
                    key = "colors";
                    value = "pet_color_id";
                }

                if (StringUtils.isValidString(key) && StringUtils.isValidString(value)) {
                    Map<String, Set<String>> map = results.get(clanId);
                    if (map == null) {
                        map = new HashMap<>();
                    }
                    Set<String> values = map.get(key);
                    if (values == null) {
                        values = new HashSet<>();
                    }
                    values.add(value);
                    map.put(key, values);
                    results.put(clanId, map);
                }
            }
        }

        LOG.info("Total hits: " + totalHits);
        long end = System.currentTimeMillis();
        LOG.info("getColorsDao  for ids: " + ids + ", Time taken: " + (end - start));

        return results;
    }
}
