#!/bin/bash
export PATH=$PATH:$PWD/bin
export HOME=$PWD
HELP="h for Help, d for description, l to list options, . <object> for action, cd <object> to go to object, cd .. to leave object, Ctrl+D to exit"
cd StartRoom
printf "\nWelcome to the Game!\n$HELP\n"
bash --rcfile <(echo "export PS1='\w>'; alias s=source; alias h='printf \"$HELP\n\"'; alias l='ls --color=auto'")
