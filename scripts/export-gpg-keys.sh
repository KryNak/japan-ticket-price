#!/bin/bash

DIR=$(dirname -- $0)
PARENT_DIR=$DIR/..
KEY_FOLDER=$PARENT_DIR/keys

if [[ ! -d $KEY_FOLDER ]]; then
  mkdir $KEY_FOLDER
fi

pushd $KEY_FOLDER
gpg --armour --export-secret-keys krystian.nakielski200397@gmail.com > gpg-secret-key
popd
