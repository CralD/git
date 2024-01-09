package com.epam;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SystemOutTestUtils {
    private static final PrintStream originalSystemOut = System.out;
    private static final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public static void enableOutputCapture() {
        System.setOut(new PrintStream(outputStream));
    }

    public static String getSystemOut() {
        return outputStream.toString().trim();
    }

    public static void resetSystemOut() {
        System.setOut(originalSystemOut);
        outputStream.reset();
    }
}
