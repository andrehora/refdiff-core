#!/bin/bash

clone_project() {
	
	url=$1
	project_name=$(echo $url | awk -F '/' '{print $NF}')
	
	echo "Cloning project: "$project_name
	
	git clone $url
	cd $project_name
	git log --first-parent --reverse --pretty=format:'%H','%ad','%an' | perl -pe 'END{print "\n"}' > "../commits_"$project_name
	cd ..
}

cd projects
clone_project "https://github.com/andrehora/method-blame"
clone_project "https://github.com/iluwatar/java-design-patterns"
clone_project "https://github.com/bumptech/glide.git"