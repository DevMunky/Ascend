package dev.munky.ascend.bukkit.listeners;

import dev.munky.ascend.Ascend;
import dev.munky.ascend.util.configuration.AscendedConfiguration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Ascend.message.sendMessage(e.getPlayer(),"general.welcome");
    }
}
