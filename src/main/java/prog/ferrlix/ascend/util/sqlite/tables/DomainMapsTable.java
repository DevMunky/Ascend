package prog.ferrlix.ascend.util.sqlite.tables;

import prog.ferrlix.ascend.permissions.PermissionLevel;
import prog.ferrlix.ascend.util.sqlite.SQLite;

public class DomainMapsTable {
    static String Table = "domains_maps";
    public static void createTable() {//column domain = uuid of that domain
        String sql = """
                CREATE TABLE IF NOT EXISTS %s (
                	domain string PRIMARY KEY NOT NULL,
                	player string NOT NULL,
                	looker string NOT NULL,
                	switcher string NOT NULL,
                	taker string NOT NULL,
                	builder string NOT NULL,
                	partner string NOT NULL
                );""".formatted(Table);
        SQLite.execute(sql);
    }
    public static void insert(String domain, String player, String looker, String switcher, String taker, String builder, String partner){
        String sql = """
                INSERT INTO %s (domain, player, looker, switcher, taker, builder, partner)
                VALUES(`%s`,`%s`,`%s`,`%s`,`%s`,`%s`,`%s`);
                );""".formatted(Table,domain.toLowerCase(),player.toLowerCase(),looker.toLowerCase(),switcher.toLowerCase(),taker.toLowerCase(),builder.toLowerCase(),partner.toLowerCase());
        SQLite.execute(sql);
    }
    public static void update(String domain, PermissionLevel Column, Boolean NewValue) {
        String sql = """
                UPDATE %s
                SET %s = %s
                WHERE domain = %s;
                """.formatted(Table, Column.toString().toLowerCase(), NewValue.toString().toLowerCase(), domain.toLowerCase());
        SQLite.execute(sql);
    }
}
