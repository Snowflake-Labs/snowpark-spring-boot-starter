# Snowpark Spring Boot Starter

This project holds
the [Spring Boot Starter](https://docs.spring.io/spring-boot/reference/features/developing-auto-configuration.html#features.developing-auto-configuration.custom-starter.starter-module)
for [Snowpark Java](https://docs.snowflake.com/en/developer-guide/snowpark/index).

The goal of this project is to abstract the boilerplate code that is used in wiring up a Spring Boot project
with [Snowflake](https://snowflake.com).

If you are new to Snowflake, get started with a [trial](https://signup.snowflake.com/) today.

## Modules

The project has two modules

* [snowpark-spring-boot-autoconfigure](./snowpark-spring-boot-autoconfigure) - This is the core of the starter project
  which helps auto configuration of Snowpark Java project using application properties and exposes a Snowpark Java
  `session` bean.
* [snowpark-spring-boot-starter](./snowpark-spring-boot-starter) - The custom starter project which will be used by the
  Spring Boot applications.
* [demo-app](./demo-app) - A Spring Web Application that uses the starter

> [!IMPORTANT]
> The code is work in progress and may undergo rapid changes.
>

## Build

Install the artifacts in local maven repository.

```shell
./mvnw -DskipTests clean install
```

Check out the [README](./demo-app/README.md) to run the demo application that uses this starter.
