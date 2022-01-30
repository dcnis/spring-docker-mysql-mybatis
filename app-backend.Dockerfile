FROM maven:3.8.3-jdk-11
ENV HOME=/home/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
COPY pom.xml $HOME
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
COPY ./src $HOME/src
COPY ./logback-spring.xml $HOME
COPY ./mybatis-config.xml $HOME
RUN ["mvn", "package"]
EXPOSE 3000 3307
CMD ["java", "-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "./target/mysql-spring-0.0.7-SNAPSHOT.jar"]
