#!/bin/bash
apt-get update
apt-get install -y docker.io docker-compose git
usermod -aG docker ${SSH_USER:-deployer}
