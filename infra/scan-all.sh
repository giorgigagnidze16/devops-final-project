#!/bin/bash
set -e

IMAGES="
devops-final-prj-backend:latest
devops-final-prj-frontend:latest
nginx:1.25-alpine
prom/prometheus:v2.44.0
prom/alertmanager:v0.26.0
grafana/grafana:9.5.2
denvazh/gatling:latest
docker.elastic.co/elasticsearch/elasticsearch:8.13.4
docker.elastic.co/logstash/logstash:8.13.4
docker.elastic.co/kibana/kibana:8.13.4
"

OUTPUT="trivy-report.txt"
# shellcheck disable=SC2188
> "$OUTPUT"

for image in $IMAGES; do
  echo "=======================================" >> "$OUTPUT"
  echo "Scanning $image ..." | tee -a "$OUTPUT"
  trivy image "$image" | tee -a "$OUTPUT"
done