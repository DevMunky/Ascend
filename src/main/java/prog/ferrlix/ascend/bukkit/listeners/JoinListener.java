package prog.ferrlix.ascend.bukkit.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import prog.ferrlix.ascend.Ascend;
import prog.ferrlix.ascend.util.ConfigUtil;
import prog.ferrlix.ascend.util.MessageUtil;

public class JoinListener implements Listener {
    FileConfiguration messages = ConfigUtil.get("messages.yml").getFileConfig();
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.getPlayer().sendMessage(MessageUtil.get(messages,"general.welcome"));
    }
}
