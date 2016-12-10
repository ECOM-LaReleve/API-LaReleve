#!/bin/bash

export INSTALL_FOLDER=install
CONFIG_DONE=$INSTALL_FOLDER/config.done

# Command run at the end of this file
START_CONTAINER="/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0"

if [ ! -e $CONFIG_DONE ]; then

    # CONFIGURATION OF WILDFLY ADMINISTRATION
    source $INSTALL_FOLDER/wildfly-env.sh

    if [ $ACTIVATE_ADMIN -eq 1 ]; then
        # Create an admin user and modify the START_CONTAINER command
        $JBOSS_HOME/bin/add-user.sh $ADMIN_USERNAME $ADMIN_PASSWORD
        START_CONTAINER="$START_CONTAINER -bmanagement 0.0.0.0"
    fi;

    # MYSQL
    bash $INSTALL_FOLDER/mysql/setup.sh
    if [ $? -ne 0 ]; then
        exit 1; # Exit on failure
    fi

    # HAWKULAR
    bash $INSTALL_FOLDER/hawkular/setup.sh
    if [ $? -ne 0 ]; then
        exit 1; # Exit on failure
    fi

    # Configuration has been done. Create a file to make sure it isn't run again
    touch $CONFIG_DONE
fi;


# Start wildfly
$START_CONTAINER
