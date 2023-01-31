# How to build and run the application

Clone the repository and run the following commands in PowerShell from the root folder of the repository:

```powershell
docker run --env POSTGRES_USER=docker --env POSTGRES_PASSWORD=docker --env POSTGRES_DB=docker -p 5432:5432 -v pg_data:<local_folder> -d postgres
mvn package
java -jar .\target\backend-0.0.1-SNAPSHOT.jar
```

# How to test

Import Backend.postman_collection.json in Postman and run the requests to get/add/delete/update resources.
