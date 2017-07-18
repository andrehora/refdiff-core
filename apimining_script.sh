#!/bin/bash

apimining() {
	
	file=$1
	
	total=$(more $file | awk -F ';' '{print $1, $3, $4}' | sort | uniq | wc -l)
	both=$(more $file | awk -F ';' '{print $3, $4}' | sort | uniq | wc -l)
	tracked=$(more $file | awk -F ';' '{print $1, $3, $4}' | sort | uniq | grep "SameMethod" | wc -l)
	untracked=$(more $file | awk -F ';' '{print $1, $3, $4}' | sort | uniq | grep -E "RenameMethod|MoveMethod" | wc -l)
	inter=$(($total-$both))
	tracked_only=$(($tracked-$inter))
	untracked_only=$(($untracked-$inter))

	echo $file, "Tracked:"$tracked, "Both:"$both, "UntrackedOnly:"$untracked_only
}

apimining "apimining_glide"
apimining "apimining_fresco"
apimining "apimining_guice"
apimining "apimining_MPAndroidChart"
apimining "apimining_che"
apimining "apimining_Android-Universal-Image-Loader"
apimining "apimining_storm"
apimining "apimining_clojure"