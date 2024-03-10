package prog.ferrlix.ascend.util.sqlite.tables;

import org.bukkit.Location;
import prog.ferrlix.ascend.util.sqlite.SQLite;

public class DomainsTable {
    public enum Column{
        UUID("uuid"),LOCATION("location"),
        SIZEX("sizeX"),SIZEZ("sizeZ"),
        LEVEL("level"),OWNER("owner");
        private final String value;
        Column(String value) {
            this.value = value;
        }
        String toSQLFormat(){
            return this.value;
        }
    }
    private static final String Table = "domains";
    public static void update(String UUID, Column Column, Object NewValue) {
        String sql = """
                UPDATE %s
                SET %s = %s
                WHERE uuid = %s;
                """.formatted(Table, Column.toSQLFormat(), NewValue, UUID);
        SQLite.execute(sql);
    }
    public static void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS %s (
                	uuid string PRIMARY KEY NOT NULL,
                	location string NOT NULL,
                	sizeX integer NOT NULL,
                	sizeZ integer NOT NULL,
                	level integer NOT NULL,
                	owner string NOT NULL
                );""".formatted(Table);
        SQLite.execute(sql);
    }
    public static void insert(String uuid, Location location, Integer sizeX, Integer sizeZ, Integer level, String owner){
        String loc = "%s-%s-%s-%s-%s-%s".formatted(location.getX(),location.getY(),location.getZ(),location.getPitch(),location.getYaw(),location.getWorld());
        String sql = """
                INSERT INTO %s (uuid, location, sizeX, sizeZ, level, owner)
                VALUES(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`);
                );""".formatted(Table,uuid,loc,sizeX,sizeZ,level,owner);
        SQLite.execute(sql);
    }
}
