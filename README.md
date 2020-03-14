# cotacao

### Modules:
[agroal, cdi, hibernate-orm, jaeger, jdbc-postgresql, narayana-jta, resteasy, resteasy-jsonb, security, smallrye-opentracing, vertx, vertx-web]

## Setup

### Linux

1. Install docker-ce and docker-compose
2. Install Maven into /opt/java/maven
3. Install [GraalVM](https://www.graalvm.org/downloads/) into /opt/graalvm
4. Make sure your developer tools are up-to-date
	1. sudo apt install build-essential
	2. sudo apt install zlib1g-dev
5. Alter your .bashrc or .zshrc
	1. `export GRAALVM_HOME=/opt/graalvm`
    	2. `export JAVA_HOME=$GRAALVM_HOME`
    	3. `export PATH=$JAVA_HOME/bin:$PATH`

### OS X

1. Install Docker
2. Install Maven into /opt/java/maven
3. Install [GraalVM](https://www.graalvm.org/downloads/) into `/Library/Java/JavaVirtualMachines/graalvm`
4. Alter .bash_profile or .zshrc to include
    1. `export GRAALVM_HOME=/Library/Java/JavaVirutalMachines/graalvm`
    2. `export JAVA_HOME=$(/usr/libexec/java_home -v 1.8)` - This only works if you don't have java 1.8 installed.
        1. alternative: `export JAVA_HOME=$GRAALVM_HOME/Contents/Home`
    3. `export PATH=$JAVA_HOME/bin:$PATH`

### Windows 10 [LATEST]

1. Install Windows Subsystem for Linux
2. Install Maven
3. From WSL
    1. `sudo apt-get install zlib1g-dev`
    2. Install [GraalVM](https://www.graalvm.org/downloads/) into /opt/graalvm
    3. Update your .bashrc or .zshrc file  
        1. `export GRAALVM_HOME=/opt/graalvm`
        2. `export JAVA_HOME=$GRAALVM_HOME`
        3. `export PATH=$JAVA_HOME/bin:$PATH`
4. Install Docker

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