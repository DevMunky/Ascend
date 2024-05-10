package dev.munky.ascend.util.sqlite;

import dev.munky.ascend.util.Logging;

import java.sql.*;

/**
 * Another singleton, this time for data storage (mostly domains)
 */
public class MySQL {

    public static String host = "minepedia.eu";
    public static String port = "3306";
    public static String database = "GlobalData";
    public static String username = "******";
    public static String password = "*****";
    public static Connection connection;

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
    public static void createTable() throws SQLException {
        PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Players (Name VARCHAR(100),UUID VARCHAR(100),Coins INT(100),PRIMARY KEY (Name))");
        ps.executeUpdate();
    }
    public static void updateTable() throws SQLException {
        PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT IGNORE INTO Players (Name,UUID,Coins) VALUES (?,?,?)");
        ps.setString(1, "TEST PLAYER");
        ps.setString(2, "TEST UUID");
        ps.setInt(3, 10);
        ps.executeUpdate();
    }
    public static int readTable() throws SQLException {
        PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Coins FROM Players WHERE Name = ?");
        ps.setString(1, "TEST PLAYER");
        ResultSet rs = ps.executeQuery();
        int coins = 0;
        if (rs.next()) {
            coins = rs.getInt("Coins");
        }
        return coins;
    }
}
