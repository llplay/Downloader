package party.searene.client;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import party.searene.annotation.Log;
import party.searene.exception.XmlRpcClientInitializationException;

import java.util.List;

/**
 * Created by searene on 11/28/16.
 */
@Repository
public class XRClient {

    private final XmlRpcClient server;

    @Log
    private Logger logger;

    @Autowired
    public XRClient(XmlRpcClient server) throws XmlRpcClientInitializationException {
        if(server == null) {
            throw new XmlRpcClientInitializationException(
                    "Something was wrong while initializing XmlRpcClient."
            );
        }
        this.server = server;
    }

    private Object execute(String method, List params) {
        try {
            return server.execute(method, params);
        } catch (XmlRpcException e) {
            logger.error("Exception occurred, message: %s, stacktrace: %s",
                    e.getMessage(), ExceptionUtils.getStackTrace(e));
            return null;
        }
    }

    public Object listMethod() {
        return execute("system.listMethods", null);
    }
}
