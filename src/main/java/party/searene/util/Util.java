package party.searene.util;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import party.searene.annotation.Log;

/**
 * Created by searene on 11/29/16.
 */
@Component
public class Util {

    @Log
    private static Logger logger;

    public static void logException(Exception e) {
        logger.error(String.format("Exception occurred, message: %s", e.getMessage()));
    }
}
