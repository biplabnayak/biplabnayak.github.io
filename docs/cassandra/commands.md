## Start up
```jshelllanguage
cassandra.bat -f
```

## nodetool command
- to inspect the cassandra cluster

### Status
```jshelllanguage
E:\softwares\apache-cassandra-3.11.4\bin>nodetool status
Datacenter: datacenter1
========================
Status=Up/Down
|/ State=Normal/Leaving/Joining/Moving
--  Address    Load       Tokens       Owns (effective)  Host ID                               Rack
UN  127.0.0.1  205.59 KiB  256          100.0%            7e57b4b5-1648-451c-8c9a-62e30d27b5f2  rack1
```
