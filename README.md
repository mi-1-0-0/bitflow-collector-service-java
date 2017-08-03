This is initial version of java bitflow collector service. 
It is to be published in Apache Tomcat Server.
Configurations (Tomcat):
- Export .war file fromt the project.
- Change server port to 8000 in server.xml file.
- To deploy in the root, change war file to ROOT, undeploy the content published on root ("/"), and then deploy the war file. (It's the shortest way to do so).

Before using the service, please change the permission of the file /opt/bitflow/data-collector/data.csv by using the following command:
- chmod 644 /opt/bitflow/data-collector/data.csv

After applying these configurations, you will be able to use the service by using the URL, like: http://ip:8000/size
