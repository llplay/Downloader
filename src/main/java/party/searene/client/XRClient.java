package party.searene.client;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import party.searene.exception.XmlRpcClientInitializationException;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

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

    private Object execute(String method, Vector params) {
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

    /**
     * upload bt file
     * @param file
     * @return
     */
    public Object AddByFile(File file){
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[(int) file.length()];
            int read = 0;
            while ((read = in.read(buffer, 0, buffer.length)) > 0) {
                baos.write(buffer, 0, read);
            }
            byte[] bytes = baos.toByteArray();
            client.execute("load_raw_start", new Object[] { bytes });
        } catch (Exception e) {
            logger.error(String.format("Exception occurred, message: %s, stacktrace: %s",
                    e.getMessage(), ExceptionUtils.getStackTrace(e)));
        }
        return null;
    }

    /**
     * 暂停任务
     * @param uniqueId
     * @return
     */
    public Object Pause(String uniqueId){
        Vector<String> params= new Vector<String>();
        params.addElement(uniqueId);

        return execute("d.pause", params);
    }

    /**
     * 恢复任务
     * @param uniqueId
     * @return
     */
    public Object Resum(String uniqueId){
        Vector<String> params= new Vector<String>();
        params.addElement(uniqueId);
        return execute("d.resume", params);
    }

    /**
     * 获取任务列表
     * @return
     */
    public Object[] getTaskList() {
        Vector<String> params = new Vector<>();
//        params.addElement("main");
//        params.addElement("d.name=");
//        params.addElement("d.state=");
//        params.addElement("d.get_down_rate=");
        return (Object[])execute("download_list", params);
    }

    /**
     * 获取特定ID的task name
     * @param uniqueId task Id
     * @return task name
     */
    public Object getTaskName(String uniqueId){
        Vector<String> params = new Vector<>();
        params.addElement(uniqueId);
        return (Object)execute("d.name", params);
    }

    /**
     * 获取特定ID的task state
     * @param uniqueId task Id
     * @return task state
     */
    public Object getTaskState(String uniqueId) {
        Vector<String> params = new Vector<>();
        params.addElement(uniqueId);
        return (Object)execute("d.get_state", params);
    }


    /**
     * 获取active task
     * @return
     */
    public Object[] getActiveTasks(){

        return null;
    }


}
