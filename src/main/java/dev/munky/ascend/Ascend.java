package dev.munky.ascend;

import com.onarandombox.MultiverseCore.MultiverseCore;
import dev.munky.ascend.bukkit.listeners.PlayerListener;
import dev.munky.ascend.commands.AdminCommand;
import dev.munky.ascend.commands.IslandCommand;
import dev.munky.ascend.generators.VoidGenerator;
import dev.munky.ascend.util.Logging;
import dev.munky.ascend.util.configuration.ItemConfiguration;
import dev.munky.ascend.util.configuration.MainConfiguration;
import dev.munky.ascend.util.configuration.MessageConfiguration;
import dev.munky.ascend.util.sqlite.MySQL;
import dev.munky.ascend.world.WorldHandler;
import org.bukkit.Bukkit;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

/**
 * One block plugin main class
 */
public final class Ascend extends JavaPlugin {
    public static final Ascend instance = new Ascend();
    public static Plugin plugin;
    public static Logger logger;
    public static boolean debug = true;
    public static MainConfiguration config;
    public static ItemConfiguration item;
    public static MessageConfiguration message;
    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id) {
        Logging.info("Ascend's VoidGenerator is being used!");
        return new VoidGenerator();
    }
    @Override
    public void onEnable() {
        Logging.info("Hello, Ascend is enabling!");
        instantiateStaticVariables();
        registerCommands();
        registerEvents();
        MySQL.connect();
        WorldHandler.checkWorld();
        checkDependencies();
        Logging.info("Success! Time to Ascend");
    }
    /**
     * Method to reload from command. Should only really have one usage in AdminCommand
     */
    public void reload(){
        Logging.info("Reloading!");
        onDisable();
        onEnable();
        Logging.info("Successfully Reloaded!");
    }
    //this exists to update static variables upon reload, because I cannot re-instantiate the class and use static{}
    void instantiateStaticVariables(){ // These have to be in order of importance / usage
        Logging.debug("Initiating Certain Fields...");
        plugin = this;
        logger = this.getLogger();
        config = new MainConfiguration();
        item = new ItemConfiguration();
        message = new MessageConfiguration();
        debug = config.yaml.getBoolean("debug", debug);
        if (debug){
            Logging.warning("Debug Enabled!");
        }
    }
    void checkDependencies(){
        Logging.info("Dependencies...");
        MultiverseCore mvCore = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
        if (mvCore==null){
            Logging.severe("Multiverse not found, but is required. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
        }else{
            Logging.info("Multiverse found! Continuing...");
        }
    }
    void registerCommands(){
        Logging.debug("Registering Commands...");
        new AdminCommand();
        new IslandCommand();
    }
    void registerEvents(){
        Logging.debug("Registering Events...");
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logging.info("Disabling...");
        MySQL.disconnect();
        Logging.info("See you on the flip side cuzzo.");
    }
}

//you need to finish SQLITE and possibly get some game testing in
