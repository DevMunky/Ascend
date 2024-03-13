package prog.ferrlix.ascend.commands;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.executors.ConsoleCommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import prog.ferrlix.ascend.Ascend;
import prog.ferrlix.ascend.annotations.Command;
import prog.ferrlix.ascend.util.ConfigUtil;
import prog.ferrlix.ascend.util.MessageUtil;

/**
 * Main "/ascend" command class. Initiated with just the constructor
 */
@Command
public class MainCommand {
    FileConfiguration messages = ConfigUtil.get("messages.yml").getFileConfig();
    public MainCommand(){
        new CommandAPICommand("ascend")
                .withArguments(new StringArgument("action"))
                .withAliases("as")
                .executes((sender, args) -> {
                    String action = (String) args.get("action");
                    if (!(sender instanceof Player|sender instanceof ConsoleCommandSender|sender instanceof ConsoleCommandExecutor))
                        throw CommandAPI.failWithString("Command can only be used by players and console!");
                    if (action==null)
                        throw CommandAPI.failWithString("First argument (action) is undefined!");
                    switch(action){
                        case"reload"-> {
                            Ascend.instance.reload();
                            sender.sendMessage(MessageUtil.get(messages, "admin.reload.success"));}
                        case"debug"-> {
                            if (Ascend.debug){
                                Ascend.debug = false;
                                sender.sendMessage(MessageUtil.get(messages, "admin.debug.enable"));
                            }else{
                                Ascend.debug = true;
                                sender.sendMessage(MessageUtil.get(messages, "admin.debug.disable"));}
                        }
                        case "world"->{
                            Ascend.instance.getWorldHandler().checkWorld();
                        }
                        default -> {
                            sender.sendMessage(MessageUtil.get(messages, "general.unknown-command"));
                        }
                    }
                }).register();
    }
}
