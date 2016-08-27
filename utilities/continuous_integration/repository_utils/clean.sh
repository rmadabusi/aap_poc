#!/bin/sh
#!/bin/bash

path=$1

########################################################
#                     Clean                            #
########################################################
clean()
{
  echo "Clean for code repo exist."
  if [ -d "$path" ]; then
    cd $path
    rm -rf *
  fi
}

clean $path
