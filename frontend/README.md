## Frontend Local Builds and Tests

### Requirements

- Node (16.11.1) and NPM (8.1.0) installed
- Karma CLI installed: https://karma-runner.github.io/6.3/intro/installation.html
- For Windows see [npm-on-windows](npm-on-windows.md)

### Initialize Project

`npm install`

### Build Frontend with Maven

#### Generate jar that includes frontend sources

`mvn install`

### Build Frontend with NPM

#### Run tests and copy frontend sources to `/target/resources`

`npm run build`

### Run Frontend Tests Locally

#### Run tests using NPM

`npm test`

#### Run tests continually with auto-watch in ChromeHeadless

`karma start` 

#### Run tests once with Chrome

`karma start --singleRun --browsers Chrome`