#!/bin/bash

DIR=$(dirname -- $0)

pushd $DIR/..
act -j build --container-architecture linux/arm64
popd
