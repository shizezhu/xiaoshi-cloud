## 🐱‍ 介绍
最近辞掉了深圳的工作，计划去武汉发展，毕竟深圳留不下来，那就趁现在还年轻，规划一下未来的人生道路。
利用这段空档期，我计划搭建一套完整的全栈微服务架构，用来保持编码水平的熟练度，也系统性地学习一下前端和运维方面知识。

## 🐶 工程结构

* 公共服务放在最外层，端口号以10xx开头
* xiaoshi-admin-api: 管理后台RPC接口，暴露给其它模块调用
* xiaoshi-admin-server: 管理后台接口服务，端口号以11xx开头
* xiaoshi-client-api: 客户端服务RPC接口，暴露给其它模块调用
* xiaoshi-client-server: 客户端接口服务，端口号以12xx开头
* xiaoshi-framework: 系统框架工程包

为什么要将管理后台的服务与客户端服务拆分？
* 客户端服务需要保证很高的响应速度和稳定性，管理后台涉及批量数据操作、报表生成等消耗资源的操作，容易影响整体系统性能。
* 拆分管理后台和客户端服务后，团队可以独立开发和部署各自的模块，简化协作流程，提升开发效率。
* 拆分服务后，可以根据实际流量需求分别扩展管理后台和客户端服务资源，提升架构的灵活性。
* 拆分管理后台和客户端服务还能避免接口混用的问题。在同一个服务中，客户端开发人员可能误用管理后台接口，尤其在使用 Swagger 这类接口文档工具时。管理后台接口通常不适合高并发，如果被客户端误用，可能会引发性能瓶颈和响应延迟。