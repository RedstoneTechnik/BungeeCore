package de.redstonetechnik.bungeecoremadbryan.commands;

import java.util.ArrayList;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class JoinCommand extends Command {
    public JoinCommand() {
        super("join", "");
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            if (args.length == 1) {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                if (target == null || !target.isConnected()) {
                    player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.join.Message.1.1"));
                    return;
                }

                if (target.equals(player)) {
                    player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.join.Message.1.2"));
                    return;
                }

                ServerInfo server = target.getServer().getInfo();
                String ServerPerm = (String)BungeeCore_MadBryan.serverPermissions.get(server.getName());
                    player.connect(server);
            } else {
                player.sendMessage(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.join.Message.1.3"));
            }
        }

    }

    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {
        return (Iterable)(args.length == 1 ? this.allPlayers(args[0]) : new ArrayList());
    }
}
