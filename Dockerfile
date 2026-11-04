FROM europe-north1-docker.pkg.dev/cgr-nav/pull-through/nav.no/jre:openjdk-27

WORKDIR /app

ENV LOGGING_CONFIG=classpath:logback-nais.xml
ENV TZ="Europe/Oslo"

COPY --chown=1069:1069 target/pensjon-elsam-minibuss*jar app.jar

CMD ["-jar", "/app/app.jar"]
