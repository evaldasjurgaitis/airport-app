# Getting Started with Airport app

Project acceptance criteria: https://github.com/evaldasjurgaitis/airport-app/projects

Project kanban board: https://github.com/evaldasjurgaitis/airport-app/projects/1

Prerequisites:

To run Airport app locally need to have: node(v12.18.3), npm(6.14.7), mysql(8.0), JAVA 16 or docker and JAVA 16.

Run application using docker:
1. Build airport-backend. (On <b>airport-backend</b> directory in terminal write command`.\gradlew build` or `gradlew build` or `./gradlew build` it depends on what terminal use.)
2. Build airport-batch. (On <b>airport-batch</b> directory in terminal write command `.\gradlew build` or `gradlew build` or `./gradlew build` it depends on what terminal use.)
3. On <b>airport-ops</b> directory in terminal write command `docker-compose up`
4. Wait while all images create and containers start run.
5. On browser go to http://localhost:3000
6. Upload csv files in Admin panel 

Run application using local mysql database:
1. In mysql database execute `init.sql` script. Script is on `airport-ops` module.
2. Run airport-backend. (On <b>airport-backend</b> directory in terminal write command `.\gradlew bootRun` or `gradlew bootRun` or `./gradlew bootRun` it depends on what terminal use. )
3. Run airport-batch. (On <b>airport-batch</b> directory in terminal write command `.\gradlew bootRun` or `gradlew bootRun` or `./gradlew bootRun` it depends on what terminal use.)
4. Run price-service. (On `airport-ops/priceService` directory in terminal write command `java -jar provider.jar`)
5. Run airport-ui. (On <b>airport-ui</b> directory in terminal write command `npm start`).
6. On browser go to http://localhost:3000
7. Upload csv files in Admin panel 

Files upload works on async mode no need to wait while files will finish uploaded:
1. (task_countries.csv approximately will upload per 1s it depends on computer parameters)
2. (task_regions.csv approximately will upload per 10s it depends on computer parameters)
3. (task_airports.csv approximately will upload per ~14-18 min it depends on computer parameters)