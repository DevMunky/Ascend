package prog.ferrlix.ascend.util.sqlite.tables;

import prog.ferrlix.ascend.util.sqlite.SQLite;

public class IndexTable {
    static String Table = "indices";
    public static void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS %s (
                	id INTEGER PRIMARY KEY AUTOINCREMENT,
                	uuid string NOT NULL
                );""".formatted(Table);
        SQLite.execute(sql);
    }
    public static void insert(Integer id, String uuid){
        String sql = """
                INSERT INTO %s (id, uuid)
                VALUES(`%s`,`%s`);
                );""".formatted(Table,id,uuid);
        SQLite.execute(sql);
    }
    public static void update(Integer whereId, String setUuid) {
        String sql = """
                UPDATE %s
                SET uuid = %s
                WHERE id = %s;
                """.formatted(Table, setUuid, whereId);
        SQLite.execute(sql);
    }
    public static void update(String whereUuid,Integer setId) { //probably shouldn't use this one
        String sql = """
                UPDATE %s
                SET id = %s
                WHERE uuid = %s;
                """.formatted(Table, setId, whereUuid);
        SQLite.execute(sql);
    }
}
