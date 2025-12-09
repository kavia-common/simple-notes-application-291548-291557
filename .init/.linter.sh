#!/bin/bash
cd /home/kavia/workspace/code-generation/simple-notes-application-291548-291557/notes_backend
./gradlew checkstyleMain
LINT_EXIT_CODE=$?
if [ $LINT_EXIT_CODE -ne 0 ]; then
   exit 1
fi

