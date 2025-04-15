package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class ClearChat extends Command {
    public ClearChat() {
        super("cc", "bungeecore.cc", new String[0]);
    }
	public void execute(CommandSender sender, String[] args) {
	        ProxiedPlayer p = (ProxiedPlayer) sender;
			 if (args.length != 0) {
			 sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Verwende §c/cc");
			 } else {
			for(int i = 0 ; i < 200 ; i++) {
				for(ProxiedPlayer all : p.getServer().getInfo().getPlayers()) {
					all.sendMessage(" ");
				}
			}
			for(ProxiedPlayer all : p.getServer().getInfo().getPlayers()) {
				all.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ClearChat.Message"));
			}
			}
			 return;
			 }
}