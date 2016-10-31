#!/usr/bin/env bash

curl -X POST -d @emailRequest.json "http://localhost:8085/email" --header "Content-Type:application/json"