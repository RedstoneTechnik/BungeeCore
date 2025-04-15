package de.redstonetechnik.bungeecoremadbryan.commands;

import java.sql.SQLException;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class creategroup extends Command {
    public creategroup() {
        super("creategroup", "bungeecore.admin", new String[]{"creategroup"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.creategroup.Message.1.1"));
        } else {
        	WarkingGroup group = new WarkingGroup(args[0]);
            if (group.getGroupName() == null) {
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.creategroup.Message.1.2") + args[0] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.creategroup.Message.1.3"));
            	group.groupcreate(args[0]);
            }else {
        		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.creategroup.Message.1.4"));
        	}
        }
    }
}
