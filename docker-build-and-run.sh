## Does not work in VM, since it has no Docker daemon
mvn install
docker build -t dchat .
docker run -p 8080:8080 dchat