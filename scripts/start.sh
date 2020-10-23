#!/bin/bash

echo "testing tee functionality"
whatever=$?
echo $whatever

#force an error
grep "where" what.txt

counter=0
while [ $counter -le 20 ]
do
    echo "this is a long process $counter"
    ((counter++))
    #sleep 0.5
done

echo "All done"
exit 0
