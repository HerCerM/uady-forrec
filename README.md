# Forrec

Forrec is a forwarder-receiver mini-framework implemented with sockets. It was developed as part of an assignment for my college Software Architecture course.

## Installation and use

This is a Maven project. To install it in your local .m2 directory run the installation command. This also performs the compilation.

```bash
mvn install
```

After installation, you can use the framework in another Maven project by adding the following dependency to the POM file:

```xml
<dependency>
    <groupId>hercerm.net</groupId>
    <artifactId>forwarder-receiver</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## Example project built with Forrec

Voting application: <https://www.youtube.com/watch?v=9jyh9aSZmew>