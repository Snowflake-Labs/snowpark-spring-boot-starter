# Snowpark Spring Boot Stater Demo Application

A demo project to demonstrate how to
use [Snowpark Java](https://docs.snowflake.com/en/developer-guide/snowpark/java/index) Spring Boot Starter](
../snowpark-spring-boot-starter).

> [!IMPORTANT]
> This is proof of concept and not a production ready code.
>

## Build and Run the application

### Set the environment variables

> [!NOTE]
> Currently the starter supports using password and keypair for Snowflake authentication.

Edit the [application.properties](./src/main/resources/application.properties) and update the following properties,

```properties
spring.application.name=demo-app
snowflake.connection.account-identifier=<your account id>
snowflake.connection.user-name=<your snowflake username>
snowflake.connection.password=<your snowflake password>
snowflake.connection.database=KAMESH_DEMO_DB
snowflake.connection.schema=DATA
```

> [!TIP]
> Use the `demo.sql` from [repo](https://github.com/kameshsampath/sf-git-integration-demo.git), to load and setup
> the `TODOS` table with few records. The script will create `KAMESH_DEMO_DB` database and `DATA` schema for you.
>

### Run the Application

```shell
./mvnw spring-boot:run
```

(OR)

Build the jar,

```shell
./mvnw clean package
```

Run the jar,

```shell
 java --add-opens=java.base/java.nio=ALL-UNNAMED -Dnet.snowflake.jdbc.enableBouncyCastle=true -jar target/demo-app-0.0.1-SNAPSHOT.jar
```

> [!NOTE]
> * The `add-opens` option is required for using reflection on closed/unexported memebers of java.nio package
> * The `net.snowflake.jdbc.enableBouncyCastle` is required to use Bouncy Castle security factory while using KeyPair

The application should now be available at <http://localhost:8080>.

## Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/maven-plugin/build-image.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#using.devtools)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#web.servlet.spring-mvc.template-engines)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#web)

### Guides

The following guides illustrate how to use some features concretely:

* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
