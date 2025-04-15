package de.redstonetechnik.bungeecoremadbryan.commands;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.api.TimeHelper;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingUser;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Ban1Command extends Command {
	public static int auslöser = 0;
	public static int target1 = 0;
    public Ban1Command() {
        super("ban", "bungeecore.ban", new String[0]);
    }
	public boolean getBanPower(WarkingUser player, WarkingUser target) {
		target1 = 0;
		auslöser = 0;
		target1 = WarkingGroup.get(target.UserGroup).getGroupBenBannPower();
		auslöser = WarkingGroup.get(player.UserGroup).getGroupBannPower();
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
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "/ban [Player] [Zeit oder perma] [Grund]");
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "Zeit Format : m = Minuten | h = Stunden | d = Tage | y = Yahre");
        } else {
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append(BungeeCore_MadBryan.ChatPrefix);
            StringBuilder banReason = new StringBuilder();
            LocalDateTime start = LocalDateTime.now();
            LocalDateTime end = TimeHelper.addTime(start, args[1]);
            if (args.length == 2 && end != null) {
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "&cSyntax: /ban <Spieler> [<Zeit>] <Grund>");
                return;
            }
            WarkingUser target = new WarkingUser(args[0]);
            WarkingUser player = new WarkingUser(sender.getName());
            if (target.UserName == null) {
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.1"));
            } else {
            	if(getBanPower(player, target)) {
            		sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.2"));
            	}else {
                    for(int i = (end == null) ? 1 : 2; i < args.length; i++) {
                        banReason.append(args[i].replace("perma", "")).append(" ");
                    }

                    String msg = banReason.toString().trim();
                if (args[1].equalsIgnoreCase("perma")) {
                    msgBuilder.append(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.3") + player.UserName + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.4")+ msg);
                    end = TimeHelper.addTime(start, "500y"); //permanent mute
                } else {
                    end = TimeHelper.addTime(start, args[1]); //permanent mute
                }
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.11") + target.UserName + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.12") + msg);
                ProxiedPlayer targetPlayer = ProxyServer.getInstance().getPlayer(target.UUID);
                if (targetPlayer != null) {
                    target.banPlayer(targetPlayer.getAddress().getAddress().getHostAddress(), end, msg, sender.getName());
//                    long playerTime = target.BanTime.toLocalDateTime() - LocalDateTime.now();
                    long playerTime = target.BanTime.getTime() - Timestamp.valueOf(LocalDateTime.now()).getTime();

                    String formattedPlaytime = convertTime(playerTime);
                    msgBuilder.append(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.5") + formattedPlaytime + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.6"));
                    msgBuilder.append(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.7") + msg);
                    msgBuilder.append(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.8"));
                    msgBuilder.append(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.ban.Message.1.9") + sender.getName());
                    targetPlayer.disconnect(msgBuilder.toString());
                } else {
                    target.banPlayer((String)null, end, msg, sender.getName());
                }

            }
        }
      }
    }
    private String convertTime(long secondsx) {
        int days = (int) TimeUnit.MINUTES.toDays(secondsx);
        int hours = (int) (TimeUnit.MINUTES.toHours(secondsx) - TimeUnit.DAYS.toHours(days));
        int minutes = (int) (TimeUnit.MINUTES.toMinutes(secondsx) - TimeUnit.HOURS.toMinutes(hours) - TimeUnit.DAYS.toMinutes(days));

        if (days != 0) {
            return days + " Tage, " + hours + " Stunden, " + minutes + " Minuten";
        } else {
            if (hours != 0) {
                return hours + " Stunden, " + minutes + " Minuten";
            } else {
                return minutes + " Minuten";
            }
        }

    }
}

