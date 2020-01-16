## Elasticsearch demo project
If you've never heard of elasticsearch than this demo shows how to use external search engine.

You'll learn more about:
- how to create simple spring boot application
- how to use lombok library
- how to install elasticsearch on linux (Ubuntu 18.04.3 LTS)
- how to configure elasticsearch
- how to use elasticsearch 

### How to create Spring Boot project 
* here you go to create [spring boot app](https://start.spring.io)
* [spring properties](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#common-application-properties)
* configure logback for [slf4j](https://leodev.ru/blog/spring-boot/spring-boot-slf4j/)
* run app: `./gradlew ass bootRun`    

### How to use Lombok extension
* install plugin [lombok](https://plugins.jetbrains.com/plugin/6317-lombok)
* need to enable Annotation Processor 
 `Settings -> Build -> Compiler` 
 
 ### How to install elasticsearch
 * [official documentation](https://www.elastic.co/guide/index.html)
 * there are some variants, but here we're going to use the following [ubuntu](https://www.elastic.co/guide/en/elasticsearch/reference/7.5/deb.html):
 * import pgp key: `wget -qO - https://artifacts.elastic.co/GPG-KEY-elasticsearch | sudo apt-key add -` 
 * installing from the APT repository: `sudo apt-get install apt-transport-https` 
 * save the repository definition to: `echo "deb https://artifacts.elastic.co/packages/7.x/apt stable main" | sudo tee -a /etc/apt/sources.list.d/elastic-7.5.1-amd64.deb.list`   
 * install Debian package: `sudo apt-get update && sudo apt-get install elasticsearch`
 * check version: `apt list elasticsearch -a`  

