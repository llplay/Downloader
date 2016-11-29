package party.searene.client;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import party.searene.exception.XmlRpcClientInitializationException;

import java.util.List;

/**
 * Created by searene on 11/28/16.
 */
@Repository
public class XRClient {

    private XmlRpcClient client;

    private Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    public XRClient(XmlRpcClient client) throws XmlRpcClientInitializationException {
        if(client == null) {
            throw new XmlRpcClientInitializationException(
                    "Something was wrong while initializing XmlRpcClient."
            );
        }
        this.client = client;
    }

    private Object execute(String method, List params) {
        try {
            return client.execute(method, params);
        } catch (XmlRpcException e) {
            logger.error(String.format("Exception occurred, message: %s, stacktrace: %s",
                    e.getMessage(), ExceptionUtils.getStackTrace(e)));
            return null;
        }
    }

    public Object[] listMethods() {
        return (Object[]) execute("system.listMethods", null);
    }
}
