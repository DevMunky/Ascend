package prog.ferrlix.ascend.objects.domains;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.Vector;
import prog.ferrlix.ascend.Ascend;
import prog.ferrlix.ascend.util.ConfigUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Map of all domains and where they are in the world
 */
public class LocationMap {
    private static boolean enabled = false;
    private static String worldMode;
    private static World world;
    private static FileConfiguration config;
    private static Integer maxSizeX;
    private static Integer maxSizeZ;
    public static LinkedHashMap<Location, Domain> linkedMap = new LinkedHashMap<>();
    public static void init(){
        enabled = true;
        config = ConfigUtil.get("config.yml").getFileConfig();
        maxSizeX = (Integer) config.get("max.sizex");
        maxSizeZ = (Integer) config.get("max.sizez");
        worldMode = (String) config.get("island-world-mode");
        assert Objects.equals(worldMode, "one"):"World Mode in config.yml is not 'one', the only currently supported mode";
        String worldName = (String) config.get("world-name");
        assert worldName != null:"World Name in config.yml is null, please fix";
        assert maxSizeX != null:"Max Size X is null";
        assert maxSizeZ != null:"Max Size Z is null";
        world = Bukkit.getWorld(worldName);
        Ascend.logger.info("LocationMap fully initialized!");
    }
    public static void registerDomain(Domain domain) {
        if (linkedMap.entrySet().stream().findFirst().isEmpty()){
            Location center = new Location(world, 0,100,0);
            linkedMap.put(center, domain);
            domain.setLocation(center);
            Ascend.logger.info("First Location " + domain.getLocation().toString());
        }else{
            // This line actually doesn't have an error because the opening `if` checks to see if a value exists, and since a value exists, a last value does too
            //noinspection OptionalGetWithoutIsPresent
            Map.Entry<Location,Domain> lastEntry = linkedMap.entrySet().stream().skip(linkedMap.size() - 1).findFirst().get();
            Vector offset = new Vector(maxSizeX / 2, 0 ,maxSizeZ / 2);
            Location nextLocation = lastEntry.getKey().toVector().add(offset).toLocation(world); // new origin of domain
            linkedMap.put(nextLocation, domain);
            domain.setLocation(nextLocation);
            Ascend.logger.info("Added Location " + domain.getLocation().toString());
        }
    }
}
