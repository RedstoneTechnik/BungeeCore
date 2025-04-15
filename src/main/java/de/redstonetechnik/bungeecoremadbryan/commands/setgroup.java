package de.redstonetechnik.bungeecoremadbryan.commands;

import java.sql.SQLException;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingUser;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class setgroup extends Command {
    public setgroup() {
        super("setgroup", "bungeecore.admin", new String[]{"setgroup"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroup.Message.1.1"));
        } else {
			WarkingGroup group1 = WarkingGroup.get(args[1]);
			group1.clearCacheplayergroup(group1);
        	WarkingUser user = new WarkingUser(args[0]);
        	WarkingGroup group = new WarkingGroup(args[1]);
        	ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
        	if (user.UserName == null) {
        		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroup.Message.1.2") + args[0] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroup.Message.1.3"));
        	}else {
            if (group.getGroupName() == null) {
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroup.Message.1.4") + args[1] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroup.Message.1.5"));
            }else {
            	try {
					group.setgroup(user.UUID, group.getGroup());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroup.Message.1.6") + args[0] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroup.Message.1.7") + args[1] + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroup.Message.1.8"));
        	    target.disconnect(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroup.Message.1.9") + group.getGroupName() + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.setgroup.Message.1.10"));
            }
        }
        }
    }
}
