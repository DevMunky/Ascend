package prog.ferrlix.ascend.util;

import org.apache.commons.io.IOUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
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
    ConfigUtil(String path){
        register(path);
    }
    private static Map<String,ConfigUtil> soleInstances = new HashMap<>();
    /**
     * Get the instance from the instance map.
     * Every file has one instance for itself and no more.
     * @param plugin this plugin instance, from static field in main class
     * @param fileName name of file in the plugin folder
     * @return The configuration
     */
    public static synchronized ConfigUtil getInstance(@NotNull Plugin plugin, String fileName){
        String path = plugin.getDataFolder().getAbsolutePath() + "\\" + fileName;
        if (!new File(path).exists()){
            Ascend.logger.severe("Path in data folder %s does not exist!".formatted(path));
        }
        if (soleInstances.get(path) == null)
            soleInstances.put(path, new ConfigUtil(path));
        soleInstances.get(path).register(path);
        return soleInstances.get(path);
    }
    private void register(String path){
        try{
            File tmpFile = new File(path);
            if (tmpFile.length() == 0){
                String[] paths = path.split("/");
                path = paths[paths.length - 1];
                InputStream resource = Ascend.plugin.getResource(path);
                if (resource != null){
                    writeInputStreamToFile(Ascend.plugin.getResource(path), tmpFile);}}
            this.file = tmpFile;
            this.config = YamlConfiguration.loadConfiguration(this.file);
            matchConfig();
        }catch(Exception e){
            e.printStackTrace();
        }
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
    public void writeInputStreamToFile(InputStream inputStream, File file){
        try(OutputStream outputStream = new FileOutputStream(file)){
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception e) {
            // handle exception here
        }
    }
}
