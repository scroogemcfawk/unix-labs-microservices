How to run:
1. Compile fat jars of services with 'gradlew jar' command.
2. Start services with docker-compose.yml 
3. Start main service (api-service/src/main/kotlin/com/github/scroogemcfawk/unix_lab_microservices/api_service/Main.kt)
4. Send HTTP request to localhost:8081/api/v1/add with left and right Int parameters, response will be request number
5. Get the result via localhost:8081/api/v1/result with requestNumber parameter.
