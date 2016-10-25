FROM jboss/wildfly:latest
MAINTAINER Rémi GATTAZ "remi.gattaz@gmail.com"

# Add application ear and new entrypoint
ADD build/lareleve.ear /opt/jboss/wildfly/standalone/deployments/
ADD entrypoint.sh /entrypoint.sh

# Entrypoint uses files in folder install
# creation of folder before ADD and VOLUME so that we can write in the folder inside the container
RUN mkdir install
ADD install install
VOLUME ["/install"]

# Expose port of application and administation interfaces
EXPOSE 8080 9990

# Entrypoint
CMD ["sh", "-c", "/entrypoint.sh"]
