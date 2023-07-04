# Commands
## Requires
```
Java 17.0.5
Docker version 20.10
```
## Build java
```
./gradlew clean
./gradlew build
```
## Local startup
```
./gradlew bootRun
```
## Release
```
cp build/libs/javadocker-0.0.1-SNAPSHOT.jar docker/javadocker.jar
```
## Docker
```
docker-compose build
docker-compose up -d
docker-compose stop
docker-compose down
```
### Useful
See active docker
```
docker ps
docker-compose ps
```
Enter in a docker container using bash shell
```
docker exec -it <container_sha> bash
```
See docker log (add -f for tail)
```
docker logs <container_sha>
docker-compose logs <compose_service_name>
```