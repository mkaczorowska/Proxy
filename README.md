# Proxy

Proxy is Java application for fetching information about GitHub users repositories.

## Installation

Using Maven generate *.jar file, which will be present in /target directory.
From main folder build docker image:
```java
docker build -f Dockerfile -t proxy-app .
```
Then run docker image:
```java
docker run -p 8000:8080 <IMAGE_ID>
```

## Usage

Use port 8000 (set while running docker image) for GET command. Set path to "/repositories/getForUser". Set header "Accept: application/json" and set "username" parameter to chosen user. You can use for example Postman application for that. For example:

```java
http://localhost:8000/repositories/getForUser?username=octocat
```
## Documenatation

```java
http://localhost:8000/v2/api-docs
```
```java
http://localhost:8000/swagger-ui/
```
