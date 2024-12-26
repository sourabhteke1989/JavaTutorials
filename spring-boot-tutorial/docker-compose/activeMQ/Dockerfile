# Using jdk as base image
FROM openjdk:8-jdk-alpine

# Copy the whole directory of activemq into the image
ADD apache-activemq-5.16.4-bin.tar.gz /opt

# Set the working directory to the bin folder 
WORKDIR /opt/apache-activemq-5.16.4/bin

# Start up the activemq server
ENTRYPOINT ["./activemq","console"]