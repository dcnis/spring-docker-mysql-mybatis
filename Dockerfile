FROM maven:3.8.3-jdk-11
ENV HOME=/home/usr/app
RUN mkdir -p $HOME
WORKDIR $HOME
ADD pom.xml $HOME
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
ADD . $HOME
RUN ["mvn", "package"]
EXPOSE 8080
CMD ["java", "-jar", "./target/mysql-spring-0.0.7-SNAPSHOT.jar"]
