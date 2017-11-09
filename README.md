# springcloud 微服务实战 
> springcloud官方文档 http://projects.spring.io/spring-cloud/

1. 基于springBoot构建各种微服务项目 
2. 基于Eureka实现注册中心的高可用
3. 基于Ribbon实现客户端负载均衡
4. 基于Hystrix实现服务容错保护
5. 基于Feign实现声明式服务调用
6. 基于Zuul实现API网关
7. 基于Config实现分布式配置
***
### 项目分布

* common    基础服务模块     
* user      用户服务模块      
* register  注册中心      
* support   第三方支持模块      
* gateway   API网关      
* core      工具类，数据初始化      
* mq        分布式消息  
       
***
## Eureka实现高可用注册中心

## Ribbon实现客户端负载均衡

## Hystrix实现服务容错保护

## Feign实现声明式服务调用

## Zuul实现API网关
ignored-services: "*" **这里表示忽略自动为微服务创建路由**

*****
使用yml的配置方式 其中user-route是随便取的对应你所定义的微服务

zuul:
  routes:
    user-route:
      path: /user/**
      service-id: user-service
     
同时指定path和URL，并且不破坏Hystrix、Ribbon特性 [参考配置](http://1754966750.blog.51cto.com/7455444/1958422 "使用Zuul构建微服务网关-路由端点与路由配置详解")
   zuul:
        routes:
          user-route: 
           path: /user/** 
           service-id: user-service
      ribbon: 
        eureka: 
          enabled: false  # 为Ribbon禁用Eureka
      user-service: # 这边是serviceId
        ribbon: 
          listOfServers: http://localhost:8081,http://localhost:8082
另外yml的配置方式可以保证路由规则的有序性，而properties文件无法保证顺序
    例如: 先匹配 user-route 后匹配 common-route 就可以这么写
     zuul:
            routes:
              user-route: 
               path: /user/** 
               service-id: user-service
              common-route:
                path: /com/** 
                service-id: common-service
***
## Config实现分布式配置


