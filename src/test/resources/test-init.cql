DROP KEYSPACE IF EXISTS testks;

CREATE KEYSPACE IF NOT EXISTS testks WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE testks.people(id text primary key, name text);

INSERT INTO testks.people (id, name) values ('XYZ123', 'John');
INSERT INTO testks.people (id, name) values ('ZYX567', 'Anna');
