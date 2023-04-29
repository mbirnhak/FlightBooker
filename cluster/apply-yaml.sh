pushd ../cluster
export APP_USER="${1:-bsimoes2001}"
for f in *.yaml; do envsubst < $f | kubectl apply -f -; done