#!/bin/bash

clone_project() {
	
	url=$1
	project_name=$(echo $url | awk -F '/' '{print $NF}')
	rm -rf $project_name
	
	echo "Cloning project: "$project_name
	
	git clone $url
	cd $project_name
	git log --first-parent --reverse --pretty=format:'%H','%ad','%an' | perl -pe 'END{print "\n"}' > "../commits_"$project_name
	cd ..
}

cd projects

clone_project "https://github.com/mikepenz/LollipopShowcase"
