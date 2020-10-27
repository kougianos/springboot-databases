# springboot-databases
Spring boot microservice that exposes a simple REST CRUD API and connects to MySQL localhost Database for data persistence. <br>

####DB connection details
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

####Prerequisites for running locally <br>
* JDK 11
* Maven
* MySQL

####Run the application</b> <br>

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
To perform operations on the database using the API you can use Postman or another tool of your preference


####One Time Pin implementation <br>
Using the below maven dependency (link: https://github.com/jchambers/java-otp)
```xml
<dependency>
  <groupId>com.eatthepath</groupId>
  <artifactId>java-otp</artifactId>
  <version>0.2.0</version>
</dependency>
```
the service offers an easy-to-use One Time Pin generation and validation, exposing 2 HTTP request methods to clients/consumers.
