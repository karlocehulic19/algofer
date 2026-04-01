# AlgoFER Go Server

## Prerequisites

 - Docker config file, I have aquried mine by doing `docker login`

> This might not be needed later on, as if better solution occurs during deployment, one should be easily converted to dev environment

## Code Runner Architecture

Initialy, for the sake of simplicity, architecture would look something like this:

1. Server copies code to `Solution.java` file 
2. Container copies, compiles and runs `Solution.java`

The problem with this approach becomes logging. Since compilation would probably be done in while image is built, it would be hard to differ if compilation failed, or some other build process. For that reason it seems better to have a container for solution compilation, and a container for solution runtime 
