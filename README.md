# Notification Service

The notification service provides the bounded context `Notification`. It is responsible for managing notifications.

## Documentation

Detailed information about the notification service can be found in the [documentation](https://misarch.github.io/docs/docs/dev-manuals/services/notification).


## Getting started

A development version of the notification service can be started using docker compose:

```bash
docker-compose -f docker-compose.dev.yml up --build
```
A GraphiQL interface is available at http://localhost:8080/graphiql to interact with the service.

> [!NOTE]
> Running the service locally through the IDE is neigher recommended nor supported.