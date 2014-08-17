springMVCCrud
=============

This is a Spring MVC Hibernate Crud PostgreSQL Tomcat project 

This is a Web Application developped on Eclipse Juno with Java 1.7 Hibernate 3.2.1 Postgresql 9.3 and Tomcat 1.7

I've included all the necessary Spring and Hibernate file to get you going fast.

The setting for Hibernate Postgresql is defined in springMVCCrud-servlet.xml

<prop key="hibernate.hbm2ddl.auto">create</prop>  // To automatically recreate the tables in the db

or 

<prop key="hibernate.hbm2ddl.auto">update</prop>  // To only update the tables in the db

jdbc.properties contains the credential to access the db

Have fun
