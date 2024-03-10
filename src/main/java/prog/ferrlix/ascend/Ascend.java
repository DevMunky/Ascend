package prog.ferrlix.ascend;

import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import prog.ferrlix.ascend.commands.IslandCommand;
import prog.ferrlix.ascend.commands.MainCommand;
import prog.ferrlix.ascend.generators.VoidGenerator;
import prog.ferrlix.ascend.util.ConfigUtil;
import prog.ferrlix.ascend.util.sqlite.SQLite;

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
    }
    /**
     * Method to reload from command. Should only really have one usage in MainCommand
     */
    public void reload(){
        logger.info("Ascend Custom Plugin Reloading!");
        onDisable();
        onEnable();
        logger.info("Ascend Custom Plugin Successfully Reloaded!");
    }
    //this exists to update static variables upon reload, because I cannot re-instantiate the class and use static{}
    void instantiateStaticVariables(){
        plugin = this;
        logger = this.getLogger();
        config = ConfigUtil.getInstance(this,"config.yml").getFileConfig();
        debug = (boolean) config.get("settings.debug", debug);
        if (debug){
            logger.warning("Debug Enabled!");
        }
    }
    void checkDependancies(){
        MultiverseCore mvCore = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
        if (mvCore==null){
            logger.severe("Multiverse Required");
            Bukkit.getPluginManager().disablePlugin(this);
        }else{
            logger.info("Multiverse found!");
        }
    }
    void registerCommands(){
        new MainCommand();
        new IslandCommand();
    }
    void registerEvents(){
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        SQLite.end();
        Ascend.logger.info("Ascend is Disabling!");
        Ascend.logger.info("Ascend disabled, goodbye!");
    }
}

//you need to finish SQLITE and possibly get some game testing in
