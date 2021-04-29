# Mix

#### 介绍
一个开源的基于SpringCloud + Vue3 + ts + Ant Design 的前后端分离权限管理系统。

Mix(混合)，因为会在学习过程中不断地在这个项目中实践所学，所以与工作项目相比，所选技术不一定是最优解，同学们可以看作是一个技术大DEMO。

我是希望能将所学都体现在这个开源项目中，希望同学们能在这里找到想学的技术的落地实践，当然如果没找到可以给我提下 :)

#### 技术选型
##### 后端
技术 | 作用 | 版本
----|----|---
JDK | Java 开发套件 | 1.8+
Spring Framework | 容器 |5.3.5
Spring Boot | spring 快速开发框架|2.4.4
Spring Cloud | 微服务全家桶 |2020.0.2
Spring Cloud Alibaba | 阿里微服务套件 |2020.0.RC1
MyBatis | ORM框架
MyBatis Generator | 代码生成器
PageHelper | MyBatis物理分页插件
Druid | 数据库连接池
Redis | 分布式缓存数据库
Swagger2 | 接口文档工具
jenkins | 持续集成工具
docker | 容器工具
maven | 项目构建管理


未完待续


##### 前端 （详情请看mix-vue项目）

技术 | 作用 | 版本
----|----|---
vue | js框架 | 3
Ant Design of Vue | UI框架 | 2.1.3 
vue cli | vue 脚手架 | 4.5.12

未完待续



##### 模块说明

MIX

​	|------ mix-admin     管理后台接口服务模块

​	|------ mix-api	       feign接口模块

​	|------ mix-auth         认证服务模块

​	|------ mix-common  公共模块

​	|------ mix-gateway   统一网关模块

​	|------ mix-monitor    系统监控模块

​	|------ mix-vue           前端代码

   

##### 启动说明

###### 后端服务

启动mix-gateway 、mix-admin、mix-auth 顺序不限



###### 前端服务

进入mix-vue

分别执行：

`yarn install`

` yarn serve `