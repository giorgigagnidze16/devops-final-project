#!/bin/bash
set -e

IMAGES="
devops-final-prj-backend:latest
devops-final-prj-frontend:latest
nginx:1.25-alpine
prom/prometheus:v2.44.0
grafana/grafana:9.5.2
denvazh/gatling:latest
"

for image in $IMAGES; do
  echo "Scanning $image ..."
  trivy image "$image"
done