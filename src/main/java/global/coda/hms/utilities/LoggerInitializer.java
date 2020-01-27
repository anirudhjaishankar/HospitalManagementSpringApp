package global.coda.hms.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerInitializer {
    public static Logger initiateLogger(String className){
        return LogManager.getLogger(className);
    }
}
