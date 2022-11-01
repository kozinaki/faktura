FROM bellsoft/liberica-openjdk-debian:17

RUN apt update

RUN apt install -y nodejs npm

WORKDIR renderapi

COPY . ./

CMD ["run.sh"]

ENTRYPOINT [ "./gradlew", "bootRun" ]
