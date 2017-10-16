FROM java:8
MAINTAINER Hugo Picado (hugo.pcd@gmail.com)

# groups
RUN groupadd -r cassandra --gid=999 && useradd -r -g cassandra --uid=999 cassandra

# install
ENV MIRROR http://apache.mirrors.pair.com/
ENV VERSION 3.11.1

RUN curl $MIRROR/cassandra/$VERSION/apache-cassandra-$VERSION-bin.tar.gz | tar -xzf - -C /opt \
    && mv /opt/apache-cassandra-$VERSION /opt/cassandra \
    && mkdir -p /tmp/cassandra

# post installation config
ADD config_cassandra.sh /opt/cassandra
RUN chmod +x /opt/cassandra/config_cassandra.sh
ENTRYPOINT ["/opt/cassandra/config_cassandra.sh"]

# owners
RUN chown -R cassandra:cassandra /opt/cassandra
USER cassandra

# start
WORKDIR /opt/cassandra

# 7000: intra-node communication
# 7001: TLS intra-node communication
# 7199: JMX
# 9042: CQL
# 9160: thrift service
EXPOSE 7000 7001 7199 9042 9160
CMD ["/opt/cassandra/bin/cassandra", "-f"]

