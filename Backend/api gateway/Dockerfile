#image
FROM openjdk:11
#port to expose
EXPOSE 1000
#target - destination
ADD target/apigateway_mc.jar jarFile.jar
ENTRYPOINT ["java", "-jar", "jarFile.jar"]