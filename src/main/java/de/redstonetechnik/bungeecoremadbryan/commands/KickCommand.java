package de.redstonetechnik.bungeecoremadbryan.commands;

import java.util.ArrayList;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class KickCommand extends Command {
    public KickCommand() {
        super("kick", "bungeecore.kick", new String[0]);
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.kick.Message.1.1"));
        } else {
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.kick.Message.1.2"));
            } else {
                if (args.length == 1) {
                    target.disconnect(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.kick.Message.1.3"));
                } else {
                    StringBuilder msgBuilder = new StringBuilder();
                    msgBuilder.append(BungeeCore_MadBryan.ChatPrefix).append("§c");

                    for(int i = 1; i < args.length; ++i) {
                        msgBuilder.append(args[i]).append(" ");
                    }

                    target.disconnect(msgBuilder.toString());
                }

                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.kick.Message.1.4") + target.getName() + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.kick.Message.1.5"));
            }
        }
    }

    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {
        return (Iterable)(args.length == 1 ? this.allPlayers(args[0]) : new ArrayList());
    }
}