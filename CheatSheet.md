## Cheat Sheet
A simple cheat sheet to remember some commands.

### Acess MySQL-DB in docker container

```bash
docker exec -it mysql mysql -p
secret
```

### Access Redis in docker container

```bash
docker exec -it redis redis-cli
```

### Run all docker containers except app-frontend
```bash
docker compose up --scale app-frontend=0
```

### Explore specific docker container's filesystem
```bash
docker exec -it {containername} /bin/bash
```

### Bulk delete of docker containers by name
```bash
docker rmi $(docker images | grep '<none>')
```

### Get Bearer Token from keycloak via API
```bash
docker exec -it keycloak curl -X POST 'http://keycloak:8080/auth/realms/popupchinese/protocol/openid-connect/token' \
 --header 'Content-Type: application/x-www-form-urlencoded' \
 --data-urlencode 'grant_type=password' \
 --data-urlencode 'client_id=popupchineseapp' \
 --data-urlencode 'username=user1' \
 --data-urlencode 'password=user1'
```