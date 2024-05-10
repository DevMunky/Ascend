package dev.munky.ascend.util.configuration;

import org.bukkit.configuration.file.YamlConfiguration;

public final class MainConfiguration extends AscendedConfiguration{
    // I like the idea of only having primitives for configuration fields
    public boolean debug = false;
    public boolean verboseCommands = false;
    public String domainWorldName = "world_the_end"; // default value
    public MainConfiguration() {
        super("config.yml");
        debug = yaml.getBoolean("debug", debug);
        verboseCommands = yaml.getBoolean("verbose.commands", verboseCommands);
        domainWorldName = yaml.getString("domain-world-name", domainWorldName);
    }

}
