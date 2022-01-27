# Spring Docker MySQL MyBatis
An simple Java application showing the usage of Spring Boot, Docker, MySQL and MyBatis.

## Description

This project will spin up two docker containers. One for the Spring Boot application and another one for the MySQL Database.
MyBatis is a persistence framework with suppport for custom SQL, stored procedures and advanced mappings.

## Requirements

The requirements for this project are the followings:

- Java 11
- Docker
- Maven

## Usage

After downloading the source code you can run the application with a single command.
```bash
docker compose up --build
```

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

## License
[MIT](https://choosealicense.com/licenses/mit/)
