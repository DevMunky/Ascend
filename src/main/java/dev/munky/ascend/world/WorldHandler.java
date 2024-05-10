package dev.munky.ascend.world;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import dev.munky.ascend.Ascend;
import dev.munky.ascend.util.configuration.AscendedConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.Collection;

public class WorldHandler {
    public static World world; //World for Ascend to use for everything
    static MultiverseCore mvCore = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
    public static void checkWorld(){
        Collection<MultiverseWorld> worlds = mvCore.getMVWorldManager().getMVWorlds();
        ArrayList<String> worldNames = new ArrayList<>(mvCore.getMVWorldManager().getMVWorlds().stream().map(MultiverseWorld::getName).toList());
        String configWorldName =  Ascend.config.domainWorldName;
        if (configWorldName==null) {
            Ascend.logger.severe("Config world-name returned null. Double check config.yml\n - Disabling plugin because there is no world to load Domains into!\n");
            Bukkit.getPluginManager().disablePlugin(Ascend.plugin);
            return;
        }if (!worldNames.contains(configWorldName)) {
            Ascend.logger.severe("Server does not have a world named %s \n - Creating new world for Domains to load into!".formatted(configWorldName));
            mvCore.getMVWorldManager().addWorld(configWorldName, World.Environment.NORMAL, null, WorldType.NORMAL, false, "VoidGenerator", false);
        }
        mvCore.getMVWorldManager().loadWorld(configWorldName);
        world = mvCore.getMVWorldManager().getMVWorld(configWorldName).getCBWorld();
    }
}
