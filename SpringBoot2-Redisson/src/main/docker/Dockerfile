# Start with a base image containing Java runtime. The default timezone is Shanghai.
FROM registry.cn-shanghai.aliyuncs.com/shalousun/alpine-oraclejdk8:1.0

# Add Maintainer Info
MAINTAINER shalousun

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Add a volume pointing to /tmp
VOLUME /tmp

# Add the application's jar to the container
ADD SpringBoot2-Redisson.jar app.jar

RUN sh -c 'touch /app.jar'
# Set jvm
ENV JAVA_OPTS="-server -Xmx512m -Xms512m -Djava.awt.headless=true"

# Run the jar file
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]