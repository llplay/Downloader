package party.searene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import party.searene.client.XRClient;

@SpringBootApplication
public class DownloaderApplication {

	public static void main(String[] args) {
        ConfigurableApplicationContext cxt = SpringApplication.run(DownloaderApplication.class, args);
        XRClient xrClient = (XRClient) cxt.getBean("XRClient");
        Object[] methods = xrClient.listMethods();
        for(Object method: methods) {
            System.out.println(method);
        }
    }
}
