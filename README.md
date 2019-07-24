Entando - Survey Plugin
=====================

## Scope
The scope of this service is to create Surveys, receive responses and be able to emit reports.

## Environment Configuration

You'll need these environment variables set in order to run the project. Some variables are **required** and *MUST* be set in order to run the server:

### Required Environment Variables:
>- `PORT`: TCP Port where the server will run. Default: `8081`
>- `KEYCLOAK_AUTH_URL`: The keycloak authentication URL. Default: `http://localhost:8081/auth`
>- `KEYCLOAK_REALM`: The keycloak realm. Default: `entando-development`
>- `KEYCLOAK_CLIENT_ID`: The keycloak resource/clientId. Default: `entando-survey`
>- `KEYCLOAK_CLIENT_SECRET`: The keycloak client secret.

### Optional Environment Variables:
>- `LOG_LEVEL`: Log level. Default: `INFO`

## Dependencies
Entando Survey uses Entando web-commons which uses Entando keycloak-connector.

In order to make it work on dev environment, you have to clone and install the dependencies or just add to your IntelliJ workspace.

* Web Commons: https://github.com/entando/web-commons
* Keycloak Connector: https://github.com/entando/keycloak-commons

### CLI:
```
$ git clone git@github.com:entando/web-commons.git
$ git clone git@github.com:entando/keycloak-commons.git

$ cd web-commons && mvn install -Dmaven.test.skip=true && cd ..
$ cd keycloak-commons && mvn install -Dmaven.test.skip=true && cd ..


## Startup
In Dev mode with h2 DB
    mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
