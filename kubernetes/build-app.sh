#!/bin/bash

if [[ "$*" == 'clean' ]]
then
    kubectl delete -f .
fi

if [[ "$1" == 'build' ]]
then
    
    docker context create buildx-build
    docker buildx create --use buildx-build
    # exit when any command fails
    set -e

    pushd ../aggregator
    chmod +x aggregator-build-push.sh
    ./aggregator-build-push.sh $2
    popd
    pushd ../Flights-Kiwi
    chmod +x build-push.sh
    ./build-push.sh $2
    popd
    pushd ../notifications
    chmod +x notifications-build-push.sh
    ./notifications-build-push.sh $2
    popd
    pushd ../payment
    chmod +x payment-build-push.sh
    ./payment-build-push.sh $2
    popd
    pushd ../seating
    chmod +x seating-build-push.sh
    ./seating-build-push.sh $2
    popd
    pushd ../transaction-history
    chmod +x transaction-history-build-push.sh
    ./transaction-history-build-push.sh $2
    popd
    pushd ../userservice
    chmod +x userservice-build-push.sh
    ./userservice-build-push.sh $2
    popd
    pushd ../front-end
    chmod +x ui-build-push.sh
    ./ui-build-push.sh $2
    popd

    set +e
    
fi