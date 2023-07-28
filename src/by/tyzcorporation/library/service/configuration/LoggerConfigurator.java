package by.tyzcorporation.library.service.configuration;

import java.io.IOException;
import java.util.logging.LogManager;

public class LoggerConfigurator {
    public static void configureLogger() {
        try {
            LogManager.getLogManager().readConfiguration(LoggerConfigurator.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
