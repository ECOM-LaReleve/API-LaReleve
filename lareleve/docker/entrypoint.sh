#!/bin/bash

INSTALL_FOLDER=install
CONFIG_DONE=$INSTALL_FOLDER/config.done
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh

# Command run at the end of this file
START_CONTAINER="/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0"

if [ ! -e $CONFIG_DONE ]; then

    # CONFIGURATION OF WILDFLY ADMINISTRATION
    source $INSTALL_FOLDER/wildfly-env.sh

    if [ $ACTIVATE_ADMIN -eq 1 ]; then
        # Create an admin user and modify the START_CONTAINER command
        /opt/jboss/wildfly/bin/add-user.sh $ADMIN_USERNAME $ADMIN_PASSWORD
        START_CONTAINER="$START_CONTAINER -bmanagement 0.0.0.0"
    fi;


    # CONFIGURATION OF THE MYSQL
    source $INSTALL_FOLDER/mysql-env.sh
    # Install mysql connector
    connector_location=$JBOSS_HOME/modules/com/mysql/jdbc/main/
    curl --remote-name http://central.maven.org/maven2/mysql/mysql-connector-java/6.0.4/mysql-connector-java-6.0.4.jar
    mkdir -p $connector_location
    mv mysql-connector-java-6.0.4.jar $connector_location
    echo '<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.0" name="com.mysql.jdbc">
  <resources>
    <resource-root path="mysql-connector-java-6.0.4.jar"/>
    <!-- Insert resources here -->
  </resources>
  <dependencies>
    <module name="javax.api"/>
    <module name="javax.transaction.api"/>
  </dependencies>
</module>' >> $connector_location/module.xml

    # Instructions to add mysql driver and datasource in wildfly
    # 1) Start embed-server
    # $ embed-server --std-out=echo
    # 2) Add MySQL driver
    # $ /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql.jdbc,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource)
    # 3) Create datasource
    # $ data-source add --name=UnifiedPushDS --driver-name=mysql --jndi-name=java:LaReleveDS --connection-url=jdbc:mysql://$MYSQL_HOST/$MYSQL_DATABASE —user-name=$MYSQL_USER —password=$MYSQL_PASSWORD --use-ccm=false --max-pool-size=25 --blocking-timeout-wait-millis=5000 --enabled=true
    # Full bloc of instruction
    $JBOSS_CLI "embed-server --std-out=echo,
    /subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql.jdbc,driver-xa-datasource-class-name=com.mysql.jdbc.jdbc2.optional.MysqlXADataSource),
    data-source add --name=LaReleveDS --driver-name=mysql --jndi-name=java:/LaReleveDS --connection-url=jdbc:mysql://$MYSQL_HOST/$MYSQL_DATABASE?autoReconnect=true&useSSL=false —user-name=$MYSQL_USER —password=$MYSQL_PASSWORD --use-ccm=false --max-pool-size=25 --blocking-timeout-wait-millis=5000 --enabled=true"

    # Configuration has been done. Create a file to make sure it isn't run again
    touch $CONFIG_DONE
fi;


# Start wildfly
$START_CONTAINER
