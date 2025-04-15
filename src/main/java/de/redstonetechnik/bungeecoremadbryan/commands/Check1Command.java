package de.redstonetechnik.bungeecoremadbryan.commands;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingUser;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class Check1Command extends Command {
    public Check1Command() {
        super("check", "bungeecore.check", new String[]{"check"});
    }
    public void execute(CommandSender sender, String[] args) {
    	Timestamp perma;
    	perma = Timestamp.from(Instant.ofEpochSecond(946674800L));
        if (args.length < 1) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.1"));
        } else {
            WarkingUser target = new WarkingUser(args[0]);
            if (target.UserName == null) {
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.2"));
            }else {
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.3"));
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	if(BungeeCord.getInstance().getPlayer(args[0]) != null) {
//            		LuckPerms api = LuckPermsProvider.get();
//                    User user = api.getUserManager().getUser(target.UUID);
//                                    String prefix = user.getPrimaryGroup();
//                                    String suffix = user.getUsername();	
            		WarkingUser target1 = new WarkingUser(target.UUID);
            		WarkingGroup group = new WarkingGroup(target1.UserGroup);
                    if (group.getGroupPrefix() == null) {
                    	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.4") + target1.UserName);
                    }else {
                    	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.4") + group.getGroupName() + " " +group.getGroupPrefix().toString().replace("&", "§") + " " + target1.UserName);
                	}
//                	sender.sendMessage(BungeeCore.ChatPrefix + "§7Gruppen Gewicht: §9" + BungeeCore.instance.config.getInt("BanPower." + target1.UserGroup));
            	}else {
            		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.5") + target.UserName);
            	}
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	if(target.isBanned()) {
            		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.6"));
                	if(target.BanTime != null) {
                    	if(target.BanTime.before(WarkingUser.PermaBan)) {
                    		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.8") + "Permanent");
                    	}else {
                    		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.8") + target.BanTime);
                    	}
                	}else {
                		    sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.9"));
                	}
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.10")+ target.Banner);
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.11") + target.BanReason);
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	}else {
            		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.7"));
                	if(target.UnBanTime == null){
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.9"));
					}else{
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.2.9") + target.UnBanTime);
					}
					if(target.UnBanner == null){
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.12"));
					}else{
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.2.12") + target.UnBanner);
					}
					if(target.UnBanReason == null){
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.13"));
					}else{
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.2.13") + target.UnBanReason);
					}
            	    sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	}
            	if(target.MuteTime!= null) {
            		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.14"));
                	if(target.MuteTime != null) {
                    	if(target.MuteTime.before(WarkingUser.PermaMute)) {
                    		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.15"));
                    	}else {
                    		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.16") + target.MuteTime);
                    	}
                	}else {
                		    sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.17"));
                	}
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.18") + target.Muter);
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.19") + target.MuteReason);
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	}else {
            		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.20"));
					if(target.UnMuteTime == null){
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.17"));
					}else{
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.2.17") + target.UnMuteTime);
					}
					if(target.UnMuter == null){
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.18") + " §4✘");
					}else{
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.2.18") + target.UnMuter);
					}
					if(target.UnMuteReason == null){
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.21"));
					}else{
						sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.2.21") + target.UnMuteReason);
					}
                	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	}
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.check.Message.1.3"));
            }
            }
    }
}
