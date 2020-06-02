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
Started game with number '840570719'
I received your move '840570719'. I added '1', and answered with '280190240'.
I received your move '280190240'. I added '1', and answered with '93396747'.
I received your move '93396747'. I added '0', and answered with '31132249'.
I received your move '31132249'. I added '-1', and answered with '10377416'.
I received your move '10377416'. I added '1', and answered with '3459139'.
I received your move '3459139'. I added '-1', and answered with '1153046'.
I received your move '1153046'. I added '1', and answered with '384349'.
I received your move '384349'. I added '-1', and answered with '128116'.
I received your move '128116'. I added '-1', and answered with '42705'.
I received your move '42705'. I added '0', and answered with '14235'.
I received your move '14235'. I added '0', and answered with '4745'.
I received your move '4745'. I added '1', and answered with '1582'.
I received your move '1582'. I added '-1', and answered with '527'.
I received your move '527'. I added '1', and answered with '176'.
I received your move '176'. I added '1', and answered with '59'.
I received your move '59'. I added '1', and answered with '20'.
I received your move '20'. I added '1', and answered with '7'.
I received your move '7'. I added '-1', and answered with '2'.
I received your move '2'. I added '1', and answered with '1'.
I am application 'app1' and my player won.
```