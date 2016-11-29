package party.searene.configuration;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by searene on 11/28/16.
 */
@Configuration
public class XmlRpcConfiguration {

    private Logger logger = LogManager.getLogger(this.getClass());

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
            logger.error("Exception occurred, message: %s, stacktrace: %s",
                    e.getMessage(), ExceptionUtils.getStackTrace(e));
            return null;
        }
    }
}
