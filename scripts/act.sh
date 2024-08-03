#!/bin/bash

DIR=$(dirname -- $0)
PARENT=$DIR/..

pushd $PARENT
act -j build --secret-file $PARENT/.secrets
popd
