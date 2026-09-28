FROM tomcat:9.0-jdk17-openjdk-slim
RUN rm -rf /usr/local/tomcat/webapps/ROOT
RUN sed -i 's/port="8005"/port="-1"/g' /usr/local/tomcat/conf/server.xml
COPY dist/javaMail.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]