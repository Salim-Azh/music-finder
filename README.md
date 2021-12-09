# music-finder

This is a Java application that allows registered users to search for songs using iTunes API.
## Installation
In order to install everything you need to the run the project, you'll have to use the command `mvn install -f "path/to/project/directory/pom.xml"`

## Build
To build the project and run the tests, use `mvn test -f "path/to/project/directory/pom.xml"`

## Usage
To build and run the project, use `mvn clean compile exec:java`
To build, run the tests and run the application, use `mvn clean test exec:java`

## Branching procedure

Create the branch from *dev* for the corresponding use case with the name like :
*MFUCXX_name* where *XX* is the use case number and *name* is your name

## Commit convention

* build : changes that affect the build system or external dependencies
 \<unit\> message
* test-green: \<acceptance\> \<unit\> message
* refactor : any modification that does not bring new features
