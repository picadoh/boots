#!/usr/bin/env bash

cqlsh -h 127.0.0.1 -e "CREATE KEYSPACE IF NOT EXISTS localks WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};"
