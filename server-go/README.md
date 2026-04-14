# AlgoFER Go Server

## Prerequisites

 - Docker configuration file, I have acquired mine by doing `docker login`

> This might not be needed later on, as if better solution occurs during deployment, one should be easily converted to dev environment

## Code Runner Architecture

Initially, for the sake of simplicity, architecture would look something like this:

1. Server copies code to `Solution.java` file 
2. Container copies, compiles and runs `Solution.java`

The problem with this approach becomes logging. Since compilation would probably be done in while image is built, it would be hard to differ if compilation failed, or some other build process. For that reason it seems better to have a container for solution compilation, and a container for solution runtime 

The question then becomes, how do we make two containers talk with each other. The compilation container has to send the compiled solution to the runner container. That is where we might encounter overhead, and where things also could go wrong.

### 1. Compilation Container

#### Happy path
After compilation succeeds, we will get a .class solution compiled file.

### Middleman

Docker CLI has copy command (`docker cp <CONTAINER:SRC_PATH>`). We can copy compiled file from Compilation container.

### 2. Execution Container

Execution container gets the .class file(ideally with build context copy?) and executes it. If execution throws stdout error, there is probably a bug in the solution. Otherwise we would need to compare the users solution with the correct solution.

