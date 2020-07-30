#!/bin/bash
export PATH=$PATH:$PWD/bin
export HOME=$PWD
G="\033[0;32m"
X="\033[0m"
HELP="$G h$X for Help, $G d$X for description, $G l$X to list options, $G .$X <object> for action, $G cd$X <object> to go to object, $G cd ..$X to leave object, $G Ctrl+D$X to exit"
cd StartRoom
printf "\nWelcome to the Game!\n$HELP\n"
bash --rcfile <(echo "export PS1='\w>'; alias s=source; alias h='printf \"$HELP\n\"'; alias l='ls --color=auto'; alias ls='ls --color=auto'")
