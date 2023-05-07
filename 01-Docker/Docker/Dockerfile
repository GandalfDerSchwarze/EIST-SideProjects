#use openjdk 17 as base image
FROM openjdk:17

#create foler in container for app files
RUN mkdir /app

#Copy the files from host machine to image filesystem
COPY out/production/docker/ /app

#Set the directory for commands
WORKDIR /app

#Run the Project
CMD java Main