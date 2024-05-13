package dev.munky.ascend.domain;

import dev.munky.ascend.Ascend;
import dev.munky.ascend.util.Logging;
import dev.munky.ascend.util.RelativeLocation;
import dev.munky.ascend.util.sql.tables.IslandTable;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IslandManager {
    private final Ascend plugin;
    private final World domainWorld;
    private final List<IslandDomain> islands;
    public IslandManager(Ascend plugin){
        this.plugin = plugin;
        this.domainWorld = Ascend.config.domainWorld;
        this.islands = new ArrayList<>();
    }
    public RelativeLocation createIsland(Player owner){
        UUID ownerUUID = owner.getUniqueId();
        return null;
    }
    public RelativeLocation loadIsland(UUID owner){
        // TODO get island id from ownerTable then read the data using an index passed in the params of this method (gui in the future will give options for which index)
        Object[] data = IslandTable.readTable();
        return null;
    }
    public PreparedStatement saveIsland(UUID owner){
        return null;
    }
    public RelativeLocation nextLocation(){
        int increment = 1;
        int x = 0;  // center x
        int y = 0;  // center y
        var d = "right";
        int n = 1;
        RelativeLocation finalLocation = null;
        for (int i = 0; i < islands.size(); i++) {
            // change the direction
            if (i == Math.pow(n, 2) - n) {
                d = "right";
            } else if (i == Math.pow(n, 2)) {
                d = "down";
            } else if (i == Math.pow(n, 2) + n) {
                d = "left";
            } else if (i == Math.pow(n, 2) + (n * 2 + 1)) {
                d = "up";
                n += 2;
            }
            Logging.info(x + "," + y);
            // get the current x and y.
            switch (d) {
                case "right" -> x += increment;
                case "left" -> x -= increment;
                case "down" -> y += increment;
                default -> y -= increment;
            }
            finalLocation = new RelativeLocation(x,y);
        }
        return finalLocation == null ? new RelativeLocation(0,0) : finalLocation;
    }
}
