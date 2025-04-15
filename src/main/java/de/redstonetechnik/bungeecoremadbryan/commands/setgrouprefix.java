package de.redstonetechnik.bungeecoremadbryan.commands;

import java.sql.SQLException;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class setgrouprefix extends Command {
    public setgrouprefix() {
        super("setgroupprefix", "bungeecore.admin", new String[]{"setgroupprefix"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupprefix.Message.1.1"));
        } else {
			WarkingGroup group1 = WarkingGroup.get(args[0]);
			group1.clearCacheplayergroup(group1);
        	WarkingGroup group = new WarkingGroup(args[0]);
            if (group.getGroupName() == null) {
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupprefix.Message.1.2") + args[0] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupprefix.Message.1.3"));
            }else {
                if (group.getGroupPrefix() == null) {
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupprefix.Message.1.4") + args[0] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupprefix.Message.1.5") + args[1] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupprefix.Message.1.6"));
                	try {
    					group.setgroupprefix(group.getGroup(), args[1]);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                }else {
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupprefix.Message.1.7") + args[0] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupprefix.Message.1.8") + args[1] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupprefix.Message.1.9"));
                	try {
    					group.setgroupprefix(group.getGroup(), args[1]);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                }
        	}
        }
    }
}
