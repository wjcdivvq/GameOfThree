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

# example output
Example output when running the game with just one player:
```
Waiting for the game to start...
Started game with number '448600937'
I received your move '149533646'. I added '1', and answered with '49844549'.
I received your move '49844549'. I added '1', and answered with '16614850'.
I received your move '16614850'. I added '-1', and answered with '5538283'.
I received your move '5538283'. I added '-1', and answered with '1846094'.
I received your move '1846094'. I added '1', and answered with '615365'.
I received your move '615365'. I added '1', and answered with '205122'.
I received your move '205122'. I added '0', and answered with '68374'.
I received your move '68374'. I added '-1', and answered with '22791'.
I received your move '22791'. I added '0', and answered with '7597'.
I received your move '7597'. I added '-1', and answered with '2532'.
I received your move '2532'. I added '0', and answered with '844'.
I received your move '844'. I added '-1', and answered with '281'.
I received your move '281'. I added '1', and answered with '94'.
I received your move '94'. I added '-1', and answered with '31'.
I received your move '31'. I added '-1', and answered with '10'.
I received your move '10'. I added '-1', and answered with '3'.
I received your move '3'. I added '0', and answered with '1'.
I am application 'app1' and my player won.
```