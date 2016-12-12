#!/bin/bash

JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh

# CONFIGURATION OF THE MYSQL datasource
source $INSTALL_FOLDER/mysql/mysql-env.sh

# Install mysql connector
connector_location=$JBOSS_HOME/modules/com/mysql/jdbc/main/
curl --remote-name http://central.maven.org/maven2/mysql/mysql-connector-java/6.0.4/mysql-connector-java-6.0.4.jar
if [ $? -ne 0 ]; then
    exit 1; # Exit on failure
fi
mkdir -p $connector_location
mv mysql-connector-java-6.0.4.jar $connector_location
cat $INSTALL_FOLDER/mysql/connector.xml >> $connector_location/module.xml

# Instructions to add mysql driver and datasource in wildfly
# 1) Start embed-server
# $ embed-server --std-out=echo
# 2) Add MySQL driver
# $ /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql.jdbc,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource)
# 3) Create datasource
# $ data-source add --name=UnifiedPushDS --driver-name=mysql --jndi-name=java:LaReleveDS --connection-url=jdbc:mysql://$MYSQL_HOST/$MYSQL_DATABASE -—user-name=$MYSQL_USER -—password=$MYSQL_PASSWORD --use-ccm=false --max-pool-size=25 --blocking-timeout-wait-millis=5000 --enabled=true
# Full bloc of instruction
$JBOSS_CLI "embed-server --std-out=echo,
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql.jdbc,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource),
data-source add --name=LaReleveDS --driver-name=mysql --jndi-name=java:/LaReleveDS --connection-url=jdbc:mysql://$MYSQL_HOST/$MYSQL_DATABASE?autoReconnect=true&useSSL=false --user-name=$MYSQL_USER
--password=$MYSQL_PASSWORD --use-ccm=false --max-pool-size=25 --blocking-timeout-wait-millis=5000 --enabled=true"
