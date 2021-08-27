
```shell
curl -X POST -H "Content-Type: application/json" -d '{"username": "forsrc", "password": "forsrc"}' http://localhost:8080/jredmine-server/jwt/login -s

```

```shell

curl -X GET http://localhost:8080/jredmine-server/jwt/user_info  --header "Authorization: Bearer `curl -X POST -H "Content-Type: application/json" -d '{"username": "forsrc", "password": "forsrc"}' http://localhost:8080/jredmine-server/jwt/login -s -v 2>&1 | grep "Authorization:" | awk '{print $3}'`"


```

```shell
JWT_TOKEN=`curl -X POST -H "Content-Type: application/json" -d '{"username": "forsrc", "password": "forsrc"}' http://localhost:8080/jredmine-server/jwt/login -s -v 2>&1 | grep "Authorization:" | awk '{print $3}'`
curl -X GET --header "Authorization: Bearer $JWT_TOKEN" http://localhost:8080/jredmine-server/api/user/
```