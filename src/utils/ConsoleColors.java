package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConsoleColors {
    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("variables.env")) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error al cargar los colores desde variables.env: " + e.getMessage());
        }
    }

    public static final String RESET = properties.getProperty("COLOR_RESET", "\u001B[0m");
    public static final String SUCCESS = properties.getProperty("COLOR_SUCCESS", "\u001B[32m");
    public static final String WARNING = properties.getProperty("COLOR_WARNING", "\u001B[33m");
    public static final String ERROR = properties.getProperty("COLOR_ERROR", "\u001B[31m");
    public static final String INFO = properties.getProperty("COLOR_INFO", "\u001B[36m");
}