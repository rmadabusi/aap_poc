#!/bin/sh
#!/bin/bash

########################################################
#                Step up variables                     #
########################################################
envt=$1
path=$2
action=$3

echo "Environments : " $envt
echo "Project path : " $path
echo "Action       : " $action

########################################################
#                     Clean                            #
########################################################
clean()
{
  echo "Checking for repositry exist."
  if [ -d "$path" ]; then
    echo "Folder Exist."
  fi
}

########################################################
#                     Backup                           #
########################################################
backup()
{
  echo "Backup the existing source."
  if [ -d "$path" ]; then
    echo "Folder Exist."
  fi
}


########################################################
#                     Clone                            #
########################################################
clonerepo()
{
  echo "Fetching the resource."
  if [ -d "$path" ]; then
    echo "Folder Exist."
  fi
}

########################################################
#                     Build                            #
########################################################
build()
{
  echo "Building the resource."
  if [ -d "$path" ]; then
    echo "Folder Exist."
  fi
}

echo "Creating repositry."
#$action "$path"
echo $action "$path"

case "$action" in
1) clean "$path"
   ;;
2) backup "$path"
   ;;
3) clonerepo "$path"
   ;;
4) build "$path"
   ;;
*) echo "invalid option"
   ;;
esac
