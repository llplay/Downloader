package party.searene.configuration;

import org.apache.logging.log4j.Logger;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import party.searene.annotation.Log;
import party.searene.util.Util;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by searene on 11/28/16.
 */
@Configuration
public class XmlRpcConfiguration {

    @Log
    private Logger logger;

    @Value("${XmlRpc.url}")
    private String xmlRpcUrl;

    /* Create a new XmlRpcClient bean */
    @Bean
    public XmlRpcClient xmlRpcClient() {
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
            config.setServerURL(new URL(xmlRpcUrl));
            XmlRpcClient xmlRpcClient = new XmlRpcClient();
            xmlRpcClient.setConfig(config);
            return xmlRpcClient;
        } catch (MalformedURLException e) {
            Util.logException(e);
            return null;
        }
    }
}
