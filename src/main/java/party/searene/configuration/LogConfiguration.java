package party.searene.configuration;

import party.searene.beanProcessor.LogInjector;

/**
 * Created by searene on 11/28/16.
 */
public class LogConfiguration {
    public LogInjector logInjector() {
        return new LogInjector();
    }
}
