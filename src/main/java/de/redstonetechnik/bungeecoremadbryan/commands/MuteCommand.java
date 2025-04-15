package de.redstonetechnik.bungeecoremadbryan.commands;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingUser;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class MuteCommand extends Command {
	
	public static int auslöser = 0;
	public static int target1 = 0;
	
    public MuteCommand() {
        super("mute", "bungeecore.mute", new String[0]);
    }
    
	public boolean getMutePower(WarkingUser player, WarkingUser target) {
		target1 = 0;
		auslöser = 0;
		target1 = WarkingGroup.get(target.UserGroup).getGroupBenMutePower();
		auslöser = WarkingGroup.get(player.UserGroup).getGroupMutePower();
		//target1 = LuckPermsProvider.get().getGroupManager().getGroup(LuckPermsProvider.get().getUserManager().getUser(target).getPrimaryGroup().toString()).getWeight().getAsInt();
		//auslöser = LuckPermsProvider.get().getGroupManager().getGroup(LuckPermsProvider.get().getUserManager().getUser(player).getPrimaryGroup().toString()).getWeight().getAsInt();
		if(target1 >= auslöser) {
			return true;
		}else {
			return false;
		}
	}
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 3) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "/mute [Player] [dd.mm.yyyy oder perma] [Grund]");
        } else {
            WarkingUser target = new WarkingUser(args[0]);
            WarkingUser player = new WarkingUser(sender.getName());
            if (target.UserName == null) {
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.mute.Message.1.1"));
            } else  {
            	this.getMutePower(player, target);
            	if(getMutePower(player, target)) {
            		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.mute.Message.1.2"));
            	}else {
                StringBuilder msgBuilder = new StringBuilder();
                msgBuilder.append(BungeeCore_MadBryan.ChatPrefix);
                Timestamp muteTime;
                if (args[1].equalsIgnoreCase("perma")) {
                    msgBuilder.append(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.mute.Message.1.3"));
                    muteTime = Timestamp.from(Instant.ofEpochSecond(946674800L));
                } else {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

                    try {
                        Date parsedDate = dateFormat.parse(args[1]);
                        muteTime = new Timestamp(parsedDate.getTime());
                        msgBuilder.append(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.mute.Message.1.4"));
                        msgBuilder.append(muteTime.toLocalDateTime().format(BungeeCore_MadBryan.DateFormat));
                        msgBuilder.append(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.mute.Message.1.5"));
                    } catch (ParseException var9) {
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.mute.Message.1.6"));
                        return;
                    }
                }

                StringBuilder muteReason = new StringBuilder();

                for(int i = 2; i < args.length; ++i) {
                    muteReason.append(args[i]).append(" ");
                }

                String msg = muteReason.toString();
                msgBuilder.append(msg);
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.mute.Message.1.7") + target.UserName + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.mute.Message.1.8") + msg);
                ProxiedPlayer targetPlayer = ProxyServer.getInstance().getPlayer(target.UUID);
                if (targetPlayer != null) {
                    target.mutePlayer(targetPlayer.getAddress().getAddress().getHostAddress(), muteTime, msg, sender.getName());
                    targetPlayer.sendMessage(msgBuilder.toString());
                } else {
                    target.mutePlayer((String)null, muteTime, msg, sender.getName());
                }
              }
            }
        }
    }
}

