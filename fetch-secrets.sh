#!/usr/bin/env bash

OPTSTRING=":v"

verbose=false

while getopts ${OPTSTRING} opt; do
  case ${opt} in
    v)
      verbose=true
      ;;
    ?)
      echo "Ukjent valg: -${OPTARG}"
      exit 1
      ;;
  esac
done

shift $((OPTIND-1))


if [[ $# -eq 0 ]]; then
    env="q2"

elif [[ $# -eq 1 ]]; then
    env=$1

else
    echo "Usage: "
    echo "    No input: fetch secrets for q2"
    echo "    <env>"

    exit 1
fi


bold=$(tput bold)
normal=$(tput sgr0)
white="[97;1m"
yellow="[33;1m"
endcolor="[0m"


if [ "${BASH_VERSINFO:-0}" -lt 4 ]; then
    echo "Du har for gammel versjon av bash. Vennligst installer versjon 4 eller høyere"

    if [[ $OSTYPE == 'darwin'* ]]; then
        echo
        echo "På Mac kan du kjøre: ${white}brew install bash${endcolor}"
    fi

    exit 1
fi

if command -v nais >& /dev/null; then
  DISCONNECT_STATUS=$(nais device status | grep -c Disconnected)

  if [ $DISCONNECT_STATUS -eq 1 ]; then
    read -p "Du er ikke koblet til med naisdevice. Vil du koble til? (j/n) " -n 1 -r
    echo
    if [[ $REPLY =~ ^[YyjJ]$ ]]; then
      nais device connect
    else
      echo -e "${red}Du må være koblet til med naisdevice, avslutter${endcolor}"
      exit 1
    fi
  fi
fi

vault --version >& /dev/null || (
  echo "ERROR: You need to install the Vault CLI on your machine: https://www.vaultproject.io/downloads.html" && exit 1
) || exit 1
jq --version >& /dev/null || (
  echo "ERROR: You need to install the jq CLI tool on your machine: https://stedolan.github.io/jq/" && exit 1
) || exit 1
base64 --help >& /dev/null || (
  echo "ERROR: You need to install the base64 tool on your machine. (brew install base64 on macOS)" && exit 1
) || exit 1
which kubectl >& /dev/null || (
  echo "ERROR: You need to install and configure kubectl (see: https://confluence.adeo.no/x/UzjYF)" && exit 1
) || exit 1

export VAULT_ADDR=https://vault.adeo.no

while true; do
	NAME="$(vault token lookup -format=json | jq '.data.display_name' -r; exit ${PIPESTATUS[0]})"
  ret=${PIPESTATUS[0]}
  if [ "$ret" -ne 0 ]; then
    echo "Looks like you are not logged in to Vault."

    read -p "Do you want to log in? (y/n) " -n 1 -r
    echo    # (optional) move to a new line
    if [[ $REPLY =~ ^[Yy]$ ]]
    then
      vault login -method=oidc -no-print
    else
      echo "Could not log in to Vault. Aborting."
      exit 1
    fi
  else
    break;
  fi
done

set -e
set -o pipefail

spin[0]="⠻"
spin[1]="⠽"
spin[2]="⠾"
spin[3]="⠷"
spin[4]="⠯"
spin[5]="⠟"

spinIndex=0
spinStarted=false
spinElements=6

declare -A kubernetes_context_namespace_secrets
declare -A kubernetes_secret_array

function doSpin {
    if "$spinStarted"; then
        echo -ne "\b${spin[spinIndex]}"
    else
        echo -ne "${spin[spinIndex]}"
    fi
    spinStarted=true
    spinIndex=$(((spinIndex+1) % $spinElements))
}

function fetch_kubernetes_secret {
    local context=$1
    local namespace=$2
    local secret=$3
    local name=$4
    local context_namespace_secrets_key
    local context_namespace_secrets_value
    local secret_name
    local secret_response

    context_namespace_secrets_key="$context:$namespace"

    if [ -v kubernetes_context_namespace_secrets["$context_namespace_secrets_key"] ]; then
        context_namespace_secrets_value=${kubernetes_context_namespace_secrets["$context_namespace_secrets_key"]}
    else
        context_namespace_secrets_value=$(kubectl --context="$context" -n "$namespace" get secrets)
        kubernetes_context_namespace_secrets["$context_namespace_secrets_key"]=$context_namespace_secrets_value
    fi

    secret_name=$(echo "$context_namespace_secrets_value" | grep "$secret" | tail -1 | awk '{print $1}')

    if [ -v kubernetes_secret_array["$secret_name"] ]; then
        secret_response=${kubernetes_secret_array["$secret_name"]}
    else
        secret_response=$(kubectl --context="$context" -n "$namespace" get secret "$secret_name" -o json)
        kubernetes_secret_array["$secret_name"]=$secret_response
    fi

    {
      echo -n "$name="
      echo "$secret_response" | jq -j ".data[\"$name\"]" | base64 --decode
      echo
    } >> ${env}.env
}

function fetch_kubernetes_secret_array {
    local type=$1
    local context=$2
    local namespace=$3
    local secret=$4
    local A=("$@")

    echo -n -e "\t- $type "

    mkdir -p "secrets/$env/$path"

    for i in "${A[@]:4}"
    do
        doSpin
        fetch_kubernetes_secret "$context" "$namespace" "$secret" "$i"
    done

    spinIndex=0
    spinStarted=false
    echo -e "\b${bold}${white}✔${endcolor}${normal}"
}


rm -f ${env}.env
touch ${env}.env


echo -e "${bold}Henter secrets fra Kubernetes${normal}"

fetch_kubernetes_secret_array "AzureAD" "dev-fss" "pensjonsamhandling" "azure-pensjon-elsam-minibuss-$env" \
    "AZURE_APP_CLIENT_ID" \
    "AZURE_OPENID_CONFIG_ISSUER" \
    "AZURE_APP_CLIENT_SECRET" \
    "AZURE_OPENID_CONFIG_TOKEN_ENDPOINT" \
    "AZURE_APP_WELL_KNOWN_URL" \
    "AZURE_APP_TENANT_ID" \
    "AZURE_OPENID_CONFIG_JWKS_URI"

fetch_kubernetes_secret_array "Unleash" "dev-fss" "pensjonsamhandling" "pensjon-elsam-minibuss-${env}-unleash-api-token" \
    "UNLEASH_SERVER_API_TOKEN" \
    "UNLEASH_SERVER_API_URL"

echo

echo "${bold}Henter secrets fra Vault${normal}"

echo -n -e "\t- Servicebruker "
echo "SERVICEUSER_USERNAME=$(vault kv get -field 'username' 'serviceuser/dev/srvpensjon')" >> ${env}.env
echo "SERVICEUSER_PASSWORD=$(vault kv get -field 'password' 'serviceuser/dev/srvpensjon')" >> ${env}.env

echo -e "${bold}${white}✔${endcolor}${normal}"

echo -n -e "\t- Truststore "

mkdir -p "secrets/$env/truststore"
vault kv get -field keystore certificate/dev/nav-truststore | base64 --decode > ".truststore.jts"
echo "NAV_TRUSTSTORE_PATH=$(pwd)/.truststore.jts" >> ${env}.env
echo "NAV_TRUSTSTORE_PASSWORD=$(vault kv get -field keystorepassword certificate/dev/nav-truststore)" >> ${env}.env

echo "ENVIRONMENT_NAME=${env}" >> ${env}.env

echo -e "${bold}${white}✔${endcolor}${normal}"

echo

echo "${bold}Hentet hemmeligheter og oppdatert ${env}.env fil ${normal}"
