[![Build Status](https://app.travis-ci.com/KrylovDenisK/job4j_url_shortcut.svg?branch=main)](https://app.travis-ci.com/KrylovDenisK/job4j_url_shortcut)
[![codecov](https://codecov.io/gh/KrylovDenisK/job4j_url_shortcut/branch/main/graph/badge.svg?token=HQGBZPWG7S)](https://codecov.io/gh/KrylovDenisK/job4j_url_shortcut)

# URL SHORTCUT
REST Service for shortening the link to the target url. He registers his resource on the service, while receiving a personal username and password. For each url, an access code is provided through which the redirection to the target resource is carried out. Also, statistics are kept on the number of calls to the target resource.

### Functionality
 - Site registration
 ```
$ curl -X POST -H "Content-Type:application/json" http://localhost:8080/registration -d '{"site":"your_site"}'

Result: {"id":2,"login":"your_login","password":"your_password","registration":true}
```
- Getting a token
```
$ curl -X POST -i -H "Content-Type:application/json" http://localhost:8080/login -d '{"login":"your_login", "password": "your_password"}'
```
- Convert Link
```
$ curl -X POST -H "Authorization:Bearer your_token" -H "Content-Type:application/json" http://localhost:8080/convert -d '{"url": "your_link"}'
```
- Redirect
```
$ curl -X GET http://localhost:8080/redirect/your_code
```

- Getting statistics
```
$ curl -X GET -H "Authorization:Bearer your_token" http://localhost:8080/statistic
```


## Technologies used
- Spring, Spring BOOT
- Spring DATA JPA, PostgreSQL, Liquibase
- Spring Security, JWT
- Mockito, Junit4
