package prog.ferrlix.ascend.util.sqlite;

import org.jetbrains.annotations.Nullable;
import prog.ferrlix.ascend.Ascend;
import prog.ferrlix.ascend.util.sqlite.tables.DomainMapsTable;
import prog.ferrlix.ascend.util.sqlite.tables.DomainsTable;
import prog.ferrlix.ascend.util.sqlite.tables.IndexTable;

import java.sql.*;
import java.util.Arrays;

/**
 * Another singleton, this time for data storage (mostly domains)
 */
public class SQLite {
    private static String url = "jdbc:sqlite:%s\\data.db".formatted(Ascend.plugin.getDataFolder().getAbsolutePath());
    public static Connection connection;
    static{
        connection = getConnection();
        DomainsTable.createTable();
        IndexTable.createTable();
        DomainMapsTable.createTable();
    }
    public static void execute(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (Exception e) {
            Ascend.logger.severe("SQL EXCEPTION -");
            e.printStackTrace();
        }
    }
    private static @Nullable Connection getConnection(){
        try{
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                Ascend.logger.info("SQLITE CONNECTION SUCCESS, DRIVER %s".formatted(conn.getMetaData().getDriverName()));
            } else {
                Ascend.logger.severe("SQLITE CONNECTION FAILURE");
            }
            return conn;
        } catch (SQLException e) {
            Ascend.logger.severe("SQL EXCEPTION -");
            e.printStackTrace();
        }
        return null;
    }
    public static void end(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }
}
