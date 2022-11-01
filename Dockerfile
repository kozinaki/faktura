FROM bellsoft/liberica-openjdk-debian:17

RUN apt update

RUN apt install -y nodejs npm

WORKDIR renderapi

COPY . ./

CMD ["run.sh"]

CMD ["./gradlew build"]

CMD ["./gradlew bootJar"]

ENTRYPOINT [ "java", "-jar", "build/libs/faktura-0.1.jar" ]
