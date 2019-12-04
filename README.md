#### 项目介绍

* 这是一个基于Spring Boot框架，采用MySQL进行数据持久化的多人在线博客平台。实现了用户注册、登陆、写博客、以及注销登陆等功能。

#### 实现思路
* 1.在配置好SpringBoot基本配置文件，以及使用Docker方式启动MySQL数据库之后，通过Flyway完成建表语句。
* 2.接着根据Java Web的分层思想，在configuration中完成Bean的配置，在controller中完成为用户设置或注销Cookie，返回用户及博客信息等。在dao层中完成与数据库的交互，以及在service层中完成业务逻辑，包括判断用户名是否存在，以及获取博客的总页数等。
* 3.最后通过JUnit5单元测试框架，以及Travis CI编写测试方法，以及配置log4j日志框架打印运行过程中的详细信息。

#### 如何使用
将代码拷贝至本地后，需要：
* 1.使用Docker方式启动本地数据库，命令如下：
```
docker run --name mysql -p 3306:3306 -v `pwd`/database:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=password -d mysql:5.7
```
* 2.在确保启动数据库后，使用`mvn flyway:clean flyway:migrate`清除本地缓存后，初始化数据库。
* 3.正常运行之后，在本地的8080端口，就可以看到项目的初始页面了。

#### 联系作者
邮箱：mryuyan97@gmail.com
