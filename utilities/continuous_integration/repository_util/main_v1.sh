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

cd $envt
########################################################
#                Action                                #
########################################################

main()
{
  case "$action" in
  1) sh clean_v1.sh "$path"
    ;;
  2) sudo sh backup_v1.sh "$path"
    ;;
  3) sh clone_v1.sh "$path"
    ;;
  *) echo "invalid option"
    ;;
esac
}

main $action $path
