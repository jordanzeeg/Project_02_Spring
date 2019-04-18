package com.java.util;

import org.apache.log4j.Logger;

public class LoggerSingleton {
    private static Logger log;
    private LoggerSingleton() {

    }

    public static Logger getLogger() {
        if (log != null) {
            return log;
        } else {
            log = Logger.getRootLogger();
            return log;
        }
    }
}
