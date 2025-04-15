package de.redstonetechnik.bungeecoremadbryan.commands;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class CheckGroupCommand extends Command {
    public CheckGroupCommand() {
        super("checkgroup", "bungeecore.checkgroup", new String[0]);
    }
    public void execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.1"));
        } else {
            WarkingGroup group = new WarkingGroup(args[0]);
            if (group.getGroupName() == null) {
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.2"));
            }else {
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.3"));
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.4") + group.getGroupName());
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.5") + group.getGroupPrefix());
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.6") + group.getGroupSuffix());
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.7") + group.getGroupBannPower());
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.8") + group.getGroupBenBannPower());
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.9") + group.getGroupMutePower());
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.10") + group.getGroupBenMutePower());
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7");
            	sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.checkgroup.Message.1.3"));
            }
            }
    }
}
