package dev.munky.ascend.util.sql.tables;

import com.google.gson.reflect.TypeToken;
import dev.munky.ascend.Ascend;
import dev.munky.ascend.domain.IslandDomain;
import dev.munky.ascend.domain.permission.DomainPermission;
import dev.munky.ascend.util.sql.MySQL;

import java.lang.reflect.Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OwnerTable {
    public static void createTable() throws SQLException {
        // creating table with massive varchar to store the whole permission json string.
        // Really shouldnt become a problem unless there are tens of thousands of players
        // who all need a permission in that domain.
        // (This is why certain domains do not have a per player permission mechanic,
        // and instead a group of players which is evaluated at runtime.
        PreparedStatement ps = MySQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS owners (owner_uuid VARCHAR(36),island_uuids VARCHAR(10000),PRIMARY KEY (owner_uuid))");
        ps.executeUpdate();
    }

    public static void updateTable(UUID owner,IslandDomain island) throws SQLException {
        PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT IGNORE INTO owners (owner_uuid,island_uuids) VALUES (?,?,?)");
        ps.setString(1, island.uuid.toString());
        ps.setString(2, island.owner.toString());
        ps.setString(3, Ascend.gson.toJson(island.islandPermissions));
        ps.executeUpdate();
    }

    public static List<UUID> readTable(UUID ownerUUID) throws SQLException {

        PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT island_uuids FROM owners WHERE owner_uuid = ?");
        ps.setString(1, ownerUUID.toString());
        ResultSet rs = ps.executeQuery();
        List<UUID> island_uuids = new ArrayList<>();
        if (rs.next()) {
            Type type = new TypeToken<List<UUID>>(){}.getType();
            island_uuids = Ascend.gson.fromJson(rs.getString("island_uuids"),type);
        }
        return island_uuids;
    }
}
