package de.redstonetechnik.bungeecoremadbryan.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ServerSwitchCommand extends Command {
    private String serverName;

    public ServerSwitchCommand(String cmd, String name, String permission, String... aliases) {
        super(cmd, permission, aliases);
        this.serverName = name;
    }

    public void execute(CommandSender sender, String[] strings) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            ServerInfo target = ProxyServer.getInstance().getServerInfo(this.serverName);
            player.connect(target);
        }

    }
}
