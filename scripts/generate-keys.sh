#!/bin/bash

DIR=$(dirname -- $0)
PARENT_DIR=$DIR/..
KEY_FOLDER=$PARENT_DIR/keys

if [[ -d $KEY_FOLDER ]]; then
  find $KEY_FOLDER -mindepth 1 -delete
fi

pushd $KEY_FOLDER
if [[ $? -eq 1 ]]; then
  mkdir $KEY_FOLDER && pushd $KEY_FOLDER || exit 1
fi

ssh-keygen -t ed25519 -f deploy_key -N "" -C "bot.github@gmail.com"
popd
