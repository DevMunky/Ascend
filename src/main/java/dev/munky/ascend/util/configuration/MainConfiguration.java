package dev.munky.ascend.util.configuration;

import dev.munky.ascend.util.Logging;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.ApiStatus;

public final class MainConfiguration extends AscendedConfiguration{
    // I like the idea of only having primitives for configuration fields
    public boolean debug = false;
    public boolean verboseCommands = false;
    public String domainWorldName = "world_the_end"; // default value
    public final World domainWorld = getWorld();
    @ApiStatus.Internal
    private World getWorld(){
        World world = Bukkit.getWorld(domainWorldName);
        if (world==null){
            Logging.severe("No world exists by the name '%s', please update config and try again!".formatted(domainWorldName));
            throw new IllegalArgumentException("No world exists by the name '%s', please update config and try again!".formatted(domainWorldName));
        }
        return world;
    }
    public MainConfiguration() {
        super("config.yml");
        debug = yaml.getBoolean("debug", debug);
        verboseCommands = yaml.getBoolean("verbose.commands", verboseCommands);
        domainWorldName = yaml.getString("domain-world-name", domainWorldName);
    }

}
