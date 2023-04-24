#!/bin/bash
set -ex

#!/bin/bash

# Define the repository name and owner
repository_owner="example_owner"
repository_name="example_repo"

# Use the GitHub API to get the latest release version
latest_release=$(curl -s https://api.github.com/repos/${repository_owner}/${repository_name}/releases/latest | grep "tag_name" | cut -d '"' -f 4)

# Download the release zip file
wget https://github.com/${repository_owner}/${repository_name}/archive/${latest_release}.zip -O ${latest_release}.zip

echo "${password}" | sudo -S apt-get update
sudo apt-get install dirmngr --install-recommends
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys C5AD17C747E3415A3642D57D77C6C491D6AC1D69
echo "deb https://dl.k6.io/deb stable main" | sudo tee /etc/apt/sources.list.d/k6.list
sudo apt-get update
sudo apt-get install k6
