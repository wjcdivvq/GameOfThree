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