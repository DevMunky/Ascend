package dev.munky.ascend.util.sql.tables;

import com.google.gson.reflect.TypeToken;
import dev.munky.ascend.Ascend;
import dev.munky.ascend.domain.IslandDomain;
import dev.munky.ascend.domain.permission.DomainPermission;
import dev.munky.ascend.util.RelativeLocation;
import dev.munky.ascend.util.sql.MySQL;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class IslandTable{
    public static void createTable() throws SQLException {
        // creating table with massive varchar to store the whole permission json string.
        // Really shouldnt become a problem unless there are tens of thousands of players
        // who all need a permission in that domain.
        // (This is why certain domains do not have a per player permission mechanic,
        // and instead a group of players which is evaluated at runtime.
        PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS islands (island_uuid VARCHAR(36),owner_uuid VARCHAR(36),permissions VARCHAR(10000),PRIMARY KEY (island_uuid))");
        ps.executeUpdate();
    }

    public static void updateTable(IslandDomain island) throws SQLException {
        PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT IGNORE INTO islands (island_uuid,owner_uuid,permissions) VALUES (?,?,?)");
        ps.setString(1, island.uuid.toString());
        ps.setString(2, island.owner.toString());
        ps.setString(3, Ascend.gson.toJson(island.islandPermissions));
        ps.executeUpdate();
    }

    public static Object[] readTable(UUID islandUUID) throws SQLException {

        PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT owner_uuid FROM islands WHERE island_uuid = ?");
        ps.setString(1, islandUUID.toString());
        ResultSet rs = ps.executeQuery();
        UUID ownerUUID = null;
        if (rs.next()) {
            ownerUUID = UUID.fromString(rs.getString("owner_uuid"));
        }

        PreparedStatement ps2 = MySQL.getConnection().prepareStatement("SELECT permissions FROM islands WHERE island_uuid = ?");
        ps.setString(1, islandUUID.toString());
        ResultSet rs2 = ps.executeQuery();
        Map<DomainPermission,UUID> permissions = null;
        if (rs2.next()){
            Type type = new TypeToken<Map<DomainPermission, UUID>>(){}.getType();
            permissions = Ascend.gson.fromJson(rs2.getString("permissions"),type);
        }
        return new Object[]{islandUUID,ownerUUID,null,permissions};
    }
}
