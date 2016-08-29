FROM java:8
###### ElasticSearch SETUP ######
ENV ES_PKG_NAME elasticsearch-2.3.5

# Run command to download, extract and move specified directory setup
RUN \
    cd / && \
    wget https://download.elasticsearch.org/elasticsearch/elasticsearch/$ES_PKG_NAME.tar.gz && \
    tar xvzf $ES_PKG_NAME.tar.gz && \
    rm -f $ES_PKG_NAME.tar.gz && \
    mv /$ES_PKG_NAME /elasticsearch

# Copy elasticsearch.yml config
COPY elasticsearch.yml /elasticsearch/config/elasticsearch.yml
COPY data/ /data

# Expose ports. - 9200: HTTP - 9300: transport
EXPOSE 9200 9300

# ENRETPOTINT TO start execution, when do run command
ENTRYPOINT ["sh", "/elasticsearch/bin/elasticsearch", "-Des.insecure.allow.root=true"]
###### ElasticSearch SETUP ######