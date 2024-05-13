package dev.munky.ascend.util;

import dev.munky.ascend.Ascend;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public record RelativeLocation(
        double x,
        double z
) {
    public RelativeLocation(Location location){
        this(location.x(), location.z());
    }
    public Location toBukkitLocation(){
        World world = Ascend.config.domainWorld;
        return new Location(world,x,world.getSeaLevel(),z);
    }
    public Location toBukkitLocation(World world){
        return new Location(world,x,world.getSeaLevel(),z);
    }
}
