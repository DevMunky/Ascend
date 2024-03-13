package prog.ferrlix.ascend.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import prog.ferrlix.ascend.Ascend;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Singleton Class with a map of every file with its own singleton instance.
 * Each file has its own instance
 */
public class ConfigUtil {
    private File file;
    private FileConfiguration config;

    /**
     * Register static fields, check File paths, and match keys in files.
     * @param path path for the File associated with this instance
     */
    ConfigUtil(String path){
        File tmpFile = new File(path);
        if (!tmpFile.exists()){
            Ascend.logger.severe("Path in data folder %s does not exist!".formatted(path));
            // if the resource doesnt exist, then we messed up
            if (Ascend.plugin.getResource(tmpFile.getName())==null){
                Ascend.logger.severe("No resource found for %s ! Major config error, contact DevMunky or Optivat!".formatted(tmpFile.getName()));
                Bukkit.getPluginManager().disablePlugin(Ascend.plugin);
            }
            // since the file doesnt exist, replace it with the one from resources
            Ascend.plugin.saveResource(tmpFile.getName(), true);
            Ascend.logger.warning("Created new %s because it did not exist before.".formatted(tmpFile.getName()));
        }
        this.file = tmpFile;
        this.config = YamlConfiguration.loadConfiguration(this.file);
        // "match" the config
        matchConfig();
    }
    private static Map<String,ConfigUtil> soleInstances = new HashMap<>();
    /**
     * Get the instance from the instance map.
     * Every file has one instance for itself and no more.
     * Warn if the file does not exist, yet still create that object, because the constructor will create one.
     * If the constructor errors, nothing will happen except for error messages and NPE wherever the config is used.
     * @param fileName name of file in the plugin folder
     * @return The configuration
     */
    @NotNull
    public static synchronized ConfigUtil get(String fileName){
        String path = Ascend.plugin.getDataFolder().getAbsolutePath() + "\\" + fileName;
        if (soleInstances.get(path)==null)
            soleInstances.put(path, new ConfigUtil(path));
        return soleInstances.get(path);
    }
    public File getFile(){return this.file;}
    public FileConfiguration getFileConfig(){return this.config;}
    /**
     * Inserts / deletes keys in this#file using the corresponding file in resources.
     * Doesn't modify values, only cleans up unknown keys and adds nonexistent keys
     */
    public void matchConfig() {
        try {
            InputStream is = Ascend.plugin.getResource(file.getName());
            if (is != null) {
                YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(file);
                for (String key : defConfig.getKeys(true))
                    if (!config.contains(key)) config.set(key, defConfig.getConfigurationSection(key));

                for (String key : config.getKeys(true))
                    if (!defConfig.contains(key)) config.set(key, null);
                config.save(file);
                return;
            }Ascend.logger.warning("Match Config - Getting resource %s returned null".formatted(file.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
