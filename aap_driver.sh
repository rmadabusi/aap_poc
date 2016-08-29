#!/bin/sh
#!/bin/bash

HOME=${pwd}
cd $HOME

echo "Driver scripts execution started... "

echo "Pre-requistics installation started... "
echo "**********************************************************************************************"
echo "Installing Docker...."
####### Docker Installation steps #######
#Update package information
sudo apt-get update
sudo apt-get -y install apt-transport-https ca-certificates
sudo apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
#Add an entry for Ubuntu operating system
sudo /bin/sh -c 'echo "deb https://apt.dockerproject.org/repo ubuntu-trusty main" >> /etc/apt/sources.list.d/docker.list'
sudo apt-get update
#Remove if docker already installed
sudo apt-get purge lxc-docker
#Verify that APT is pulling from the right repository.
apt-cache policy docker-engine

#kernal packages
sudo apt-get update
sudo apt-get -y install linux-image-extra-$(uname -r) linux-image-extra-virtual

#Install dockers
sudo apt-get update
sudo apt-get -y install docker-engine
sudo service docker start

#check hello-word program to test Docker is running or not
sudo docker run hello-world

echo "Docker installation completed"
echo "**********************************************************************************************"
####### Docker Installation steps #######

####### Java Installation steps #######
echo "**********************************************************************************************"
echo "Installing Java8...."
sudo apt-get update
sudo /bin/sh -c 'echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee /etc/apt/sources.list.d/webupd8team-java.list'
sudo /bin/sh -c 'echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list'
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
sudo apt-get update
sudo apt-get -y install oracle-java8-installer
echo "Java8 installation completed"
echo "**********************************************************************************************"
####### Java Installation steps #######


####### Ant Installation steps #######
echo "**********************************************************************************************"
echo "Installing Ant...."
sudo apt-get -y install ant
echo "Ant installation completed"
echo "**********************************************************************************************"
####### Ant Installation steps #######
echo "Pre-requistics installation completed"

echo "Start executing Continuous Integration...."
cd utilities/continuous_integration/
ant run -Dhome=$HOME
echo "Everything installed. Now containers are running, check below for running containers"
sudo docker ps

echo "Driver scripts execution completed"

exit 
