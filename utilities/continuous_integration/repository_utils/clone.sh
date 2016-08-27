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

copy()
{
  echo "Cloning for code repository exist."
  if [ -d "$path" ]; then
    cd $path
    if [ -d "$path/aap_poc" ]; then
      cd $path/aap_poc
      git checkout developing
      echo "cloned successfully"
      cp -rf $path/aap_poc/* /mnt/codebase_bk/.
    fi
  fi
}

clone $path
copy $path
