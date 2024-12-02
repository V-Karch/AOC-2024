#!/bin/bash

IFS=$'\n' read -d '' -r -a lines < "input.txt"

for line in "${lines[@]}"; do
    echo "$line"
done
