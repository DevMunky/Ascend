package prog.ferrlix.ascend.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.OfflinePlayerArgument;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import prog.ferrlix.ascend.Ascend;
import prog.ferrlix.ascend.annotations.Command;
import prog.ferrlix.ascend.events.DomainCreateEvent;
import prog.ferrlix.ascend.objects.domains.Domain;
import prog.ferrlix.ascend.util.ConfigUtil;
import prog.ferrlix.ascend.util.MessageUtil;

@Command
public class IslandCommand {
    FileConfiguration messages = ConfigUtil.get("messages.yml").getFileConfig();
    // /is new
    public IslandCommand(){
        new CommandAPICommand("island")
                .withAliases("is")
                .withSubcommand(new CommandAPICommand("new")
                        .executesPlayer((p, args) -> {
                            if (Domain.ownerExists(p.getUniqueId()))
                                p.sendMessage(MessageUtil.get(messages,"client.island.create.already-own"));
                            Bukkit.getPluginManager().callEvent(new DomainCreateEvent(p.getUniqueId()));
                            if (Domain.ownerExists(p.getUniqueId()))
                                p.sendMessage(MessageUtil.get(messages,"client.island.create.success"));
                            else
                                p.sendMessage(MessageUtil.get(messages,"client.island.create.fail"));
                        }))
                .withSubcommand(new CommandAPICommand("teleport")
                        .withAliases("tp")
                        .withArguments(new OfflinePlayerArgument("owner"))
                        .executesPlayer((p,args)->{
                            if (!Domain.ownerExists(p.getUniqueId()))
                                p.sendMessage(MessageUtil.get(messages,"client.island.create.already-own"));
                        }))
                .register();
    }
}
