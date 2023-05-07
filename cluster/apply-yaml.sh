pushd ../cluster
export APP_USER="${1:-bsimoes2001}"
sed "s/APP_USER/$1/g" mongo-deployment.yaml | kubectl apply -f -
for f in *.yaml; do envsubst < $f | kubectl apply -f -; done