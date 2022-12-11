## Spring Cloud笔记

### 简介
#### [微服务](https://www.martinfowler.com/articles/microservices.html)

**英文**:

​		In short, the microservice architectural style [[1\]](https://www.martinfowler.com/articles/microservices.html#footnote-etymology) is an approach to developing a single application as a suite of small services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API. These services are built around business capabilities and independently deployable by fully automated deployment machinery. There is a bare minimum of centralized management of these services, which may be written in different programming languages and use different data storage technologies.                       																								 -----[摘自官网]

**中文**:

​		简而言之，微服务架构风格 [1] 是一种将单个应用程序开发为一组小服务的方法，每个小服务都在自己的进程中运行并与轻量级机制（通常是 HTTP 资源 API）进行通信。这些服务是围绕业务能力构建的，并且可以通过全自动部署机制独立部署。这些服务的集中管理最少，可以用不同的编程语言编写并使用不同的数据存储技术。

**通俗定义:**

​		微服务是一种架构，这种架构是将单个的整体应用程序分割成更小的项目关联的独立的服务。一个服务通常实现一组独立的特性或功能，包含自己的业务逻辑和适配器。各个微服务之间的关联通过暴露api来实现。这些独立的微服务不需要部署在同一个虚拟机，同一个系统和同一个应用服务器中。

**微服务与单体应用区别:**

1. 单体应用 ： 将应用程序的所有功能都打包成一个独立的单元，可以是 JAR、WAR、EAR 或其它归档格式。架构如图所示:

   ![单体应用架构图](https://i.postimg.cc/3RkkqPxJ/image-20200708224716035.png)

   单体架构的应用比较容易部署、测试， 在项目的初期，单体应用可以很好地运行。然而，随着需求的不断增加， 越来越多的人加入开发团队，代码库也在飞速地膨胀。慢慢地，单体应用变得越来越臃肿，可维护性、灵活性逐渐降低，维护成本越来越高。下面是单体架构应用的一些缺点：

   - **复杂性高**： 以一个百万行级别的单体应用为例，整个项目包含的模块非常多、模块的边界模糊、 依赖关系不清晰、 代码质量参差不齐、 混乱地堆砌在一起。可想而知整个项目非常复杂。 每次修改代码都心惊胆战， 甚至添加一个简单的功能， 或者修改一个Bug都会带来隐含的缺陷。
   - **技术债务**： 随着时间推移、需求变更和人员更迭，会逐渐形成应用程序的技术债务， 并且越积 越多。“ 不坏不修”， 这在软件开发中非常常见， 在单体应用中这种思想更甚。 已使用的系统设计或代码难以被修改，因为应用程序中的其他模块可能会以意料之外的方式使用它。
   - **部署频率低**： 随着代码的增多，构建和部署的时间也会增加。而在单体应用中， 每次功能的变更或缺陷的修复都会导致需要重新部署整个应用。全量部署的方式耗时长、 影响范围大、 风险高， 这使得单体应用项目上线部署的频率较低。 而部署频率低又导致两次发布之间会有大量的功能变更和缺陷修复，出错率比较高。
     可靠性差： 某个应用Bug，例如死循环、内存溢出等， 可能会导致整个应用的崩溃。
   - **扩展能力受限**： 单体应用只能作为一个整体进行扩展，无法根据业务模块的需要进行伸缩。例如，应用中有的模块是计算密集型的，它需要强劲的CPU； 有的模块则是IO密集型的，需要更大的内存。 由于这些模块部署在一起，不得不在硬件的选择上做出妥协。
   - **阻碍技术创新**： 单体应用往往使用统一的技术平台或方案解决所有的问题， 团队中的每个成员 都必须使用相同的开发语言和框架，要想引入新框架或新技术平台会非常困难。

2. 微服务: 微服务架构，主要是中间层分解，将系统拆分成很多小应用（微服务），微服务可以部署在不同的服务器上，也可以部署在相同的服务器不同的容器上。当应用的故障不会影响到其他应用，单应用的负载也不会影响到其他应用，其代表框架有Spring cloud、Dubbo等。 其架构图如下所示：

   ![微服务架构](https://i.postimg.cc/6pCLj411/image-20200723155352063.png)

   ​	一些优点:

   - **易于开发和维护**： 一个微服务只会关注一个特定的业务功能，所以它业务清晰、代码量较少。 开发和维护单个微服务相对简单。而整个应用是由若干个微服务构建而成的，所以整个应用也会被维持在一个可控状态。
   - **单个微服务启动较快**： 单个微服务代码量较少， 所以启动会比较快。
   - **局部修改容易部署**： 单体应用只要有修改，就得重新部署整个应用，微服务解决了这样的问题。 一般来说，对某个微服务进行修改，只需要重新部署这个服务即可。
   - **技术栈不受限**：在微服务架构中，可以结合项目业务及团队的特点，合理地选择技术栈。例如某些服务可使用关系型数据库MySQL；某些微服务有图形计算的需求，可以使用Neo4j；甚至可根据需要，部分微服务使用Java开发，部分微服务使用Node.js开发。

     微服务虽然有很多吸引人的地方，但它并不是免费的午餐，使用它是有代价的。使用微服务架构面临的挑战。

   - **运维要求较高**：更多的服务意味着更多的运维投入。在单体架构中，只需要保证一个应用的正常运行。而在微服务中，需要保证几十甚至几百个服务服务的正常运行与协作，这给运维带来了很大的挑战。
   - **分布式固有的复杂性**：使用微服务构建的是分布式系统。对于一个分布式系统，系统容错、网络延迟、分布式事务等都会带来巨大的挑战。
   - **接口调整成本高**：微服务之间通过接口进行通信。如果修改某一个微服务的API，可能所有使用了该接口的微服务都需要做调整。
   - **重复劳动**：很多服务可能都会使用到相同的功能，而这个功能并没有达到分解为一个微服务的程度，这个时候，可能各个服务都会开发这一功能，从而导致代码重复。尽管可以使用共享库来解决这个问题（例如可以将这个功能封装成公共组件，需要该功能的微服务引用该组件），但共享库在多语言环境下就不一定行得通了。

#### 架构演变

好的架构并不是设计出来的,一定是进化来的!!!

![架构演变](https://i.postimg.cc/cCXwyQNd/image-20200318082336122.png)

1. All in One Application	单一架构

​		起初当网站流量很小时,将所有功能都写在一个应用里面,对整个应用进行部署,以减少部署节点和成本。对于这个架构简化增删改查的工作量的数据访问框架（ORM）是关键。

 2. Vertical Application      垂直架构

​		当访问量逐渐增大，单一应用增加机器带来的加速度越来越小，提升效率的方法之一是将应用拆成互不相干的几个应用，以提升效率。此时，用于加速前端页面开发的Web框架(MVC)是关键。

 3. Distributed Service    分布式服务架构

​		当垂直应用越来越多，应用之间交互不可避免，将核心业务抽取出来，作为独立的服务，逐渐形成稳定的服务中心，使前端应用能更快速的响应多变的市场需求。此时，用于提高业务复用及整合的分布式服务框架(RPC)是关键。

 4. Elastic Computing	  流动计算架构即微服务架构

​		当服务越来越多，容量的评估，小服务资源的浪费等问题逐渐显现，此时需增加一个调度中心基于访问压力实时管理集群容量，提高集群利用率。此时，用于提高机器利用率的资源调度和治理中心(SOA)是关键

#### Spring Cloud

<img src="https://i.postimg.cc/wjx8pr1h/image.png" style="zoom: 67%; margin-left: 0px;" />

**官网** https://spring.io/projects/spring-cloud 

**英文:**

​		Spring Cloud provides tools for developers to quickly build some of the common patterns in distributed systems (e.g. configuration management, service discovery, circuit breakers, intelligent routing, micro-proxy, control bus, one-time tokens, global locks, leadership election, distributed sessions, cluster state). Coordination of distributed systems leads to boiler plate patterns, and using Spring Cloud developers can quickly stand up services and applications that implement those patterns. They will work well in any distributed environment, including the developer’s own laptop, bare metal data centres, and managed platforms such as Cloud Foundry.                                                                                                             			------摘自官网

**中文:**

​		Spring Cloud 为开发者提供了工具来快速构建分布式系统中的一些常见模式（例如配置管理、服务发现、断路器、智能路由、微代理、控制总线、一次性令牌、全局锁、领导选举、分布式会话，集群状态）。分布式系统的协调导致了样板模式，使用 Spring Cloud 开发人员可以快速建立实现这些模式的服务和应用程序。它们在任何分布式环境中都能很好地工作，包括开发人员自己的笔记本电脑、裸机数据中心和 Cloud Foundry 等托管平台。

**通俗理解:** 

​		springcloud是一个含概多个子项目的开发工具集,集合了众多的开源框架,他利用了Spring Boot开发的便利性实现了很多功能,如服务注册,服务注册发现,负载均衡等.SpringCloud在整合过程中主要是针对Netflix(耐非)开源组件的封装.SpringCloud的出现真正的简化了分布式架构的开发。NetFlix 是美国的一个在线视频网站,微服务业的翘楚,他是公认的大规模生产级微服务的杰出实践者,NetFlix的开源组件已经在他大规模分布式微服务环境中经过多年的生产实战验证,因此Spring Cloud中很多组件都是基于NetFlix。

**核心组件介绍:**

|            组件             |           功能说明           |
| :-------------------------: | :--------------------------: |
| eurekaserver、consul、nacos |       服务注册中心组件       |
|     rabbion & openfeign     | 服务负载均衡 和 服务调用组件 |
| hystrix & hystrix dashboard | 服务断路器  和  服务监控组件 |
|        zuul、gateway        |         服务网关组件         |
|           config            |       统一配置中心组件       |
|             bus             |         消息总线组件         |

**组件基本使用图解:** 

![组件图解](https://i.postimg.cc/m2Hh9tjd/image-20200724161314786.png)

#### 环境搭建与配置配置

**介绍本项目示例代码版本使用:**

|     名字     |      版本      |
| :----------: | :------------: |
| Spring Cloud |  Hoxton.SR12   |
| Spring Boot  | 2.3.12.RELEASE |
|     java     |       8        |

如果需要使用其他版本可以自行查阅官网版本适配介绍，或浏览下方版本对应图表。

**spring-cloud与spring-boot版本对应(持续更新)**

|       SpringCloud版本       |                      	SpringBoot版本                      |
|:-------------------------:|:-------------------------------------------------------:|
|    2021.0.1-SNAPSHOT	     |       Spring Boot >=2.6.4-SNAPSHOT and <2.7.0-M1        |
|         2021.0.0	         |         Spring Boot >=2.6.1 and <2.6.4-SNAPSHOT         |
|       2021.0.0-RC1	       |           Spring Boot >=2.6.0-RC1 and <2.6.1            |
|       2021.0.0-M3	        |          Spring Boot >=2.6.0-M3 and <2.6.0-RC1          |
|       2021.0.0-M1	        |          Spring Boot >=2.6.0-M1 and <2.6.0-M3           |
|         2020.0.5	         |          Spring Boot >=2.4.0.M1 and <2.6.0-M1           |
|       Hoxton.SR12	        |        Spring Boot >=2.2.0.RELEASE and <2.4.0.M1        |
|  Hoxton.BUILD-SNAPSHOT	   |           Spring Boot >=2.2.0.BUILD-SNAPSHOT            |
|        Hoxton.M2	         |          Spring Boot >=2.2.0.M4 and <=2.2.0.M5          |
| Greenwich.BUILD-SNAPSHO	  |    Spring Boot >=2.1.9.BUILD-SNAPSHOT and <2.2.0.M4     |
|      Greenwich.SR2	       |  Spring Boot >=2.1.0.RELEASE and <2.1.9.BUILD-SNAPSHOT  |
|       Greenwich.M1	       |        Spring Boot >=2.1.0.M3 and <2.1.0.RELEASE        |
| Finchley.BUILD-SNAPSHOT	  |   Spring Boot >=2.0.999.BUILD-SNAPSHOT and <2.1.0.M3    |
|       Finchley.SR4	       | Spring Boot >=2.0.3.RELEASE and <2.0.999.BUILD-SNAPSHOT |
|       Finchley.RC2	       |     Spring Boot >=2.0.2.RELEASE and <2.0.3.RELEASE      |
|       Finchley.RC1	       |     Spring Boot >=2.0.1.RELEASE and <2.0.2.RELEASE      |
|       Finchley.M9	        |     Spring Boot >=2.0.0.RELEASE and <=2.0.0.RELEASE     | 
|       Finchley.M7	        |         Spring Boot >=2.0.0.RC2 and <=2.0.0.RC2         |
|       Finchley.M6	        |         Spring Boot >=2.0.0.RC1 and <=2.0.0.RC1         |
|       Finchley.M5	        |          Spring Boot >=2.0.0.M7 and <=2.0.0.M7          |
|       Finchley.M4	        |          Spring Boot >=2.0.0.M6 and <=2.0.0.M6          |
|       Finchley.M3	        |          Spring Boot >=2.0.0.M5 and <=2.0.0.M5          |
|       Finchley.M2	        |          Spring Boot >=2.0.0.M3 and <2.0.0.M5           |
|       Edgware.SR5	        |                     1.5.20.RELEASE                      |
|       Edgware.SR5	        |                     1.5.16.RELEASE                      |
|      Edgware.RELEASE      |                     	1.5.9.RELEASE                      |
|       Dalston.RC1	        |                      1.5.2.RELEASE                      |

**父项目maven配置示例 pom.xml** 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.jason</groupId>
    <artifactId>spring_cloud_learning</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>xxxx</module>
    </modules>

    <!--  继承spring boot的父项目  -->
    <parent>
        <artifactId>spring-boot-starter-parent</artifactId>
        <groupId>org.springframework.boot</groupId>
        <version>2.3.12.RELEASE</version>
    </parent>

    <!--  自定义properties属性  -->
    <properties>
        <spring.cloud-version>Hoxton.SR12</spring.cloud-version>
        <spring.cloud.alibaba-version>2.2.1.RELEASE</spring.cloud.alibaba-version>
    </properties>

    <!--  维护版本  -->
    <dependencyManagement>
        <dependencies>
            <!--      引入spring cloud依赖      -->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring.cloud-version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--     引入spring cloud alibaba 依赖       -->
            <dependency>
                <groupId>com.alibaba.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>${spring.cloud.alibaba-version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

**子项目maven配置示例 pom.xml**

```xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring_cloud_learning</artifactId>
        <groupId>com.jason</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring_cloud_01_eureka_server</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>


    <dependencies>
        <!--  引入springbootweb  -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--   引入eureka注册中心    -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
    </dependencies>

</project>
```

### 注册中心组件

#### Eureka

官方: https://github.com/Netflix/eureka

**介绍:**

Eureka是Netflix开发的服务发现框架，本身是一个基于REST的服务，主要用于定位运行在AWS域中的中间层服务，以达到负载均衡和中间层服务故障转移的目的。 SpringCloud将它集成在其子项目spring-cloud-netflix中，以实现SpringCloud的服务发现功能。

Eureka分为客户端client与服务端server

服务端引入:

```xml
<!--   引入eureka注册中心    -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

服务端配置信息:

```yaml
server:
  port: 8761 # eureka serve 端口号 默认就是8761

spring:
  application:
    name: EUREKA-SERVER # 指定服务名称 注意:服务名不能出现下划线 默认服务名不区分大小写

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka,http://localhost:8762/eureka # eureka server服务注册中心的地址
    fetch-registry: false  # 关闭eureka client立即注册
    register-with-eureka: false # 让当前应用仅仅是服务注册中心,剔除client

#  server:
#    enable-self-preservation: false # 关闭自我保护
#    eviction-interval-timer-in-ms: 3000 # 超时3秒自动清除
```

服务端SpringBoot项目启动类:

```java
@EnableEurekaServer //开启eureka服务端
@SpringBootApplication
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class,args);
    }
}
```

客户端引入: 

```xml
<!--   引入eureka注册中心    -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

客户端配置信息:

```yaml
server:
  port: 9912 # eureka serve 端口号 默认就是8761

spring:
  application:
    name: EUREKA-CLIENT # 指定服务名称 注意:服务名不能出现下划线 默认服务名不区分大小写

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka,http://localhost:8762/eureka,http://localhost:8763/eureka

#  instance:
#    lease-expiration-duration-in-seconds: 10 # 用来修改eureka server默认接受心跳的最大时间 默认90s
#    lease-renewal-interval-in-seconds: 5 # 指定客户端多久向eureka server发送一次心跳 默认30s
```

客户端SpringBoot项目启动类:

```java
@EnableEurekaClient //开启eureka客户端
@SpringBootApplication
public class EurekaClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class,args);
    }
}

```

**Eureka心跳机制:**

​		在应用启动后，节点们将会向Eureka Server发送心跳,默认周期为30秒，如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除(默认90秒)。

**Eureka自动保护机制：**

​		Eureka Server在运行期间会去统计心跳成功的比例在15分钟之内是否低于85% , 如果低于85%， Eureka Server会认为当前实例的客户端与自己的心跳连接出现了网络故障，那么Eureka Server会把这些实例保护起来，让这些实例不会过期导致实例剔除。这样做就是为了防止EurekaClient可以正常运行, 但是与EurekaServer网络不通情况下， EurekaSerJer不会立刻将EurekaClient服务剔除

这样做的目的是为了减少网络不稳定或者网络分区的情况下，Eureka Server将健康服务剔除下线的问题。 使用自我保护机制可以使得Eureka 集群更加健壮和稳定的运行。进入自我保护状态后，会出现以下几种情况：Eureka Server不再从注册列表中移除因为长时间没有收到心跳而应该剔除的过期服务；Eureka Server仍然能够接受新服务的注册和查询请求，但是不会被同步到其他节点上，保证当前节点依然可用。

**自我保护模式：**

​		默认情况下，如果EurekaServer在一定时间内没有接收到某个微服务实例的心跳，EurekaServer将会注销该实例 (默认90秒)。但是当网络分区故障发生(延时、卡顿、拥挤)时,微服务与EurekaServer之间无法正常通信，以上行为可能变得非常危险了——因为微服务本身其实是健康的,此时本不应该注销这个微服务。Eureka通过" 自我保护模式”来解决这个问题——当EurekaServer节点在短时间内丢失过多客户端时(可能发生了网络分区故障)，那么这个节点就会进入自我保护模式。

​		一旦进入该模式，Eureka Server就会保护服务注册表中的信息，不再删除服务注册表中的数据（也就是不会注销任何微服务）。当网络故障恢复后，该Eureka Server节点会自动退出自我保护模式。综上，自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留），也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮、稳定。在Spring Cloud中，可以使用eureka.server.enable-self-preservation = false 禁用自我保护模式。

​		开启自我保护机制：通过配置将判定时间改为10s，接着启动Eureka Server，等待10s之后，就会出现以上提示信息，表示自我保护被激活了。

#### Consul

官网: https://www.consul.io/

**介绍:**

​		Consul是一个**服务网格**解决方案，提供了一个功能齐全的控制平面，具有服务发现、配置和分段功能。这些功能中的每一项都可以根据需要单独使用，也可以一起使用来构建一个完整的**服务网格**。Consul需要一个数据平面，并支持代理和原生集成模型。Consul提供了一个简单的内置代理，因此一切都可以开箱即用，但也支持第三方代理集成，如**Envoy**。 回顾下面的视频，向HashiCorp的联合创始人Armon了解更多关于Consul的信息。

**Consul的主要功能有:**

- **服务发现** : Consul的客户端可以注册一个服务，比如api或mysql，其他客户端可以使用Consul来发现特定服务的提供者。使用DNS或HTTP，应用程序可以很容易地找到他们所依赖的服务。
- **健康检查** : Consul客户端可以提供任何数量的健康检查，要么与给定的服务相关联（如： "webserver是否返回200 OK"），要么与本地节点相关联（如： "内存利用率是否低于90%"）。这些信息可以运维人员用来**监控集群的健康状况**，并被服务发现组件来路由流量（比如： 仅路由到健康节点）
-  **KV存储** ： 应用程序可以利用Consul的**层级K/V**存储来实现任何目的，包括动态配置、功能标记、协调、领导者选举等。Consul提供了HTTP API，使其非常简单以用。
-  **安全服务通信**： Consul可以为服务生成和分发TLS（ [传输层安全性协议](https://baike.baidu.com/item/TLS)）证书，以建立相互的TLS连接。可以使用[Intention](https://www.consul.io/docs/connect/intentions)来定义哪些服务被允许进行通信。服务隔离可以通过可以实时更改[Intention](https://www.consul.io/docs/connect/intentions)策略轻松管理，而不是使用复杂的网络拓扑结构和静态防火墙规则。 
- **多数据中心**: Consul支持开箱即用的**多数据中心**。这意味着Consul的用户不必担心建立额外的抽象层来发展到多个区域。 

**Consul基础架构**

​		Consul是一个分布式、高可用的系统。本节将介绍基础知识，特意省略一些不必要的细节，让你快速了解Consul的工作原理。更多细节，请参考深入的[架构概述](https://yushuai-w.gitbook.io/consul/consul-architecture)。 

每个为Consul提供服务的节点都会运行一个*Consul Agent*。运行代理不需要发现其他服务或获取/设置密钥/值数据。Agent负责对节点上的服务以及节点本身进行健康检查。 

*PS: 为了方便大家阅读，我这里补充一下， Consul Agent 分为两种模式， Server 和 Client模式，一般我们得部署模型是 Server + Client的模式（当然也可以纯Server）, Server 具有Client的全部功能， 但是由于Server负责存储数据，并且强一致性模型的缘故， Server数是有限的（3-5个Server节点，Client可以无限扩展的）更多信息可参考*[*架构概述*](https://yushuai-w.gitbook.io/consul/consul-architecture)*。* 

Agent与一个或多个Consul Server对话。Consul Server是**存储**和**复制数据**的地方。Server本身会选出一个Leader。虽然Consul可以用一台Server来运作，但建议使用3到5台，以避免故障情况导致数据丢失。建议每个数据中心采用Consul服务器集群。

Server Agent维护着一个目录（Catalog），这个目录（Catalog）是由Agent提交的信息汇总形成的。目录维护着集群的高层视图，包括哪些服务可用，哪些节点运行这些服务，健康信息等。关于代理和目录如何交互，可以在[这里](https://yushuai-w.gitbook.io/consul/architecture/anti-entropy)找到。 

需要发现其他服务或节点的基础结构组件可以查询任何Consul Server或任何Consul Agent。Agent将查询自动转发到Server。

Agent会自动将查询转发给Server Agent。 每个数据中心都运行一个Consul Server集群。当有跨数据中心的服务发现或配置请求时，本地Consul Server将请求转发到远程数据中心并返回结果。

**使用：**

Consul的服务端使用go语言进行开发成一个成形客户端:  https://developer.hashicorp.com/consul/downloads 服务端下载

```
控制台开启命令: ./consul agent -dev           # -dev表示开发模式运行，另外还有-server表示服务模式运行
输入http://127.0.0.1:8500/ui/ 访问Consul，可查看到默认的ui界面：
```

| 命令    | 解释                              | 示例              |
| :------ | :-------------------------------- | :---------------- |
| agent   | 运行一个consul agent              | consul agent -dev |
| join    | 将agent加入到consul集群           | consul join IP    |
| members | 列出consul cluster集群中的members | consul members    |
| leave   | 将节点移除所在集群                | consul leave      |

**引入Consul客户端依赖:**

```xml
<!--   引入consul依赖    -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
<!--    健康检查的依赖 必须引入,如不引入则健康检查不会生效    -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

**配置信息：**

```yaml
server:
  port: 9914

spring:
  application:
    name: CONSUL-CLIENT #指定服务名称
  cloud:
    # 服务注册
    consul:
      host: localhost # consul主机地址
      port: 8500 # consul端口号
      discovery:
        service-name: ${spring.application.name} # consul客户端注册名,会替换spring.application.name
        register-health-check: true # 开启健康检查

```

客户端SpringBoot项目启动类: 

```java
@EnableDiscoveryClient //开启一个服务注册客户端
@SpringBootApplication
public class ConsulClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsulClientApplication.class,args);
    }
}

```

**直接启动consul client 出现问题:**

​		原因， consul server 检测所有客户端心跳，但是发送心跳时client必须给予响应才能该服务才能正常使用，在现有客户端中我们并没有引入健康检查依赖，所以导致健康检查始终不通过，导致服务不能使用

#### Nacos

**官网:**  https://nacos.io/zh-cn/index.html

**介绍:** https://nacos.io/zh-cn/docs/what-is-nacos.html

​		Nacos是阿里巴巴开源的一款支持服务注册与发现，配置管理以及微服务管理的组件。用来取代以前常用的注册中心（zookeeper , eureka等等），以及配置中心（spring cloud config等等）。Nacos是集成了注册中心和配置中心的功能，做到了二合一。Nacos 致力于帮助您发现、配置和管理微服务。Nacos 提供了一组简单易用的特性集，帮助您实现动态服务发现、服务配置管理、服务及流量管理。Nacos 帮助您更敏捷和容易地构建、交付和管理微服务平台。 Nacos 是构建以“服务”为中心的现代应用架构(例如微服务范式、云原生范式)的服务基础设施。

**服务端使用: 这里 使用docker方式安装nacos服务端** 

docker 使用 nacos 2.1.0-BETA 搭建**单节点**nacos 通过http://host:port/nacos 访问界面化管理

```bash
1.拉取镜像
docker pull nacos/nacos-server:v2.1.0-BETA
2.运行容器
docker run -d \
-e MODE=standalone \
-e PREFER_HOST_MODE=hostname \
-e SPRING_DATASOURCE_PLATFORM=mysql \
-e MYSQL_SERVICE_HOST= ... \
-e MYSQL_SERVICE_PORT= ...  \
-e MYSQL_SERVICE_USER= ...  \
-e MYSQL_SERVICE_PASSWORD= ...  \
-e MYSQL_SERVICE_DB_NAME= ...  \
-p 8848:8848 \ 
--name nacos \
--restart=always \
nacos/nacos-server:v2.1.0-BETA 
```

docker 使用nacos 2.1.0-BETA 搭建**集群**nacos 

官网集群示意图:

![nacos集群示意图](https://nacos.io/img/deployDnsVipMode.jpg)

创建三个端口 8848 8858 8868分别用于创建docker-nacos示例集群部署

8848:

```bash
docker run -d \
--net=host \   #这里设置了网络模式，如果单台部署三个nacos，后面几台去掉或修改
--restart=unless-stopped \
--name nacos-server8848 \
--env PREFER_HOST_MODE=hostname \
--env MODE=cluster \
--env NACOS_SERVERS=host:8848,host:8858,host:8868 \
--env NACOS_SERVER_IP=host \
--env SPRING_DATASOURCE_PLATFORM=mysql \
--env MYSQL_SERVICE_HOST=host \
--env MYSQL_SERVICE_DB_NAME=nacos_config \
--env MYSQL_SERVICE_USER=root \
--env MYSQL_SERVICE_PASSWORD=xxx \
-p 8848:8848 \
nacos/nacos-server:2.1.0-BETA
```

8858:

```bash
docker run -d \
--restart=unless-stopped \
--name nacos-server8858 \
--env PREFER_HOST_MODE=hostname \
--env MODE=cluster \
--env NACOS_SERVERS=host:8848,host:8858,host:8868 \
--env NACOS_SERVER_IP=host \
--env SPRING_DATASOURCE_PLATFORM=mysql \
--env MYSQL_SERVICE_HOST=host \
--env MYSQL_SERVICE_DB_NAME=nacos_config \
--env MYSQL_SERVICE_USER=root \
--env MYSQL_SERVICE_PASSWORD=xxx \
-p 8858:8848 \
nacos/nacos-server:2.1.0-BETA
```

8868:

```bash
docker run -d \
--restart=unless-stopped \
--name nacos-server8868 \
--env PREFER_HOST_MODE=hostname \
--env MODE=cluster \
--env NACOS_SERVERS=host:8848,host:8858,host:8868 \
--env NACOS_SERVER_IP=host \
--env SPRING_DATASOURCE_PLATFORM=mysql \
--env MYSQL_SERVICE_HOST=host \
--env MYSQL_SERVICE_DB_NAME=nacos_config \
--env MYSQL_SERVICE_USER=root \
--env MYSQL_SERVICE_PASSWORD=xxx \
-p 8868:8848 \
nacos/nacos-server:2.1.0-BETA
```

**客户端使用：** 

**引入配置:**

```xml
<!--   引入nacos注册组件     -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

**配置:** 

```java
server:
  port: 9996
spring:
  application:
    name: NACOS-CLIENT
  cloud:
    nacos:
      discovery:
        server-addr: 43.142.130.227:8848
```

**SpringBoot启动类:**

```java
@SpringBootApplication
@EnableDiscoveryClient //激活服务注册 注册到nacos server中 可以省略不写
public class NacosClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosClientApplication.class,args);
    }
}
```

服务端客户端搭建完毕可以通过  http://localhost:8848/nacos 可视化查看

### 通信组件

#### Ribbon

#### OpenFeign

### 风控组件

#### Hystrix

#### Sentinel

### 网关组件

#### Gateway

### 远程配置组件

#### Config

#### Nacos-Config

### 其他组件
