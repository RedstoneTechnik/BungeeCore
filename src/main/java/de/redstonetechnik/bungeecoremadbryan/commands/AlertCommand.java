package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class AlertCommand extends Command {
    public AlertCommand() {
        super("bc", "bungeecore.alert", new String[]{"alert"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "/alert [Message]");
        } else {
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append(BungeeCore_MadBryan.ChatPrefix);
            String[] var4 = args;
            int var5 = args.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String arg = var4[var6];
                msgBuilder.append(arg).append(" ");
            }

            String msg = msgBuilder.toString();
            msg = ChatColor.translateAlternateColorCodes('&', msg);
            ProxyServer.getInstance().broadcast(msg);
        }
    }
}
