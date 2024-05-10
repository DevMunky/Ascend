package dev.munky.ascend.util.configuration;

import dev.munky.ascend.Ascend;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public abstract class AscendedConfiguration {
    public static final File PLUGIN_FOLDER = Ascend.plugin.getDataFolder();
    public File file;
    public YamlConfiguration yaml;

    public AscendedConfiguration(File file){
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        this.file = file;
        this.yaml = yamlConfiguration;
    }
    public AscendedConfiguration(String fileName){
        File file = new File(PLUGIN_FOLDER.getAbsolutePath() + File.separatorChar + fileName);
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        this.file = file;
        this.yaml = yamlConfiguration;
    }
}
