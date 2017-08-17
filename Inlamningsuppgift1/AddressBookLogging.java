package com.hotmail.a_asultan.Inlamningsuppgift1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class AddressBookLogging {

    public void setupLogging() {
        String loggingFilePath = "C:/OOPnew/InlamningsUppgift/src/logging.properties";
        try (FileInputStream fileInputStream = new FileInputStream(loggingFilePath)) {
            LogManager.getLogManager().readConfiguration(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not load log properties.", e);
        }
    }
}
