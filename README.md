# 微天气服务端程序

集地理信息与天气预报为一体的天气预报类小程序，界面精美，使用便捷。【致敬：和风天气】

本仓库是微天气小程序的服务端应用程序代码仓库


## 快速体验

![微天气小程序二维码](https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/8.jpg)

## 状态

不定期更新...

## 特性

- [x] 微信小程序
- [x] 集成腾讯地图，支持基础地图漫游，地图点击以及定位
- [x] 接入和风天气数据，确保数据真实可用
- [x] 提供实时天气预报、空气质量预报
- [x] 提供24小时天气预报
- [x] 提供7天天气预报
- [x] 提供城市实时天气海报分享
- [x] 提供城市搜索功能，可自定义关注城市
- [ ] 提供极端天气提醒+消息推送
- [ ] 结合AIGC优化分享海报
- [ ] 提供Web端+自定义地图模块

## 截图

<div style="display:inline-block">
  <img src="https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/1.jpg" alt="主页1" width="330"><img src="https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/2.jpg" alt="主页2" width="330"><img src="https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/3.jpg" alt="主页3" width="330">
</div>

<div style="display:inline-block">
  <img src="https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/4.jpg" alt="城市1" width="330">
  <img src="https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/5.jpg" alt="城市2" width="330">
</div>

<div style="display:inline-block">
  <img src="https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/6.jpg" alt="海报1" width="330">
  <img src="https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/7.png" alt="海报2" width="330">
</div>

## 架构说明

微天气小程序是一个微信小程序，存在独立的服务端程序，业务数据存放于Postgresql数据库中。其中，服务端程序Micro Weather Backend使用Java语言开发，天气数据是经过服务端代理的方式形成内部的数据接口，并非是前端直接调用和风的接口。最后使用Docker技术部署到阿里云服务器，通过Nginx对外提供Rest服务，而微信小程序则依托于其自身生态进行发布。微天气小程序中城市天气海报分享的图片是在服务端生成，再通过Nginx代理的方式提供访问。基于此，形成了微天气小程序的基本架构。

## 先决条件

1、你需要先注册自己的小程序，以便获取合法的小程序id、key以及密钥

2、而后注册和风天气，并创建一个应用，注意选择免费项目

3、注册腾讯位置服务，并创建应用，用于后续使用静态图

而后你才可以

4、拉取服务端代码，并构建调试环境。使用docker可快速构建本地调试环境【记得替换各种配置】

5、拉取小程序代码（即本仓库），通过微信小程序开发者工具可以开始完整调试【记得替换appid】

## 构建

这里我们使用dev环境，直接在本地进行环境构建

jdk: 17

> 更推荐使用docker进行环境构建

1. 获取源代码

~~~
git clone https://github.com/zhou-fuyi/micro-weather-backend.git
~~~


2. 环境变量设置

如下图所示，我们需要准备好全部的环境变量数据

- WECHAT_APP_ID: 微信小程序APP ID
- WECHAT_SECRET: 微信小程序密钥
- WEATHER_SERVICE_PUBLIC_KEY: 和风天气创建应用的公钥
- WEATHER_SERVICE_SECRET_KEY: 和风天气创建应用的私钥
- POSTER_KEY: 腾讯位置服务创建应用的KEY
- POSTER_API_TEMPLATE: 静态图API模板，可以直接使用 --> 'https://apis.map.qq.com/ws/staticmap/v2?markers=size:large|color:%s|%s&bounds=%s&scale=%s&size=%s&maptype=roadmap&key=%s'
- POSTER_PROXY: 海报的代理前缀地址，即通过Nginx代理后海报的访问前缀，如 --> 'http://localhost:8080'

![服务端开发环境变量截图](https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/backend-dev-config.png)

服务的启动有两种方式：
- 通过IDE启动，命令行启动Jar不讨论

  直接在Application的启动配置中新增环境变量即可
  ![](https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/backend-dev-config-2.png)

- 通过docker compose方式启动

  复制给定的[.env.template](https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/deploy/docker-compose//.env.tempate)文件并变更对应的值即可(copy .env.template .env)

3. 代码编译&服务镜像构建

- 使用携带的gradle构建应用

    ~~~
    # windows
    .\gradlew clean build -x test --no-daemon

    # linux
    gradle clean build -x test --no-daemon
    ~~~

    ![](https://cdn.jsdelivr.net/gh/zhou-fuyi/micro-weather-docs/refs/imgs/v1/backend-dev-gradle-1.png)

    > ps: Gradle项目中的--no-daemon参数是指在执行Gradle任务时不使用后台守护进程。使用该参数可以避免Gradle守护进程在系统中长时间运行而消耗大量的内存资源
    >
    > 在使用--no-daemon参数时，每次执行Gradle任务都会启动一个新的进程，任务执行完成后进程也会自动结束。这种方式适合在开发过程中频繁执行Gradle任务的情况下，可以避免Gradle守护进程一直占用内存资源，同时也能够保证任务执行的可靠性和稳定性。
    >
    > refer from: https://juejin.cn/s/idea%20gradle%20no-daemon

- 使用docker构建镜像

    ~~~
    # 需要在docs给定的data目录下执行, 如果micro_weather_db_v100_data_with_create.bak.zip存在则需要将内部的sql文件解压到data目录下, 可参见Dockerfile
    docker build -t registry.cn-hangzhou.aliyuncs.com/fuyi-atlas/micro-weather-postgis:latest .

    # 需要在microo-weather-backend中Dockerfile所在目录下执行, 仓库名称可自行更换, 本地环境无所谓的, 且后续也是可以变更的, 只是一个tag
    docker build -t registry.cn-hangzhou.aliyuncs.com/fuyi-atlas/micro-weather-backend:latest .
    ~~~

    > docker镜像推不推送看自己咯，仅本地也是可以用的

4. 服务端调试环境构建

从上面的架构说明中可知，我们需要一个postgresql作为数据库（由于一些特定的版本原因，我们这里选择12-3.4版本），一个Nginx作为图片代理，还有一个服务端应用。

本仓库提供了docker compose脚本，包含了postgresql与服务端程序，nginx需要另外部署

- docker compose脚本使用

  将docker compose脚本与上文提供的.env文件拷贝至同一个目录下, 并在此处打开命令行, 并输入如下命令:
  ~~~
  docker compose up -d --build

  # 可以使用logs命令查看日志
  docker compose logs -fn 100
  ~~~
- 单服务端程序启动方式

  可以直接在IDE中启动

- Nginx部署

  ~~~
  # 其中, 前面的路径就是海报存放的位置
  docker run --name nginx1 -p 8080:80 -v D:\tmp\micro-weather\data\poster:/usr/share/nginx/html:ro -d nginx

  # ro --> read only [It means read only volume. So the container will not be able to modify its contents.]
  # refer: https://hub.docker.com/_/nginx
  ~~~

## 支持

- thread.zhou@gmail.com
- thread_zhou@126.com


## 声明与致谢

- 目前全部的天气数据均由和风天气免费提供
- 行政区域数据最终使用[锐多宝](https://www.shengshixian.com/)提供的省市县数据，我仅在此基础上做了些许处理

感谢和风天气、微信、锐多宝以及所有为本项目提供支持的主体。

## 版权许可

 [GPL-3.0 license](./LICENSE)

 版权所有 (c) 2022-至今，Fuyi Atlas。