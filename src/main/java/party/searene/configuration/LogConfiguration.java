package party.searene.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import party.searene.beanProcessor.LogInjector;

/**
 * Created by searene on 11/28/16.
 */
@Configuration
public class LogConfiguration {
    @Bean
    public LogInjector logInjector() {
        return new LogInjector();
    }
}
