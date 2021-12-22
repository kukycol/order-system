密码模式（用户）

```
1）登录请求
post from-data localhost:8080/oauth/token

client_id=client_password
client_secret=123456
grant_type=password
username=admin
password=123456
```

授权码模式（用户）

```
1）在浏览器输入地址获取code
get http://localhost:8080/oauth/authorize?response_type=code&state=1321&client_id=client_authorization&redirect_uri=http
://example.com&scope=read

2）获取token
post form-data http://localhost:8080/oauth/token?state=1321&client_id=client_authorization&client_secret=123456
&grant_type=authorization_code&code=UjtoKg&redirect_uri=http://example.com

client_id=client_authorization
client_secret=123456
grant_type=authorization_code
code=code
redirect_uri=http://example.com
```

简化模式（用户）

```
1）在浏览器输入地址获取token
get http://localhost:8080/oauth/authorize?response_type=token&client_id=client_implicit&redirect_uri=http://example.com
```

凭证模式（无用户）

```
1）获取token
post form-data http://localhost:8080/oauth/token

client_id=client_credentials
client_secret=123456
grant_type=client_credentials
```

刷新token

```
post form-data http://localhost:8080/oauth/token

client_id="",
client_secret="",
grant_type="",
refresh_token=""
```