#!/usr/bin/env bash

input_dir=./samples
output_dir=./output/
HEADER="#"
FOOTER="*TERMINATOR*"

[ -d $output_dir ] || mkdir -p $output_dir

for fileinput in $(find $input_dir -type f -print) 
do
	echo $fileinput

	header_found=false
	while IFS= read -r line
	do
		if [[ $line =~ $HEADER ]]; then
			header_found=true
			file_header=$(echo $line | sed 's/ /|/g' | cut -d'|' -f1 | sed 's/#//g')
		fi

		if [[ ("$header_found" == true ) && ( ! -z "$file_header" ) ]]
		then
			echo $line >> $output_dir/${file_header}.txt	
		fi

		if [[ $line =~ $FOOTER ]]; then
			header_found=false
			echo "terminating ... "
		fi

	done < $fileinput
done

