VideoHost client

Project is part of platform for upload own videos and watching videos from other users.

Technology used: 
1.Java 8
2.Spring Boot 
3.Spring Security
4.Spring Data Jpa
5.Spring Web Mvc
6.Mysql
7.Lombok
8.Jstl
9.Jsp

Instructions before start:

For user. How to start? The description will be implemented later after deployment.

For developer

clone client application
git clone https://github.com/svistylin/videoserver_client.git

clone server application
https://github.com/svistylin/videohost_server.git

Open both projects IntelliJ IDEA or other development enviroment

Download mysql server version 6+ or run docker container if you already instailed docker on your pc, 
there are instruction of running mysql in docker container
https://hub.docker.com/r/mysql/mysql-server/

Required by default project configuration db username: root, password: 12345

create databases  "video_server" and "videohost_client"

Run VideohostServerApplication.class and then  VideoserverClientApplication.class

in your browser input url localhost:8080

default user account 
login:admin 
password:admin
