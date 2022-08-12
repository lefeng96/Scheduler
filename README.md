# restapi

When you clone or pull, open eclipse and open existing folder. Browse the folder to the restapi folder, click on Jersey folder and open RESTfulProject folder.

Make any changes to your existing schema and password in MySQL command prompt or workbench.

If you have to change to password for the MYSQL use this script in the command prompt.

SET PASSWORD FOR 'root'@'localhost' = PASSWORD('MyNewPass');

Set with the password in the database file in the eclipse. If the schema needs to be change just create a new database.

Create database school_db;
Use school_db;

And use the school.sql file to import the tables. Use the old one for classes. We will update the new student table later.

The repository will come with the mysql connector in the jar files. You will need apache tomcat server. Follow the steps from here.

http://www.coreservlets.com/Apache-Tomcat-Tutorial/tomcat-7-with-eclipse.html

download any tomcat server from 7 or above.
http://tomcat.apache.org/

Follow the "unzip tomcat" and "Tell Eclipse about Tomcat" steps. Right click on the project and go to run as.
Select Server and select the server that you created. Then go to http://localhost:8080/RESTfulProject/REST/WebService/Classes 
you should see the json.
