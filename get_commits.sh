#!/bin/bash
git log --first-parent --first-parent --reverse --pretty=format:'%H',%ad $@ | perl -pe 'END{print "\n"}' > ../../commits