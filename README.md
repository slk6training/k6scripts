# k6 Performance Tests

## Introduction

This directory contains [k6](https://k6.io/) performance test scripts for Order Service.

## Prerequisites

1. Make sure you have [k6 installed](https://k6.io/docs/getting-started/installation) in your system.
2. Make sure configurations are correct in `config.js`.<br> 
Note: You can overwrite these default values by setting them as environment variables or passing them in CLI using `-e` directive.

## How to run

```

$ k6 run main.js
```

## Additional Info

* You can send your test results to an existing [Grafana + InfluxDB](https://k6.io/docs/results-visualization/influxdb-+-grafana) instance using following directive:<br>
`--out http://${INFLUX_DB_URL}:${PORT}/${DB_NAME}`<br>
Eg:<br>
```
$ k6 run --out influxdb=http://10.168.137.62:8086/k6db main.js
```
