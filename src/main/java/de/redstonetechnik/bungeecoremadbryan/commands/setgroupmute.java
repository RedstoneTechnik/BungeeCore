package de.redstonetechnik.bungeecoremadbryan.commands;

import java.sql.SQLException;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;

public class setgroupmute extends Command {
    public setgroupmute() {
        super("setgroupmute", "bungeecore.admin", new String[]{"setgroupmute"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.1"));
        } else {
			WarkingGroup group1 = WarkingGroup.get(args[0]);
        	WarkingGroup group = new WarkingGroup(args[0]);
            if (group.getGroupName() == null) {
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.2") + args[0] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.3"));
            }else {
            	double power = Double.parseDouble(args[1]);
            	double benpower = Double.parseDouble(args[2]);
                if (group.getGroupBannPower() == 0) {
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.4") + args[0] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.5") + args[1] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.6") + args[2] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.7"));
                	try {
                		group.setgroupmutepower(group.getGroup(), (int) power);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                	try {
                		group.setgroupbenmutepower(group.getGroup(), (int) benpower);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
					group1.clearCacheplayergroup(group1);
                }else {
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.8") + args[0] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.9") + args[1] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.10") + args[2] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroupmute.Message.1.11"));
                	try {
    					group.setgroupmutepower(group.getGroup(), (int) power);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
                	try {
                		group.setgroupbenmutepower(group.getGroup(), (int) benpower);
    				} catch (SQLException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
					group1.clearCacheplayergroup(group1);
                }
        	}
        }
    }
}
