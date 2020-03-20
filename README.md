# cotacao

### Modules:
[agroal, cdi, hibernate-orm, jaeger, jdbc-postgresql, narayana-jta, resteasy, resteasy-jsonb, security, smallrye-opentracing, vertx, vertx-web]

## Build

1. Start the docker database `docker-compose up --no-start db & docker-compose start db` from the project root.
2. Start the docker open trace instance: `docker run -e COLLECTOR_ZI:PKIN_HTTP_PORT=9411 -p 5775:5775/udp -p 6831:6831/udp -p 6832:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 -p 9411:9411 jaegertracing/all-in-one:latest`
3. Start the server: `./mvnw quarkus:dev`
4. Test: `curl http://localhost:8080/api/cotacao/{data}`

See (Documentation):

`http://localhost:8080/`

Output (Similar):
```json
[{"dataAtualizacao":"2020-03-14T03:34:40.212Z","dataRegistro":"2020-03-14T03:34:40.212Z","version":0,"dataCotacao":"2020-03-03T13:06:38.000Z","id":103,"valorCompra":4.48770,"valorVenda":4.48830}]
```
