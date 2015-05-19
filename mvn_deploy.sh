#!/bin/bash

mvn -e -DuniqueVersion=false -DperformRelease=true compile deploy source:jar deploy

