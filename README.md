Entando - User Management
=====================

[![Build Status](https://jenkins.entandocloud.com/buildStatus/icon?job=de-entando-user-mgmt-master)](https://jenkins.entandocloud.com/view/Digital%20Exchange/job/de-entando-user-mgmt-master/2/)
[![Coverage Status](https://coveralls.io/repos/github/entando/entando-user-mgmt/badge.svg?branch=master)](https://coveralls.io/github/entando/entando-user-mgmt?branch=master)

## Scope
The scope of this service is to provide Client (App) management and also User Management REST API with permissioning to each client. Both backed by Keycloak.

## Environment Configuration

You'll need these environment variables set in order to run the project. Some variables are **required** and *MUST* be set in order to run the server:

### Required Environment Variables:
>- `PORT`: TCP Port where the server will run. Default: `8082`
>- `KEYCLOAK_AUTH_URL`: The keycloak authentication URL. Default: `http://localhost:8081/auth`
>- `KEYCLOAK_REALM`: The keycloak realm. Default: `entando`
>- `KEYCLOAK_CLIENT_ID`: The keycloak resource/clientId. Default: `entando-user-mgmt`
>- `KEYCLOAK_CLIENT_SECRET`: The keycloak client secret.

### Optional Environment Variables:
>- `LOG_LEVEL`: Log level. Default: `INFO`

## Dependencies
Entando Auth uses Entando web-commons which uses Entando keycloak-connector.

In order to make it work on dev environment, you have to clone and install the dependencies or just add to your IntelliJ workspace.

* Web Commons: https://github.com/entando/web-commons
* Keycloak Connector: https://github.com/entando/keycloak-commons

### CLI:
```
$ git clone git@github.com:entando/web-commons.git
$ git clone git@github.com:entando/keycloak-commons.git

$ cd web-commons && mvn install -Dmaven.test.skip=true && cd ..
$ cd keycloak-commons && mvn install -Dmaven.test.skip=true && cd ..
```

## Setup
In order to setup the service, please follow the steps written in the Wiki 

https://github.com/entando/entando-user-mgmt/wiki/Setup
