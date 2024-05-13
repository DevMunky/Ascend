package dev.munky.ascend.util.sql;

import dev.munky.ascend.util.Logging;

import java.sql.*;
public class MySQL {

    public static String host = "localhost";
    public static String port = "3306";
    public static String database = "GlobalData";
    public static String username = "******";
    public static String password = "*****";
    private static Connection connection;

    // connect
    public static void connect() {
        if (!isConnected()) {
            try {
                connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                Logging.info("MYSQL connection started!");
            } catch (SQLException e) {
                Logging.severe("SQL ERROR");
                e.printStackTrace();
            }
        }
    }

    // disconnect
    public static void disconnect() {
        if (isConnected()) {
            try {
                connection.close();
                Logging.info("MYSQL connection closed.");
            } catch (SQLException e) {
                Logging.severe("SQL ERROR");
                e.printStackTrace();
            }
        }
    }

    // isConnected
    public static boolean isConnected() {
        return (connection != null);
    }

    // getConnection
    public static Connection getConnection() {
        return connection;
    }
}
