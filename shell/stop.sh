#!/bin/bash
BIN_DIR=`pwd`
cd ..
PIDS=""
if [ -f "server.pid" ] ; then 
    echo "finded pid file!"
    PIDS=`cat server.pid`
    rm -rf server.pid
else
    echo "ERROR: pid file not exists!"
    PIDS=`$BIN_DIR/getpid.sh`
fi

cd $BIN_DIR
if [ -z "$PIDS" ]; then
    echo "ERROR: The service does not started!"
    exit 1
fi
echo -e "Stopping the service ..."
for PID in $PIDS ; do
    kill $PID > /dev/null 2>&1
    echo "killed PID: $PID"
done

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in $PIDS ; do
        PID_EXIST=`ps -f -p $PID | grep java`
        if [ -n "$PID_EXIST" ]; then
            COUNT=0
            break
        fi
    done
done

echo "Stopped OK!"
echo "PID: $PIDS"
