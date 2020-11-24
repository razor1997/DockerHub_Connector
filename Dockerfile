FROM java:8
COPY . /usr/src/app
WORKDIR /usr/src/app
COPY pwcho.jar /usr/src/app
CMD ["java", "-jar", "pwcho.jar"
