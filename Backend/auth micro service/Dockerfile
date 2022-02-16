#Base Image to use
FROM openjdk:11
#Exposing port 2000
EXPOSE 2000
#Add from a source to a destination
#In our case adding auth_mc jar from target folder to root folder of docker
ADD target/auth_mc.jar f.jar
#Running the jar
ENTRYPOINT ["java","-jar","f.jar"]