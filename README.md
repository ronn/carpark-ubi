# Carpark Ubi
Srping boot based application to manage the charging points installed at Carpark Ubi

- Build => gradle clean build
- Run tests => gradle test
- Run app => gradle bootRun

The app will be hosted in localhost:800 with context /carpark-ubi. There are 2 resources:

- **/api/report:** Responds to a GET request with the list of all Charging Points
        
        Example: ```http://localhost:8080/carpark-ubi/api/report```
    
- **/api/plug/{chargingPointID}:** Responds to a PUT method with the id of the specific  charging point as a path param. It will set the amperes to the given charging point depending on the availability of other ones and will distribute the amperes for all of them if necessary
The full structure is ```http://localhost:8080/carpark-ubi/api/plug/{chargingPointID}```

Example: ```http://localhost:8080/carpark-ubi/api/plug/CP2```