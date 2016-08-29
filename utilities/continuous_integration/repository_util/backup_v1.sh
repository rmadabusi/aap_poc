#!/bin/sh
#!/bin/bash

DATE=`date +%Y-%-m-%-e`

path=$1

########################################################
#                    Backup                            #
########################################################
backup()
{
  echo "Backup current code repo to backup folder."
  if [ -d "$path" ]; then
    cd $path
    tar -czf codebase.$DATE.tar.gz codebase
    echo "Compressed"
    mv codebase.$DATE.tar.gz $path/archives/
    echo "Backup Done."
  fi
}

backup $path
