package by.tyzcorporation.library.service.logger;

import java.util.logging.*;

public class MyLogger {
    private static final Logger logger = Logger.getLogger(MyLogger.class.getName());

    public static Logger getMyLogger() {
        return logger;
    }

    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    public static void logWarning(String message) {
        logger.log(Level.WARNING, message);
    }

    public static void logError(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }
}

