#!/usr/bin/env bash

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

envfile=${env}.env

bold=$(tput bold)
normal=$(tput sgr0)
white="[97;1m"
endcolor="[0m"

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

command -v vault >& /dev/null || (
  echo "ERROR: You need to install the Vault CLI on your machine: https://www.vaultproject.io/downloads.html" && exit 1
) || exit 1
command -v jq >& /dev/null || (
  echo "ERROR: You need to install the jq CLI tool on your machine: https://stedolan.github.io/jq/" && exit 1
) || exit 1
command -v base64 >& /dev/null || (
  echo "ERROR: You need to install the base64 tool on your machine. (brew install base64 on macOS)" && exit 1
) || exit 1
command -v kubectl >& /dev/null || (
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
    if [[ $REPLY =~ ^[YyJj]$ ]]
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

function fetch_kubernetes_secrets {
    local type=$1
    local context=$2
    local namespace=$3
    local secret=$4
    local mode=$5
    local A=("$@")

    echo -n -e "\t- $type "

    local context_namespace_secrets_value=$(kubectl --context="$context" -n "$namespace" get secrets)

    if [[ "mode" == "strict" ]]; then
        local secret_name=$(echo "$context_namespace_secrets_value" | awk "/$secret/ {print \$1}")
    else
        local secret_name=$(echo "$context_namespace_secrets_value" | grep "$secret" | tail -1 | awk '{print $1}')
    fi

    if [[ $secret_name == *$'\n'* ]]; then
       echo
       echo "Fant følgende hemmeligheter som samsvarte med søkestrengen \"$secret\". Støtter kun en hemmelighet"
       echo $secret_name
       exit 1
    fi

    local secret_response=$(kubectl --context="$context" -n "$namespace" get secret "$secret_name" -o json)

    for name in "${A[@]:5}"
    do
        {
          echo -n "$name='"
          echo "$secret_response" | jq -j ".data[\"$name\"]" | base64 --decode |  tr -d '\n'
          echo "'"
        } >> ${envfile}
    done

    echo -e "${bold}${white}✔${endcolor}${normal}"
}


rm -f "${envfile}"
touch "${envfile}"

if command -v cygpath 2>&1 >/dev/null; then
  use_cygpath=true
else
  use_cygpath=false
fi

function absolute_path {
    local name=$1
    if $use_cygpath; then
      $(cygpath -a -w $name)
    else
      echo "$PWD/$name"
    fi
}

echo -e "${bold}Henter secrets fra Kubernetes${normal}"

fetch_kubernetes_secrets "AzureAD" "dev-fss" "pensjonsamhandling" "azure-pensjon-elsam-minibuss-$env" "strict" \
    "AZURE_APP_CLIENT_ID" \
    "AZURE_OPENID_CONFIG_ISSUER" \
    "AZURE_APP_CLIENT_SECRET" \
    "AZURE_OPENID_CONFIG_TOKEN_ENDPOINT" \
    "AZURE_APP_WELL_KNOWN_URL" \
    "AZURE_APP_TENANT_ID" \
    "AZURE_OPENID_CONFIG_JWKS_URI"

fetch_kubernetes_secrets "Unleash" "dev-fss" "pensjonsamhandling" "pensjon-elsam-minibuss-${env}-unleash-api-token" "strict" \
    "UNLEASH_SERVER_API_TOKEN" \
    "UNLEASH_SERVER_API_URL"

echo

echo "${bold}Henter secrets fra Vault${normal}"

echo -n -e "\t- Servicebruker "

echo "SERVICEUSER_USERNAME='$(vault kv get -field username -mount=serviceuser dev/srvpensjon)'" >> "${envfile}"
echo "SERVICEUSER_PASSWORD='$(vault kv get -field password -mount=serviceuser dev/srvpensjon)'" >> "${envfile}"

echo -e "${bold}${white}✔${endcolor}${normal}"

echo -n -e "\t- Truststore "

mkdir -p "secrets/$env/truststore"
vault kv get -field keystore -mount certificate dev/nav-truststore | base64 --decode > ".truststore.jts"
echo "NAV_TRUSTSTORE_PATH='$(absolute_path .truststore.jts)'" >> "${envfile}"
echo "NAV_TRUSTSTORE_PASSWORD='$(vault kv get -field keystorepassword -mount certificate dev/nav-truststore)'" >> "${envfile}"

echo "ENVIRONMENT_NAME='${env}'" >> "${envfile}"
echo "SPRING_PROFILES_ACTIVE='${env}'" >> "${envfile}"

echo -e "${bold}${white}✔${endcolor}${normal}"

echo

echo "Hentet hemmeligheter og oppdatert filen ${bold}$(realpath ${envfile})${normal}"
