FROM debian:11

RUN apt update

RUN apt install -y nodejs npm

WORKDIR renderapi

COPY . ./

CMD ["run.sh"]

ENTRYPOINT [ "./gradlew", "bootRun" ]
