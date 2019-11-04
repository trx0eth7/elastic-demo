# Configure Project

### Create Spring Boot project 
* here you go to create [spring boot app](https://start.spring.io)
* use familiar javax inject api, add spec dependency: 
    `implementation group: 'javax.inject', name: 'javax.inject', version: '1'`
* run app: `gradlew ass bootRun`    
* [spring properties](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#common-application-properties)
* gradle spring config location https://stackoverflow.com/questions/31038250/setting-active-profile-and-config-location-from-command-line-in-spring-boot  
* configure logback for [slf4j](https://leodev.ru/blog/spring-boot/spring-boot-slf4j/)

### Lombok
* install plugin [lombok](https://plugins.jetbrains.com/plugin/6317-lombok)
* need to enable Annotation Processor 
 `Settings -> Build -> Compiler` 
* additional read: https://habr.com/ru/post/438870/ 

