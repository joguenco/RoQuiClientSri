package dev.joguenco;

import dev.joguenco.client.sri.Check;
import dev.joguenco.client.sri.Send;

import java.io.File;
import java.io.IOException;

enum webservice {
    DEV_RECEPTION("https://celcer.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl"),
    DEV_AUTHORIZATION("https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl"),
    PROD_RECEPTION("https://cel.sri.gob.ec/comprobantes-electronicos-ws/RecepcionComprobantesOffline?wsdl"),
    PROD_AUTHORIZATION("https://cel.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl");

    private final String url;

    webservice(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}

public class Main {
    public String getGreeting() {
        return "RoQui Client SRI";
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().getGreeting());

        ClassLoader classLoader = Main.class.getClassLoader();

        // Example XML file located in src/main/resources
        var xml = "0301202301123456789000110010030000000071234567811.xml";
        var pathXmlFile = classLoader.getResource(xml).getPath();
        var fileXml = new File(pathXmlFile);

        final var statusSend = Send.execute(webservice.DEV_RECEPTION.getUrl(), fileXml.getPath());
        System.out.println(statusSend.getEstado());

        String fileNameWithoutExt = fileXml.getName().substring(0, fileXml.getName().lastIndexOf('.'));

        final var statusCheck = Check.execute(webservice.DEV_AUTHORIZATION.getUrl(),fileNameWithoutExt);
        System.out.println(statusCheck.getEstadoAutorizacion().getDescripcion());
    }
}
