#!/bin/bash 

# execute in background

# remote file 'watcher'
if [ $# -lt 1 ] ; then
    echo "Usage: $0 <FILENAME>"
    exit 1
fi

LOGFILE=logme.txt

function logme()
{
    #echo "`date` : $1"
    echo "`date` : $1" >> $LOGFILE
} 

COBDATE=$(date -v -1d +%Y%m%d)
INFILE=$1.${COBDATE}
TIMEUNTIL=55
DELAYINSEC=10

while true; do

    if [ -f $INFILE ] ; then
        logme "${INFILE} is now available."
        break
    fi

    #change to hour
    if [ $TIMEUNTIL ] && [ $(date +%M) -ge $TIMEUNTIL ] ; then
        logme "We have reached time limit."
        break
    else 
        logme "Not yet timeout... "
    fi

    logme "Checking for file $INFILE remotely..."
    
    # dummy for now ... do a remote fetch
    cp /var/tmp/$INFILE ./$INFILE
    if [ $? -ne 0 ] ; then
        logme "Failed to fetch file ? "
    fi
    
    if [ -f $INFILE ] ; then
        logme "${INFILE} is now available."
        break
    fi    # check file present then exit

    # not present ? 
    logme "Sleeping until next cycle"
    sleep $DELAYINSEC
done


if [ ! -f $INFILE ] ; then
    logme "File not available."
    exit 1
fi 

logme "File found!"
exit 0
