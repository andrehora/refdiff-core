#!/bin/bash

clone_project() {
	
	project_url=$1
	project_name=$2
	
	folder="projects/"
	project_folder=$folder$project_name
	
	rm -rf $project_folder
	
	echo "Cloning project: "$project_name
	
	git clone $project_url $project_folder
	cd $project_folder
	git log --first-parent --reverse --pretty=format:'%H','%ad','%an' | perl -pe 'END{print "\n"}' > "../commits_"$project_name
	cd ../..
}

while read project_url; do

	project_name=$(echo $project_url | awk -F '/' '{print $NF}')
	
	clone_project $project_url $project_name
 
done < project_urls.txt
