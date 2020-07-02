# lv-fox

WIP

## Requirements

* sbt 1.3.x
* Scala 2.13.x
* JVM 11
* MariaDB 10.x

## Set up

Create a database schema. Also, schema name is anything will be fine.

```sql
CREATE DATABASE lvfox;
```

## Start server

```
$ sbt run
```

## API Execute examples

```sh
// POST data
$ curl -D - -X POST -H "Content-Type: application/json" -d \
  '{"url":"https://example.com", "title":"hello lv-fox", "content": "hogehogehoge....", "publishedAt": 1580240624, "updatedAt": 1580249990 }' \
  127.0.0.1:9000/page
```
