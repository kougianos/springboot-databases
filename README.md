# springboot-databases
Spring boot microservice that exposes a simple REST CRUD API and connects to MySQL localhost Database for data persistence. <br>

<b>DB connection details</b>
```
hostname: localhost
port: 3306
database: test 
username: test 
password: test
table: user
```

The project uses springdoc-openapi-ui maven dependency for REST API documentation. <br>
You can find the openAPI specification at http://localhost:8080/v3/api-docs, after you run the application. <br>
You can also find an interactive Swagger UI page at http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

<b>Prerequisites for running locally</b> <br>
* JDK 11
* Maven
* MySQL

<b>Run the application</b> <br>

```bash
git clone https://github.com/kougianos/springboot-databases.git
cd springboot-databases
mvn package
java -jar target/springboot-databases-0.0.1-SNAPSHOT.jar
```

The application uses a simple User DTO for data persistence: <br>
```java
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;
    private String email;
}
```
