# jolt-playground

This a dev utility suite including a web based rest client, multiple json utilities and jolt support.

Available on Dockerhub  docker pull sohamsg/devutilitysuite:jul20docker pull sohamsg/devutilitysuite:jul20 

## By default it uses H2 database but supports MySQL and postgress, as well

<code>
E.g. docker run -e DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/self -e DATASOURCE_USERNAME=soham -e DATASOURCE_DIALECT=org.hibernate.dialect.MySQL8Dialect -e spring.jpa.hibernate.ddl-auto=none -e DATASOURCE_DRIVER-CLASS-NAME=com.mysql.cj.jdbc.Driver -p 8080:8080 devutilitysuite:jul20
</code>



