# Spring Security Demo

一个基于 Spring Boot 的 Spring Security 示例项目，包含表单登录、基于角色的访问控制、内存用户配置，以及密码编码与校验示例。

## 环境要求

- Java 21

## 运行

```bash
./gradlew bootRun
```

应用默认运行在 [http://localhost:8000](http://localhost:8000)。登录页面为 `/login.html`，认证成功后进入 `/index.html`。

## 示例账号

| 用户名 | 密码 | 角色 |
| --- | --- | --- |
| `user1` | `pa1ss0wd1` | USER |
| `user2` | `123457` | USER |
| `admin` | `123458` | ADMIN |

用户数据保存在内存中，应用重启后会重新初始化。
