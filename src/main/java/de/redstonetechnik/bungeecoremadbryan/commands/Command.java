package de.redstonetechnik.bungeecoremadbryan.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.TabExecutor;

public abstract class Command extends net.md_5.bungee.api.plugin.Command implements TabExecutor {
    public Command(String name, String permission, String... aliases) {
        super(name, permission, aliases);
        BungeeCore_MadBryan.commands.put("/" + name, permission);
    }

    public Iterable<String> allPlayers(String begin) {
        List<String> suggestions = new ArrayList();
        Iterator var3 = ProxyServer.getInstance().getPlayers().iterator();

        while(var3.hasNext()) {
            ProxiedPlayer player = (ProxiedPlayer)var3.next();
            String playerName = player.getName();
            if (playerName.startsWith(begin)) {
                suggestions.add(playerName);
            }
        }

        return suggestions;
    }

    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {
        return new ArrayList();
    }
}

