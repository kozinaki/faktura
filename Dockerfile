FROM bellsoft/liberica-openjdk-debian:17

RUN apt update

RUN apt install -y nodejs npm

WORKDIR renderapi

COPY . ./

RUN ./build_frontend.sh
RUN ./gradlew build
RUN ./gradlew bootJar

ENTRYPOINT [ "java", "-jar", "build/libs/faktura-0.1.jar" ]
