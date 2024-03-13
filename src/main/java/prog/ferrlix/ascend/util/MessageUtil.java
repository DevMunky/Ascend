package prog.ferrlix.ascend.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;
import prog.ferrlix.ascend.Ascend;

import static net.kyori.adventure.text.Component.text;

/**
 * Utilities for messages that get sent to players
 */
public class MessageUtil {
    /**
     * Should be the only MiniMessage.Builder instance
     */
    public static final MiniMessage.Builder builder = MiniMessage.builder();
    private static final String submitIssue = "Please submit an issue on Discord! titanborn.net !";
    /**
     * use MiniMessage to deserialize a String
     * @param input String to deserialize
     * @return deserialized Component
     */
    public static @NotNull Component stringToComponent(String input){
        if (input==null)
            return text().append(text("string")).build();
        return MiniMessage.miniMessage().deserialize(input);
    }
    /**
     * For easy messaging, but technically can get any string from a file.yml
     * @param config the file configuration to search, usually from ConfigUtil
     * @param path the path to get
     * @return the component from minimessage parsed value
     */
    public static Component get(FileConfiguration config, String path){
        String string = (String) config.get(path, null);
        if (string==null){
            Ascend.logger.severe("Path %s not found! %s".formatted(path,submitIssue));
            return text().append(text(" Path Not Found ")).build();
        }
        return stringToComponent(prefix(config)).append(stringToComponent(string));
    }
    public static String prefix(FileConfiguration config){
        String string = (String) config.get("prefix", null);
        return string != null ? string : "Prefix not found, make sure it is a root key in messages.yml"; // ternary to check whether prefix exists
    }
}
