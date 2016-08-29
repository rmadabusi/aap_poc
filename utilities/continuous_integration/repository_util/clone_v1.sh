#!/bin/sh
#!/bin/bash

path=$1

########################################################
#                     Clone                            #
########################################################
clone()
{
  echo "Cloning for code repository exist."
  if [ -d "$path" ]; then
    cd $path
    git clone https://github.com/rmadabusi/aap_poc
    #echo "Folder Exist."
    if [ -d "$path/aap_poc" ]; then
      echo "cloned successfully"
    fi
  fi
}

switchdir()
{
  echo "Cloning for code repository exist."
  if [ -d "$path" ]; then
    cd $path
    if [ -d "$path/aap_poc" ]; then
      cd $path/aap_poc
      git checkout developing
      #echo "sudo cp -rf $path/aap_poc/* $2/."
      #sudo cp -rf $path/aap_poc/* $2/.
      #echo "code is ready to build"
    fi
  fi
}

clone $path
switchdir $path
