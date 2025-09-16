    #!/bin/bash
    SCRIPT_DIR=$(dirname "$0")
    cd "$SCRIPT_DIR" || exit 1 # Change to script's directory, exit if failed
    # Now, the script is effectively running in its own directory
    # All relative paths will be resolved relative to SCRIPT_DIR
    javac *.java
    cd .. || exit 1
    java -cp . Assignment1.Driver