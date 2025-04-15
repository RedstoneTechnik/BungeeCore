package de.redstonetechnik.bungeecoremadbryan.commands;


import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PingCommand extends Command {
    public PingCommand() {
        super("ping", "", new String[0]);
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            player.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Dein Ping beträgt §c" + player.getPing() + "§r§7 ms!");
        }

    }
}
