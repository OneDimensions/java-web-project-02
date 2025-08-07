### 配置优先级
命令行参数 > 环境变量 > application.properties > application.yml > application.yaml

### Bean
默认bean是singleton单例的, 在项目启动时创建, 创建完毕后会将bean存入ioc容器中

加上`@lazy`注解可以延迟初始化, 用到时才创建bean

加上`@Scope("prototype")`可以创建多个bean, 多例模式

如果是项目中自定的类, 使用@component注解自动创建bean  
如果是第三方依赖的类, 需要使用@Bean注解声明一个方法来创建bean

### 起步依赖

- spring-boot-starter-web
  - mybatis-spring-boot-starter
当项目中引入了起步依赖后, 会自动引入依赖中的依赖, 形成依赖传递


### 自动配置
1. 方案一: 第三方包类上加@component注解, 在springboot启动类上使用@componentScan({"包名1","包名2"})注解去扫描对应的包

2. 方案二: 在@configuration配置类中声明@Bean注解来创建bean, 然后在启动类上使用@import注解引入配置类

3. 方案三: 实现ImportSelector接口, 实现selectImports方法, 返回需要引入的配置类, 然后在启动类上使用@import注解引入这个实现类

4. 方案四: 第三方包将需要自动装配的bean使用import封装在一个注解中, 项目中在启动类上直接加上这个注解