# Game of Three

# build

You need java 14 and optionally docker installed.
```
./mvnw clean package && docker build -t gameofthree . && docker-compose up -d
```

# play
Start the game
```
curl -X POST http://localhost:8080/startGame
```
then check what each player is doing with:

```
docker-compose logs app1
```
or

```
docker-compose logs app2
```

You can watch what happens if only one player is online if you stop one docker container.
```
docker-compose stop app2
``` 

If you want to watch the game playing at a non-instant speed,
you can add an artificial delay to the http calls.
In this specific example the application plays against itself instead of against another application.
```
docker run --rm -p 8081:8081 --env SPRING_APPLICATION_JSON='{"http.artificialDelayInMs":500,"server.port":8081}' gameofthree
```