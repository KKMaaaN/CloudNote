# **CloudNote  云账**

***\*此项目为前后端分离架构：\****

前端将使用Vue.JS+ElementUI完成前端交互功能与UI设计

后端将使用Restful风格的Spring Cloud Netflix方案微服务分布式架构

***\*项目GitHub地址\****：https://github.com/KKMaaaN/CloudNote

简单效果演示：https://v.youku.com/v_show/id_XNTEyODgyNjc0OA==.html

初版后端系统架构拓扑图：

![](http://m.qpic.cn/psc?/V10Jvq4A4JXCnv/45NBuzDIW489QBoVep5mcZJuI6XUguZb1x4WJTZVz930VJTVkvTLV0qPaIr5qeYRs6D2BDwHxG053aRKp47DzqSIJ0vZ4ChKRNueiENOMtM!/b&bo=bQN3BAAAAAABFy0!&rf=viewer_4)



为了低耦合的且高可用，高并发的的应用需求，此项目采用微服务集群的分布式形式分为多个模块：

\- ***\*CloudNote-Eureka\****：注册中心模块，负责注册各个微服务集群中的单个服务主机，保证服务的高可用性，抽象服务主机的IP为服务名，使其支持负载均衡；基于Netflix的Eureka技术完成。

\- ***\*CloudNote-Zuul\****：网关模块，负责外部访问微服务的请求集中统一处理网关，避免主机被用户直接访问，提高系统的安全性，统一其他微服务的监测地址，可以由维护人员通过网关直接监控已提供监控支持的微服务；基于Netflix的Zuul技术完成。

\- ***\*CloudNote-BlogService\****：Blog服务模块，负责博客相关服务支持，与UserService服务模块，CrowdSerivce服务模块有Id关联。

\- ***\*CloudNote-UserSerivce\****：User服务模块，负责用户相关服务支持，为系统的核心模块。

\- ***\*CloudNote-CommentService\****：Comment服务模块，负责评论相关服务支持，与BlogSerivce服务模块，UserService服务模块有Id关联。

\- ***\*CloudNote-CrowdService\****：Crowd服务模块，负责板块相关服务支持，与UserSerivce服务模块，有Id关联。

\- ***\*CloudNote-Web\****：服务Web支持模块，负责各个服务模块间的基础支持配置，提高服务模块代码的复用性，减少开发人员的开发开支，提高管理人员对服务支持的管理。

\- ***\*CloudNote-Api\****：服务公共标准模块，负责各个服务模块间的开发规范以及基础服务接口的暴露，使抽象服务的具体实现，提高项目的可读性，并提供DTO传输层数据包装类为其他服务模块提供数据的传输规范。

\- ***\*CloudNote-Sleuth\****：服务响应监测模块，采用Netflix的Sleuth技术和Twitter的Zipkin技术，使用响应链路追踪的方式进行服务间的调用关系和处理时间，维护人员和开发人员可以通过此模块进行响应的监控，为服务的优化以及维护提供帮助。

\- ***\*CloudNote-Hystrix-Dashboard\****：服务负载监测模块，监测服务被用户群调用的频率以及服务器的负载情况，方便维护人员对资源的分配；基于Netflix的Hystrix Dashboard技术完成。

\- ***\*CloudNote-Hystrix-Turbine\****：服务集群监控集束模块，负责将各个模块对Hystrix Dashboard支持的Hystrix.stream监控数据流集束，统一为Dashboard模块使用，以此监测所有支持Dashboard服务的服务负载状况。

-***\*CloudNoe-Front-End*\***：前端用户操作模块，负责图形化业务功能，并与后端对应接口进行通信，基于Vue.js完成视图层，基于Node.js完成服务层，基于Element UI完成UI设计。

***\*具体业务服务模块所使用中间件技术：\****

\- Druid数据池技术：提高服务对数据的持久化性能，同时减少服务对持久化功能的开销，为服务的核心技术

\- Hystrix服务熔断技术：通过服务回退的方式防止服务互相调用中的错误而引发雪崩效应，而引起一系列微服务不可用的情况，增强服务间的可用性

\- MyBatis-plus持久化技术：简化持久化底层的开发，减少开发人员的开发开支，并且提供有强大的数据处理器，并且提供一系列持久化层面标签，使PO类与数据表的关联性更强并且可读性更高

-Feign远程调用（RPC）技术：NetFlix对RPC提供的解决方案，对Spring Cloud Netflix方案的适配性相对于Dubbo更高，并且使远程调用接口更加本地化。

-Ribbon 负载均衡技术：抽象各个服务主机的具体ip，通过Eureka注册中心的注册名为调用目标，提高服务集群的高可用性和效率，提供有丰富的负载均衡策略应对各种应用场景

-Actuator 服务主机信息发现技术：提供服务主机的详细状况,使Eureka注册中心能够得到此主机的运行状态，为其他监测模块提供检测支持。

-Swagger2接口详细信息生成文档：通过标签编程的形式进行服务接口的说明，自动生产说明文档页面，图形化接口的数据提供标准，提高前端开发工作效率。

-Redis 缓存持久化技术：对服务可能产生的数据进行缓存，高速的查询效率可以提高类似于登录信息缓存的搜索速度，提高系统的响应速度。

-RabbitMQ 消息队列技术：异步通信，提高系统响应性能，对多请求场景的性能削峰，降低系统服务模块间的耦合，为系统服务模块提供虽稳定且高效的通信方式。