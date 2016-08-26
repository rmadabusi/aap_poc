FROM java:8
###### KIBANA SETUP ######
#For constant name of Kibana
ENV KIBANA_PKG_NAME kibana-4.5.4-linux-x64

# Run command to download, extract and move specified directory setup
RUN \
    cd / && \
    wget https://download.elastic.co/kibana/kibana/$KIBANA_PKG_NAME.tar.gz && \
    tar xvzf $KIBANA_PKG_NAME.tar.gz && \
    rm -f $KIBANA_PKG_NAME.tar.gz && \
    mv /$KIBANA_PKG_NAME /kibana

# Copy elasticsearch.yml config
COPY kibana.yml /kibana/config/kibana.yml

# Expose ports. - 5601: HTTP
EXPOSE 5601

# ENRETPOTINT TO start execution, when do run command
ENTRYPOINT ["sh","/kibana/bin/kibana"]
###### KIBANA SETUP ######