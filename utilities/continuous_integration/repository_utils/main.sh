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
#                Action                     #
########################################################

main()
{
  case "$action" in
  1) sh clean.sh "$path"
    ;;
  2) sh backup.sh "$path"
    ;;
  *) echo "invalid option"
    ;;
esac
}

main $action $path
