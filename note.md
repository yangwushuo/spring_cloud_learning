spring-cloud与spring-boot版本对应(持续更新)

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

*Eureka心跳机制*:
在应用启动后，节点们将会向Eureka Server发送心跳,默认周期为30秒，如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除(默认90秒)。


*Eureka自动保护机制*：
Eureka Server在运行期间会去统计心跳成功的比例在15分钟之内是否低于85% , 如果低于85%， Eureka Server会认为当前实例的客户端与自己的心跳连接出现了网络故障，那么Eureka Server会把这些实例保护起来，让这些实例不会过期导致实例剔除。这样做就是为了防止EurekaClient可以正常运行, 但是与EurekaServer网络不通情况下， EurekaSerJer不会立刻将EurekaClient服务剔除

这样做的目的是为了减少网络不稳定或者网络分区的情况下，Eureka Server将健康服务剔除下线的问题。 使用自我保护机制可以使得Eureka 集群更加健壮和稳定的运行。进入自我保护状态后，会出现以下几种情况：Eureka Server不再从注册列表中移除因为长时间没有收到心跳而应该剔除的过期服务；Eureka Server仍然能够接受新服务的注册和查询请求，但是不会被同步到其他节点上，保证当前节点依然可用。

*自我保护模式*：
默认情况下，如果EurekaServer在一定时间内没有接收到某个微服务实例的心跳，EurekaServer将会注销该实例 (默认90秒)。但是当网络分区故障发生(延时、卡顿、拥挤)时,微服务与EurekaServer之间无法正常通信，以上行为可能变得非常危险了——因为微服务本身其实是健康的,此时本不应该注销这个微服务。Eureka通过" 自我保护模式”来解决这个问题——当EurekaServer节点在短时间内丢失过多客户端时(可能发生了网络分区故障)，那么这个节点就会进入自我保护模式。

一旦进入该模式，Eureka Server就会保护服务注册表中的信息，不再删除服务注册表中的数据（也就是不会注销任何微服务）。当网络故障恢复后，该Eureka Server节点会自动退出自我保护模式。综上，自我保护模式是一种应对网络异常的安全保护措施。它的架构哲学是宁可同时保留所有微服务（健康的微服务和不健康的微服务都会保留），也不盲目注销任何健康的微服务。使用自我保护模式，可以让Eureka集群更加的健壮、稳定。在Spring Cloud中，可以使用eureka.server.enable-self-preservation = false 禁用自我保护模式。

开启自我保护机制：通过配置将判定时间改为10s，接着启动Eureka Server，等待10s之后，就会出现以上提示信息，表示自我保护被激活了。

*直接启动consul client 出现问题*: 
原因， consul server 检测所有客户端心跳，但是发送心跳时client必须给予响应才能该服务才能正常使用，在现有客户端中我们并没有引入健康检查依赖，所以导致健康检查始终不通过，导致服务不能使用
