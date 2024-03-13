package prog.ferrlix.ascend.world;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.configuration.file.FileConfiguration;
import prog.ferrlix.ascend.Ascend;
import prog.ferrlix.ascend.util.ConfigUtil;

import java.util.ArrayList;
import java.util.Collection;

public class WorldHandler {
    public static World world; //World for Ascend to use for everything
    MultiverseCore mvCore = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
    FileConfiguration config = ConfigUtil.get("config.yml").getFileConfig();
    public void checkWorld(){
        Collection<MultiverseWorld> worlds = mvCore.getMVWorldManager().getMVWorlds();
        ArrayList<String> worldNames = new ArrayList<>();
        for (MultiverseWorld world : worlds){worldNames.add(world.getName());}
        String configWorldName = (String) config.get("world-name");
        if (configWorldName==null) {
            Ascend.logger.severe("Config world-name returned null. Double check config.yml\n - Disabling plugin because there is no world to load Domains into!\n");
            Bukkit.getPluginManager().disablePlugin(Ascend.plugin);
            return;
        }if (!worldNames.contains(configWorldName)) {
            Ascend.logger.severe("Server does not have a world named %s \n - Creating new world for Domains to load into!".formatted(configWorldName));
            mvCore.getMVWorldManager().addWorld(configWorldName, World.Environment.NORMAL, null, WorldType.NORMAL, false, "VoidGenerator", false);
            //Ascend.logger.severe("Server does not have a world named %s \n - Disabling plugin because there is no world to load Domains into!".formatted(configWorldName));
            //Bukkit.getPluginManager().disablePlugin(Ascend.plugin);
            //return;
        }
        mvCore.getMVWorldManager().loadWorld(configWorldName);
        world = mvCore.getMVWorldManager().getMVWorld(configWorldName).getCBWorld();
    }
}
