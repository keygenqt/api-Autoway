Autoway
===================

![picture](data/images/preview.png)

Backend auto-generation on migrations

[![picture](data/images/btn.png)](https://github.com/keygenqt/api-Autoway/raw/master/data/release/Autoway-0.0.1-all.jar)

[![picture](data/images/btn_youtube.png)](https://youtu.be/HHQzB457xmI)

### Idea

Replacing mocks with a full-fledged REST backend. Everything works out of the box, the application just needs to specify
the path to [Flyway](https://flywaydb.org/) migrations.

### Method

* GET = /gen/{table}?search={string}&limit={int}&offset={int} (for list)
* GET = /gen/{table}/{id} (for view)
* POST = /gen/{table} (application/x-www-form-urlencoded body for create)
* PUT = /gen/{table}/{id} (for update)
* DELETE = /gen/{table}/{id} (for delete)

### Options

* migration - Path to directory with sql migrations (required)
* domain - Domain/IP (optional, default - localhost)
* port - Port host (optional, default - 9090)
* mode - Rebuild db after start? *cold* - rebuild (optional, default - *hot*)
* debug - Enable logging (optional)

#### Usage:

```
## just run
java -jar Autoway-0.0.1-all.jar --migration /path/to/your/migrations/dir

## cold mode
java -jar Autoway-0.0.1-all.jar --migration /path/to/your/migrations/dir --mode cold

## debug
java -jar Autoway-0.0.1-all.jar --migration /path/to/your/migrations/dir --debug
```

#### Command line:
```
Usage: Autoway options_list
Options: 
    --migration -> Path to directory with sql migrations (for flyway) (always required) { String }
    --port [9090] -> Port host { Int }
    --domain [localhost] -> Domain/IP { String }
    --debug [false] -> Debug mode 
    --mode [HOT] -> Start mode { Value should be one of [cold, hot] }
    --help, -h -> Usage info 
```

### Architecture

* Framework - [Ktor](https://ktor.io/)
* DI - [Koin](https://insert-koin.io/)
* Protocol - [HTTP (REST API)](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol)
* Data Base - [SQLite](https://www.sqlite.org/index.html)
* Migration - [Flyway](https://flywaydb.org/)
* CLI -  [kotlinx-cli](https://github.com/Kotlin/kotlinx-cli)

# License

```
Copyright 2021 Vitaliy Zarubin

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```