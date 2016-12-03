package party.searene;

import org.apache.xmlrpc.XmlRpcException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import party.searene.client.XRClient;

@SpringBootApplication
public class DownloaderApplication {

    public static void main(String[] args) throws XmlRpcException {
        ConfigurableApplicationContext cxt = SpringApplication.run(DownloaderApplication.class, args);
        XRClient xrClient = (XRClient) cxt.getBean("XRClient");
        Object[] list = xrClient.getTaskList();
        for (Object task : list) {
            System.out.print(task + ":");
            xrClient.Resum((String) task);
//            System.out.print(xrClient.getTaskName((String) task) + "\t");
//            System.out.println(xrClient.getTaskState((String) task));
        }

//        SpringApplication.run(DownloaderApplication.class);
    }
}