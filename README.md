# Blog Server

基于 Spring Boot 和 MyBatis-Plus 的博客后端服务。

## 技术栈

- Java
- Spring Boot
- MyBatis-Plus
- Redis
- Maven

## 目录结构

```
kkh-springboot/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/seeseesea/blog/
│   │   │   ├── Application.java                # 启动类
│   │   │   ├── controller/                     # 控制器层
│   │   │   ├── core/                           # 核心配置、异常、工具类等
│   │   │   ├── dao/                            # 数据访问层
│   │   │   ├── model/                          # 实体、DTO、请求对象
│   │   │   └── service/                        # 业务逻辑层
│   │   └── resources/
│   │       ├── application.yml                 # 主配置文件
│   │       ├── application-dev.yml             # 开发环境配置
│   │       ├── log.xml                         # 日志配置
│   │       └── mapper/                         # MyBatis-Plus 映射文件
│   └── test/
│       └── java/
└── target/
```

## 快速启动

1. **克隆项目**

   ```bash
   git clone <your-repo-url>
   cd kkh-springboot
   ```

2. **配置数据库与 Redis**
    - 修改 `src/main/resources/application-dev.yml`，配置数据库和 Redis 连接信息。

3. **构建并运行**

   ```bash
   mvn clean package
   java -jar target/kkh-springboot-<version>.jar
   ```

   或使用 IDE 运行 `Application.java`。

## 配置说明

- `application.yml`：主配置文件。
- `application-dev.yml`：开发环境配置，包含数据库、Redis 等信息。
- `log.xml`：日志配置。

## 相关命令

- 构建项目：`mvn clean package`
- 运行项目：`java -jar target/kkh-springboot-<version>.jar`

## 贡献

欢迎提交 issue 和 PR！

## License

MIT
