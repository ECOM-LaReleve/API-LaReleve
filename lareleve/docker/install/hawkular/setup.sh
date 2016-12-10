#!/bin/bash

# HAWKULAR
if [ ! -z "$HAWKULAR_USERNAME" ]; then
    cd $INSTALL_FOLDER/hawkular/
    curl -u $HAWKULAR_USERNAME:$HAWKULAR_PASSWORD http://$HAWKULAR_HOST/hawkular/wildfly-agent/download
    if [ $? -ne 0 ]; then
        exit 1; # Exit on failure
    fi
    java -jar hawkular/hawkular-wildfly-agent-installer.jar --target-location=/opt/jboss/wildfly --username $HAWKULAR_USERNAME --password $HAWKULAR_PASSWORD
    cd -
fi;
