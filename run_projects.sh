#!/bin/bash

clone_project() {
	
	project_url=$1
	project_name=$2
	project_folder=$3
	
	rm -rf $project_folder
	
	git clone $project_url $project_folder
	cd $project_folder
	git log --first-parent --reverse --pretty=format:'%H','%ad','%an' | perl -pe 'END{print "\n"}' > "../commits_"$project_name
	cd ../..
}

folder="projects/"

while read project_url; do

	project_name=$(echo $project_url | awk -F '/' '{print $NF}')
	project_folder=$folder$project_name
	
	echo "Cloning project: "$project_name
	clone_project $project_url $project_name $project_folder
	
	echo "Running refdiff: "$project_name
	java -jar refdiff.jar $project_name
	
	echo "Removing project: "$project_name
	rm -rf $project_folder
 
done < project_urls2.txt
