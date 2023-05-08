FROM openjdk:17
EXPOSE 8000
ADD target/proxy-0.0.1-SNAPSHOT.jar .
CMD java -jar proxy-0.0.1-SNAPSHOT.jar --envname=prod