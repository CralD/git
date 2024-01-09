package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuffixingApp {

    private static final Logger LOGGER = Logger.getLogger(SuffixingApp.class.getName());

    public static void main(String[] args) {
        if (args.length != 1) {
            LOGGER.log(Level.SEVERE, "Usage: java -jar suffixing.jar <config-file>");
            return;
        }

        String configFile = args[0];
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream(configFile)) {
            properties.load(input);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error loading config file: " + e.getMessage());
            return;
        }

        String mode = properties.getProperty("mode");
        String suffix = properties.getProperty("suffix");
        String files = properties.getProperty("files");

        // Validate and process configuration
        validateAndProcessConfig(mode, suffix, files);
    }

    private static void validateAndProcessConfig(String mode, String suffix, String files) {
        // Validate mode
        if (!isValidMode(mode)) {
            LOGGER.log(Level.SEVERE, "Mode is not recognized: " + mode);
            return;
        }

        // Validate suffix
        if (suffix == null || suffix.isEmpty()) {
            LOGGER.log(Level.SEVERE, "No suffix is configured");
            return;
        }

        // Validate files
        if (files == null || files.isEmpty()) {
            LOGGER.log(Level.WARNING, "No files are configured to be copied/moved");
            return;
        }

        // Process files
        String[] fileList = files.split(":");
        for (String filePath : fileList) {
            processFile(filePath, suffix, mode);
        }
    }

    private static boolean isValidMode(String mode) {
        return mode != null && (mode.equalsIgnoreCase("copy") || mode.equalsIgnoreCase("move"));
    }

    private static void processFile(String filePath, String suffix, String mode) {
        File sourceFile = new File(filePath);

        // Check if the file exists
        if (!sourceFile.exists()) {
            LOGGER.log(Level.SEVERE, "No such file: " + filePath.replace("\\", "/"));
            return;
        }

        String destinationFilePath = addSuffixToFileName(filePath, suffix);
        File destinationFile = new File(destinationFilePath);


        try {
            // Process the file based on the mode
            switch (mode.toLowerCase()) {
                case "copy":
                    Files.copy(sourceFile.toPath(), destinationFile.toPath());
                    LOGGER.log(Level.INFO, filePath + " -> " + destinationFilePath.replace("\\", "/"));
                    break;
                case "move":
                    Files.move(sourceFile.toPath(), destinationFile.toPath());
                    LOGGER.log(Level.INFO, filePath + " => " + destinationFilePath.replace("\\", "/"));
                    break;
                default:
                    LOGGER.log(Level.SEVERE, "Mode is not recognized: " + mode);
                    break;
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error processing file: " + e.getMessage());
        }
    }


    private static String addSuffixToFileName(String filePath, String suffix) {
        int lastDot = filePath.lastIndexOf('.');
        return filePath.substring(0, lastDot)  + suffix + filePath.substring(lastDot);
    }
}