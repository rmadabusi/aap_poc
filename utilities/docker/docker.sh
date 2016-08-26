#!/bin/bash
#!/bin/sh
#############################################################################################################################################
##### Script Name: Docker Build and Run Script 																					       ######
##### Purpose: The purpose of the script to build, start, stop build, start, stop images for one or many modules into Docker container. ######
#############################################################################################################################################

#set -x
HOME_DIR=$(pwd)
TASK=$1
MODULES=$2


#Build docker image for given a module
function build() {
	echo "Function 'build': Running $MODULE ..."
    MODULE=$1
	DIR=$HOME_DIR/$MODULE
	
	if [ "$MODULE" = "pet_clan_service" ] || [ "$MODULE" = "pet_family_service" ] || [ "$MODULE" = "pet_color_service" ] || [ "$MODULE" = "pet_usagemetric_service" ] ; then
		cd $HOME_DIR/micro_services/$MODULE
	fi
	
	# BUILD COMMAND
	sudo docker build -t $MODULE .
	echo "Function 'build': Running $MODULE completed"
}

#Start docker image name
function start() {
	MODULE=$1
	echo "Function 'run': Running $MODULE ..."
	
	## Configure all required ports, directory location, additional params to run the module ##
	if [ "$MODULE" = "elasticsearch" ]; then
		PORTS='-p 9200:9200 -p 9300:9300'
	elif [ "$MODULE" = "kibana" ]; then
		PORTS='-p 5601:5601'
	elif [ "$MODULE" = "pet_clan_service" ]; then
		PORTS='-p 2003:2003'
	elif [ "$MODULE" = "pet_family_service" ]; then
		PORTS='-p 2001:2001'
	elif [ "$MODULE" = "pet_color_service" ]; then
		PORTS='-p 2002:2002'
	elif [ "$MODULE" = "pet_usagemetric_service" ]; then
		PORTS='-p 2000:2000'
	elif [ "$MODULE" = "api_gateway" ]; then
		PORTS='-p 3000:3000'
	elif [ '$MODULE' = 'app' ]; then 
		PORTS='-p 80:80'
		EXTRA_ARGS='/usr/sbin/apache2ctl -D FOREGROUND'
	else
		echo "WRONG module $MODULE"
		exit
	fi
	## Configure all required ports, directory location, additional params to run the module ##
	
	
	##RUN COMMAND
	echo "Command: sudo docker run -d $PORTS $MODULE ."
	sudo docker run -d $PORTS app $EXTRA_ARGS
	
	echo "Function 'run': Running $MODULE completed"
}

#Stop docker image name
function stop() {
	echo "Function 'stop': Running '$MODULE' ..."
    MODULE=$1
	#sudo docker stop $MODULE
	CONTAINER_ID=$(sudo docker ps -q --filter ancestor=$MODULE)
	if [ -z "$CONTAINER_ID" ]; then 
		echo "$MODULE is not running"
	else 
		sudo docker kill $CONTAINER_ID
	fi
	echo "Function 'stop': Running '$MODULE' completed"
}


#Hepler to reverse array
function reverseArr() {
	arr=$1
	var=0
	declare -a reversal 
	indices=( ${!arr[@]} )
	for ((i=${#indices[@]} - 1; i >= 0; i--)) ; do
		reversal[$var]="${arr[indices[i]]}"
		var=$((var+1))
	done
	echo "$reversal"
}

###############################
###### SCRIPT STARTS HERE #####
###############################


cd $HOME_DIR

#Validate or intialize modules
if [ "$MODULES" = "all" ]; then
	echo "Adding all services into array to perform task"
	#NOTE: Order of given value is important to start services from BOTTOM-TOP & TOP-BOTTOM approach
	declare -a arr=( "elasticsearch" "kibana" "pet_clan_service" "pet_family_service" "pet_color_service" "pet_usagemetric_service" "api_gateway" "app" )
	if [ "$TASK" = "stop" ]; then
		echo "Reverse services to kill task in top-bottom order"
#		arr1=$(reverseArr $arr)
		declare -a arr=( "app" "api_gateway" "pet_usagemetric_service" "pet_color_service" "pet_family_service" "pet_clan_service" "kibana" "elasticsearch" )
	
	done
	
	fi
else 
	declare -a arr=( "$MODULES" )
fi 


## MAIN LOOP TO EXECUTE COMMANDS ##
#Iterate each module and do execution
for i in "${arr[@]}"
do
	MODULE="$i"
	echo "Module: $MODULE running..."
	if [ -z "$MODULE" ]; then 
		echo "**************************** Module: $MODULE running wrong ****************************"
	else
		echo " in -z"
		if [ "$TASK" = "build" ]; then
			build $MODULE
		elif [ "$TASK" = "start" ]; then
			start $MODULE
		elif [ "$TASK" = "stop" ]; then
			stop $MODULE
		else
			echo "WRONG TASK $TASK"
		fi
	fi
	echo "Module: $MODULE running completed"
	
	sleep 2s
done
echo "The Module: $MODULES are successfully $TASK"
## MAIN LOOP TO EXECUTE COMMANDS ##

exit 
###############################
###### SCRIPT ENDS HERE #####
###############################

set +x
