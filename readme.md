How to run:
1. Compile fat jars of each service with 'gradle bootJar'
2. Start services with docker-compose.yml
3. Send HTTP request to localhost:8081/api/v1/add with left and right Int parameters, response will be request number
4. Get the result via localhost:8081/api/v1/result with requestNumber parameter.
