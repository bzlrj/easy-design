<p align="center">
    <img src="https://img.shields.io/badge/easy_design-1.0.0-blue.svg" />
    <img src="https://img.shields.io/badge/spring_boot-2.3.1-blue.svg" />
    <img src="https://img.shields.io/github/license/Yip01/easy-design" />
</p>

## 介绍

- 只做增强不做改变，引入它不会对现有工程产生影响，如丝般顺滑。
- 只需简单配置，即可快速进行开发，从而节省大量时间。

### 模块说明

```
easy-design
    ├── easy-design-common -- 系统公共模块
    ├── easy-design-payment -- 支付接口模块
    ├── easy-design-payment-alibaba -- 支付宝支付模块
    ├── easy-design-payment-wechat -- 微信支付模块
    ├── easy-design-website -- Web模块
```

## 快速开始

### 引入项目依赖

在项目的 ``pom.xml`` 加入以下代码,并将 ``${version}`` 替换为对应的版本号

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>com.y1ph</groupId>
            <artifactId>easy-design</artifactId>
            <version>${version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

随后引入所需的模块,例如：``easy-design-website``
```xml
<dependencies>
    <dependency>
        <groupId>com.y1ph</groupId>
        <artifactId>easy-design-website</artifactId>
    </dependency>
    ...
</dependencies>
```

### 启动程序

通过在 ``main`` 方法中执行 ``SpringApplication.run();`` 启动程序

```java
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
    
}
```

编写一个测试接口

```java
@RestController
public class TestController{
    
    @GetMapper("/test")
    public String test(){
        return "success";
    }
    
}

```

访问接口： ``http://127.0.0.1:8080/test``

```json
{
    "code" : 200,
    "data" : "success" 
}
```

通过上诉代码，不难发现接口返回值明明是String，但调用接口时却得到了一个对象。  
这是因为我们在 ``easy-design-website`` 模块中做了手脚，无论接口的返回值是什么，都将返回一个 ``ResultBean`` 对象。  
这样做的目的，就是为了防止在项目中出现多个类似于 ``ResultBean`` 的类出现。 



## 开源共建

### 开源协议

easy-design 开源软件遵循 [Apache 2.0 协议](https://www.apache.org/licenses/LICENSE-2.0.html)。
允许商业使用，但务必保留类作者、Copyright 信息。


### 其他说明

1. 欢迎提交 [issue](https://gitee.com/y1ph/easy-design/issues)，请写清楚遇到问题的原因、开发环境、复显步骤。

2. 联系作者 <a href="mailto:2055305009@qq.com">2055305009@qq.com</a>

