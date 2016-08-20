#!/bin/bash
CASSANDRA_HOME=/opt/cassandra
CASSANDRA_HOST="$(hostname --ip-address)"

if [ -z "$CASSANDRA_RPC_ADDRESS" ]; then
  CASSANDRA_RPC_ADDRESS="0.0.0.0"
fi

if [ -z "$CASSANDRA_LISTEN_ADDRESS" ]; then
  CASSANDRA_LISTEN_ADDRESS=$CASSANDRA_HOST  
fi

if [ -z "$CASSANDRA_BROADCAST_ADDRESS" ]; then
  CASSANDRA_BROADCAST_ADDRESS=$CASSANDRA_HOST  
fi

if [ -z "$CASSANDRA_BROADCAST_RPC_ADDRESS" ]; then
  CASSANDRA_BROADCAST_RPC_ADDRESS=$CASSANDRA_HOST  
fi

if [ -z "$CASSANDRA_SEEDS" ]; then
  CASSANDRA_SEEDS=$CASSANDRA_HOST
fi

sed -ri 's/^(# )?('"listen_address"':).*/\2 '"$CASSANDRA_LISTEN_ADDRESS"'/' "$CASSANDRA_HOME/conf/cassandra.yaml"
sed -ri 's/^(# )?('"rpc_address"':).*/\2 '"$CASSANDRA_RPC_ADDRESS"'/' "$CASSANDRA_HOME/conf/cassandra.yaml"
sed -ri 's/^(# )?('"broadcast_address"':).*/\2 '"$CASSANDRA_BROADCAST_ADDRESS"'/' "$CASSANDRA_HOME/conf/cassandra.yaml"
sed -ri 's/^(# )?('"broadcast_rpc_address"':).*/\2 '"$CASSANDRA_BROADCAST_RPC_ADDRESS"'/' "$CASSANDRA_HOME/conf/cassandra.yaml"
sed -ri 's/(- seeds:).*/\1 "'"$CASSANDRA_SEEDS"'"/' "$CASSANDRA_HOME/conf/cassandra.yaml"

exec "$@"
