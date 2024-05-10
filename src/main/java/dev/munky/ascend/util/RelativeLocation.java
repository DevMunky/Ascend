package dev.munky.ascend.util;

import dev.munky.ascend.Ascend;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public record RelativeLocation(
        double x,
        double y,
        double z
) {
    public RelativeLocation(Location location){
        this(location.x(), location.y(), location.z());
    }
    public Location toBukkitLocation(){
        return new Location(Bukkit.getWorld(Ascend.config.domainWorldName),x,y,z);
    }
    public Location toBukkitLocation(World world){
        return new Location(world,x,y,z);
    }
}
