# lv-fox (WIP)

`lv-fox` is search API server for [my blog](https://yoshinorin.net/). I call this project `love - fox`.

## Requirements

* sbt 1.3.x
* Scala 2.13.x
* JVM 11
* MariaDB 10.x

## Documentation

* [API](https://yoshinorin.github.io/lv-fox/)

## Set up

<details>
  <summary>Preconditions</summary><br>

Prepare [requirements](#requirements) environment before setup.

</details>

<details>
  <summary>Download application</summary><br>

`git clone https://github.com/YoshinoriN/lv-fox.git`

</details>

<details>
  <summary>DataBase</summary><br>

Create a database schema. Also, schema name is anything will be fine.

```sql
CREATE DATABASE lvfox;
```

</details>

<details>

  <summary>Configuration</summary><br>

After done above procedure, [set system environment](#configuration) before start server.

</details>

## Start server

```
$ sbt run
```

## Docker integration

`lv-fox` provides [docker image](https://hub.docker.com/repository/docker/yoshinorin/docker-lvfox). Please see [docker-compose.yml](https://github.com/YoshinoriN/lv-fox/blob/master/docker/docker-compose.yml)

## Configuration

`lv-fox` reads all settings from the system environment variable. You have to set the following system environment variables.

> [Examples](https://github.com/YoshinoriN/lv-fox/blob/master/src-backend/scripts/devenv.sh)

### Database

|Property|Description|Type|Default|Example|
|---|---|---|---|---|
|`LVFOX_DB_DATASOURCE_URL`|Data source url for JDBC connection.|`string`|-|`jdbc:mariadb://127.0.0.1/lvfox?useUnicode=true&characterEncoding=utf8mb4`|
|`LVFOX_DB_USER`|Database user name.|`string`|-|`root`|
|`LVFOX_DB_PASSWORD`|Database user password.|`string`|-|`pass`|
|`LVFOX_DB_CONNECTION_TIMEOUT`|Database connection timeout.|`int`|`30000`|`30000`|
|`LVFOX_DB_MAXIMUM_POOLSIZE`|Connection pool size.|`int`|`6`|`6`|

### Play framework

|Property|Description|
|---|---|
|`LVFOX_APPLICATION_SECRET`|[Play Framework - application secret](https://www.playframework.com/documentation/2.8.x/Deploying#The-application-secret)|
|`LVFOX_CORS_ALLOW_ORIGIN`|[Play Framework - Configuring the CORS filter](https://www.playframework.com/documentation/2.8.x/CorsFilter#Configuring-the-CORS-filter)|
|`LVFOX_ALLOW_HOST `|[Play Framework - Allowed hosts filter](https://www.playframework.com/documentation/2.8.x/AllowedHostsFilter)|`string`|-|

### Authentication

`lv-fox` requier access token. Please set belows to your enviroment variables.

|Property|Description|Type|Default|
|---|---|---|---|
|`LVFOX_SEARCH_TOKEN `|access token for search api|`string`|-|
|`LVFOX_POST_TOKEN `|access token for POST api|`string`|-|

## API Execute examples

```sh
// POST data
$ curl -D - -X POST -H "Authorization: Bearer 123456789" -H "Content-Type: application/json" -d \
  '{"url":"https://example.com", "title":"hello lv-fox", "content": "hogehogehoge....", "publishedAt": 1580240624, "updatedAt": 1580249990 }' \
  127.0.0.1:9000/page
```

## Using Stacks

|Stack|-|
|---|---|
|[Scala](https://www.scala-lang.org/)|-|
|[Play Framework](https://www.playframework.com/)|Framework|
|[Flyway](https://flywaydb.org/)|Database Migration|
|[quill](https://getquill.io/)|Database Library|
|[circe](https://circe.github.io/circe/)|JSON Library|
|[play-circe](https://github.com/jilen/play-circe)|JSON Library (for request & response)|
|[MariaDB](https://mariadb.org/)|Database|
|[ScalaTest](http://www.scalatest.org/)|Unit test|
|[Scalafmt](https://scalameta.org/scalafmt/)|Code formatter|
|[ReDoc](https://github.com/Rebilly/ReDoc)|Generate API documentation |
|[GitHub Pages](https://pages.github.com/)|Hosting API docuementation|


# License

This code is open source software licensed under the [Apache 2.0 License](https://www.apache.org/licenses/LICENSE-2.0.html).
