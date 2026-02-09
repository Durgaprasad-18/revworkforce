package com.revature.revworkforce.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

public class LoggerUtil {

    private static Logger logger;

    static {

        try {

            //  Create logs folder if not exists
            File logDir = new File("logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            logger = Logger.getLogger("RevWorkForceLogger");

            //  Prevent duplicate handlers
            if (logger.getHandlers().length == 0) {

                FileHandler fileHandler =
                        new FileHandler("logs/revworkforce.log", true);

                fileHandler.setFormatter(new SimpleFormatter());

                logger.addHandler(fileHandler);
                logger.setUseParentHandlers(false);
                logger.setLevel(Level.INFO);
            }

        } catch (IOException e) {

            System.out.println("Logger initialization failed: "
                    + e.getMessage());
        }
    }

    private LoggerUtil() {}

    public static Logger getLogger() {
        return logger;
    }
}
