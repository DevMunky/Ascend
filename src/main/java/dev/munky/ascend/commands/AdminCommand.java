package dev.munky.ascend.commands;

import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.CommandTree;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.LiteralArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.munky.ascend.Ascend;
import dev.munky.ascend.util.configuration.MessageConfiguration;


public class AdminCommand {
    static MessageConfiguration messages = Ascend.message;
    public CommandTree command(){

        // I actually like command trees...

        return new CommandTree("ascend")
                .withPermission(CommandPermission.OP)
                .then(new LiteralArgument("reload")
                        .executes((sender,args)->{
                            Ascend.instance.reload();
                            sender.sendMessage(messages.message("admin.reload.success"));
                        })
                ).then(new LiteralArgument("debug")
                        .then(new BooleanArgument("state")
                                .executes((sender,args)->{
                                    boolean state = (boolean) args.get("state");
                                    Ascend.debug = state;
                                    if (state) messages.sendMessage(sender, "admin.debug.enabled");
                                    else messages.sendMessage(sender,"admin.debug.disabled");
                                })
                        ).executes((sender,args)->{
                            messages.sendMessage(sender,"general.unknown-command");
                        })
                ).then(new LiteralArgument("island")
                        .then(new LiteralArgument("create")
                                .then(new PlayerArgument("player")
                                        .executes((sender,args)->{

                                        })
                                )
                        )
                );
    }
}
