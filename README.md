# RoQui Client SRI

Application for send XML files to SRI Ecuador

## Software
* Java 21 (Open JDK)
* Gradle 8.14.3

## SRI Web Service
### Developer URL
* Reception: https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl
* Authorization: https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl
### Production URL
* Reception: https://cel.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl
* Authorization: https://cel.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl

### Tools
* https://www.soapui.org/
* https://cxf.apache.org/

### Command to generate source code for web service client
```
wsdl2java -p autorizacion.ws.sri.gob.ec -d ./webclient -client -impl -ant -exsh false -dns true -dex true -verbose https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl
```

### FIX in autorizacion.ws.sri.gob.ec.RespuestaComprobante class
* In RespuestaComprobante class added @XmlRootElement annotation for return authorizations
* In RespuestaSolicitud class added @XmlRootElement annotation for return response
* Remove or comment @XmlElement(namespace ...)  from all files

## Upgrade Gradle
```
gradle wrapper --gradle-version 8.14.3
```

## Build
```
gradle build
```

## Publish in local maven repository
### GNU/Linux or MacOS
```
mvn install:install-file -Dfile=./app/build/libs/RoQuiClientSri-1.2.0.jar -DgroupId=dev.joguenco.client -DartifactId=RoQuiClientSri -Dversion=1.2.0 -Dpackaging=jar
```
### Windows
In CMD terminal, not in PowerShell
```
mvn install:install-file -Dfile=.\app\build\libs\RoQuiClientSri-1.2.0.jar -DgroupId=dev.joguenco.client -DartifactId=RoQuiClientSri -Dversion=1.2.0 -Dpackaging=jar
```

## Use
Execute and result file is in temp operating system folder



