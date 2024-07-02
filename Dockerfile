FROM eclipse-temurin:17-jre

RUN apt-get update && apt-get install -y \
  curl \
  dumb-init \
  && rm -rf /var/lib/apt/lists/*

WORKDIR /app

RUN curl -L -O https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar

ENV LOGGING_CONFIG=classpath:logback-nais.xml
ENV MAIN_CLASS=no.nav.pensjon.elsam.minibuss.Application
ENV TZ="Europe/Oslo"

COPY java-opts.sh .

RUN chmod +x java-opts.sh

COPY target/pensjon-elsam-minibuss*jar app.jar

ENTRYPOINT ["/usr/bin/dumb-init", "--"]
CMD ["bash", "-c", "source ./java-opts.sh && exec java ${DEFAULT_JVM_OPTS} ${JAVA_OPTS} -jar app.jar ${RUNTIME_OPTS} $@"]
