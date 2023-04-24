#!/bin/bash
set -ex

#!/bin/bash

# Use the GitHub API to get the latest release version
latest_release=$(curl -s https://api.github.com/repos/grafana/k6/releases/latest | grep "v0.43.1" | cut -d '"' -f 4)

# Download the release zip file
wget https://github.com/grafana/k6/archive/k6-v0.43.1-macos-arm64.zip -O k6-v0.43.1-macos-arm64.zip

sudo -S apt-get update
sudo apt-get install dirmngr --install-recommends
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69
echo "deb https://dl.k6.io/deb stable main" | sudo tee /etc/apt/sources.list.d/k6.list
sudo apt-get update
sudo apt-get install k6
