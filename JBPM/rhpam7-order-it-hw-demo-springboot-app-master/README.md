IT Orders Case application with SpringBoot
========================================

SpringBoot version of IT Orders Case application that show case capabilities of jBPM case management
KJAR supported for this application can be found at https://github.com/mswiderski/IT_Orders

Supported profiles
------------------------------

This sample application support three profiles:

- local
- openshift

configuration of each profile is via application-{profile}.properties file where data source can be configured to desired values.

To be able to use the given profile it needs to be first build with enabled maven profile:

Prepare for running application
-------------------------------

Deploy kie workbench (follow installation notes for workbench including users, groups and login module) and create project from samples - IT Orders
Make sure you start workbench with bind switch so it will be accessible on real IP instead of localhost only

```
./standalone.sh -b 0.0.0.0
```


Running the application locally
------------------------------

Build application

```
mvn clean install -Plocal
```

Next you can start application with:

```
java -Dspring.profiles.active=local -jar target/order-it-hw-app-1.0.0.jar
```

This will boot the application and connect to controller (by default located at http://localhost:8080/kie-wb/rest/controller - it's
 configurable via application-local.properties).

To access application simply go to http://localhost:8090/

Running the application on OpenShift
------------------------------

Make sure you're connected to openshift in the terminal you run the build. If you're not then logon with:

```
oc login -u system:admin
```

Build application

```
mvn clean install -Popenshift (-DskipTests)
```
This will trigger actually build on openshift so it might take few seconds. Once finished you are ready to run the app

```
mvn fabric8:run -Popenshift (-DskipTests)
```

it will run the application and tail the logs of the first POD until interrupted (Ctrl+C) when it will scale down the deployment on openshift

To access application, look into openshift to find the route for this application and you can access the app directly via that link.

*Configuration for OpenShift*

Additional configuration (except the default one in application-openshift.properties) can be found in src/main/fabric8
- deployment.yml allos to configure overall setup including env variables, configmap, mount points etc
- configmap.yml configures maven to be used by KIE - it's a custom settings.xml
- data-pvc.yml defines simple persistence volume claim


*Security restriction on OpenShift*

OpenShift by default uses high number as user id that runs the process in container. This in many cases will lead to permision denied on file system (both read and write) and will not use the user defined in dockerfile. To allow to use dockerfile user run this on your OpenShift cluster:

```
oc adm policy add-scc-to-group anyuid system:authenticated
```



Login to application
------------------------------

There are several users predefined that allow to use this application:
- maciek (maciek1!) - regular user who can place orders
- tihomir (tihomir1!) - supplier that can provide hardware specification and place orders
- krisv (krisv1!) - manager that can approve/reject orders
