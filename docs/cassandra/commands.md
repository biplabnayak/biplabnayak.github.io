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

### Ring Information
```jshelllanguage
E:\softwares\apache-cassandra-3.11.4\bin>nodetool ring

Datacenter: datacenter1
==========
Address    Rack        Status State   Load            Owns                Token
                                                                          9118163121001796480
127.0.0.1  rack1       Up     Normal  205.59 KiB      100.00%             -9166115910177580650
127.0.0.1  rack1       Up     Normal  205.59 KiB      100.00%             -9140318627424961575
.
.
```

### Describe Ring
```jshelllanguage
E:\softwares\apache-cassandra-3.11.4\bin>nodetool describering poc
Schema Version:18a67e4d-1df9-3464-a778-45be11401233
TokenRange:
        TokenRange(start_token:-1278383986553705672, end_token:-1275083923057266998, endpoints:[127.0.0.1], rpc_endpoints:[127.0.0.1], endpoint_details:[EndpointDetails(host:127.0.0.1, datacenter:datacenter1, rack:rack1)])
        TokenRange(start_token:-7743997565051657278, end_token:-7631291277685665516, endpoints:[127.0.0.1], rpc_endpoints:[127.0.0.1], endpoint_details:[EndpointDetails(host:127.0.0.1, datacenter:datacenter1, rack:rack1)])
        TokenRange(start_token:1175253586319593731, end_token:1236937570325296013, endpoints:[127.0.0.1], rpc_endpoints:[127.0.0.1], endpoint_details:[EndpointDetails(host:127.0.0.1, datacenter:datacenter1, rack:rack1)])
        TokenRange(start_token:7208549595876488211, end_token:7226685756842307871, endpoints:[127.0.0.1], rpc_endpoints:[127.0.0.1], endpoint_details:[EndpointDetails(host:127.0.0.1, datacenter:datacenter1, rack:rack1)])
.
.
```

### Other useful commands
* `tracing on;` - for turning on / off tracing in CQL console.
* `expand on` - show the expanded view of CQL query results
