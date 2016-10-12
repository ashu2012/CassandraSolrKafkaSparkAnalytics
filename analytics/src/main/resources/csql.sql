CREATE testKeySpace IF NOT EXISTS testKeySpace  WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1} ;


Its as simple as these 2 steps

1. use <keyspace_name>;

2. describe tables;