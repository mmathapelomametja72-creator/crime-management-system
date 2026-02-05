/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crimemanagement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author mmath
 */
public class DBConnection {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // Static block to load configuration on class initialization
    static {
        loadConfig();
        loadDriver();
    }

    /**
     * Explicitly loads the MySQL JDBC driver
     */
    private static void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL JDBC driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: MySQL JDBC driver not found!");
            System.err.println("Please ensure mysql-connector-j-9.4.0.jar is in the lib/ folder");
            System.err.println("Download from: https://dev.mysql.com/downloads/connector/j/");
        }
    }

    /**
     * Loads database configuration from config.properties file
     * Tries multiple locations to find the config file
     */
    private static void loadConfig() {
        Properties props = new Properties();
        InputStream input = null;
        boolean configLoaded = false;

        // Try multiple locations for the config file
        String[] possiblePaths = {
            "config.properties",                    // Current working directory
            "../config.properties",                 // Parent directory (for some IDE setups)
            "../../config.properties"               // Two levels up (for deeper IDE configurations)
        };

        // Try loading from filesystem
        for (String path : possiblePaths) {
            File file = new File(path);
            if (file.exists()) {
                try {
                    input = new FileInputStream(file);
                    props.load(input);
                    URL = props.getProperty("db.url");
                    USER = props.getProperty("db.user");
                    PASSWORD = props.getProperty("db.password");
                    System.out.println("Configuration loaded from: " + file.getAbsolutePath());
                    configLoaded = true;
                    break;
                } catch (IOException e) {
                    // Continue to next path
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            // Ignore
                        }
                    }
                }
            }
        }

        // If not found in filesystem, try classpath
        if (!configLoaded) {
            try {
                input = DBConnection.class.getClassLoader().getResourceAsStream("config.properties");
                if (input != null) {
                    props.load(input);
                    URL = props.getProperty("db.url");
                    USER = props.getProperty("db.user");
                    PASSWORD = props.getProperty("db.password");
                    System.out.println("Configuration loaded from classpath");
                    configLoaded = true;
                }
            } catch (IOException e) {
                // Continue
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        // Ignore
                    }
                }
            }
        }

        if (!configLoaded) {
            System.err.println("ERROR: Could not find config.properties!");
            System.err.println("Searched locations:");
            System.err.println("  - Current directory: " + new File(".").getAbsolutePath());
            System.err.println("  - Parent directories");
            System.err.println("  - Classpath");
            System.err.println("\nPlease ensure config.properties exists in the project root directory.");
            System.err.println("Copy config.properties.example to config.properties and update with your credentials.");
        }
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to crime_management database successfully!");
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

}





   

