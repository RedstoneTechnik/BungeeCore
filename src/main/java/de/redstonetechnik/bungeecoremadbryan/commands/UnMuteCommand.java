package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingUser;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class UnMuteCommand extends Command {
    public UnMuteCommand() {
        super("unmute", "bungeecore.unmute", new String[0]);
    }
    public void execute(CommandSender sender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer)sender;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        if (args.length < 1) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.unmute.Message.1.1"));
        } else {
            WarkingUser target = new WarkingUser(args[0]);
            if (target.UserName == null) {
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.unmute.Message.1.2"));
            }else {
            	if (target.isMuted()) {
                    LocalDate date = LocalDate.now();
                    String newdate = date.format(BungeeCore_MadBryan.DateFormat);
                    Timestamp unmuteTime;
                    Date parsedDate = null;
                    try {
                        parsedDate = dateFormat.parse(newdate);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    unmuteTime = new Timestamp(parsedDate.getTime());
                    String unmutereason = args[1];
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.unmute.Message.1.3") + target.UserName + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.unmute.Message.1.4"));
            	target.isunMuted(player, unmuteTime, unmutereason);
            }else {
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.unmute.Message.1.5") + target.UserName + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.unmute.Message.1.6"));
            }
}
    }
}
}

