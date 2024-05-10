package dev.munky.ascend.util.configuration;

import dev.munky.ascend.util.ComponentUtil;
import dev.munky.ascend.util.Logging;
import net.kyori.adventure.chat.ChatType;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

public class MessageConfiguration extends AscendedConfiguration{
    public MessageConfiguration() {
        super("messages.yml");
    }
    public Component message(String path){
        String miniMessage = yaml.getString(path.toLowerCase(),"<red>Undefined message -> " + path);
        return ComponentUtil.toComponent(miniMessage);
    }
    public Component messageWithPrefix(String path){
        String miniMessage = yaml.getString(path.toLowerCase(),"<red>Undefined message -> " + path);
        String miniPrefix = yaml.getString("prefix", "[Ascend] > ");
        return ComponentUtil.toComponent(miniPrefix).append(ComponentUtil.toComponent(miniMessage));
    }
    public void sendMessage(CommandSender sender, String path, boolean prefix){
        Component result;
        if (prefix){
            result = messageWithPrefix(path.toLowerCase());
        }else{
            result = message(path.toLowerCase());
        }
        sender.sendMessage(result);
    }
    public void sendMessage(CommandSender sender, String path){
        sendMessage(sender,path,true);
    }
}
