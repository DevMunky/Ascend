package prog.ferrlix.ascend;

import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import prog.ferrlix.ascend.bukkit.listeners.JoinListener;
import prog.ferrlix.ascend.commands.IslandCommand;
import prog.ferrlix.ascend.commands.MainCommand;
import prog.ferrlix.ascend.generators.VoidGenerator;
import prog.ferrlix.ascend.objects.domains.LocationMap;
import prog.ferrlix.ascend.util.ConfigUtil;
import prog.ferrlix.ascend.util.sqlite.SQLite;
import prog.ferrlix.ascend.world.WorldHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

/**
 * One block plugin main class
 */
public final class Ascend extends JavaPlugin {
    /**
     * Grab main class instance
     */
    public static Ascend instance;
    /**
     * Grab plugin instance
     */
    public static Plugin plugin;
    /**
     * Grab the plugin logger
     */
    public static Logger logger;
    /**
     * How to let other classes know debug is enabled
     */
    private static WorldHandler worldHandler;
    public static boolean debug = true;
    FileConfiguration config;
    /**
     * Constructor to be used by Bukkit
     */
    public Ascend(){
        instance = this;
    }
    @Override
    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        Ascend.logger.warning("Ascend's VoidGenerator is being used!");
        return new VoidGenerator();}
    @Override
    public void onEnable() {
        this.getLogger().info("Hello, Ascend is enabling!");
        instantiateStaticVariables();
        registerCommands();
        registerEvents();
        // would register sqlite, but it's a static utility class!
        checkDependancies();
        Ascend.logger.info("Ascend fully enabled! SET UP WORLD HANDLER");
        //TODO finish worldHandler
        //TODO finish SQLITE save method
        //TODO finish LocationMap and etc.
    }
    /**
     * Method to reload from command. Should only really have one usage in MainCommand
     */
    public void reload(){
        logger.info("Ascend Custom Plugin Reloading!");
        //onDisable();
        onEnable();
        logger.info("Ascend Custom Plugin Successfully Reloaded!");
    }
    //this exists to update static variables upon reload, because I cannot re-instantiate the class and use static{}
    void instantiateStaticVariables(){ // These have to be in order of importance / usage
        plugin = this;
        logger = this.getLogger();
        config = ConfigUtil.get("config.yml").getFileConfig();
        debug = (boolean) config.get("settings.debug", debug);
        worldHandler = new WorldHandler();
        if (debug){
            logger.warning("Debug Enabled!");
        }
        LocationMap.init(); // Initialize the location map for domains
    }
    void checkDependancies(){
        MultiverseCore mvCore = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
        if (mvCore==null){
            logger.severe("Multiverse not found, but is required. Disabling...");
            Bukkit.getPluginManager().disablePlugin(this);
        }else{
            logger.info("Multiverse found! Continuing...");
        }
    }
    void registerCommands(){
        new MainCommand();
        new IslandCommand();
    }
    void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        SQLite.end();
        Ascend.logger.info("Ascend is Disabling!");
        Ascend.logger.info("Ascend disabled, goodbye!");
    }
    public WorldHandler getWorldHandler(){return worldHandler;}
}

//you need to finish SQLITE and possibly get some game testing in
