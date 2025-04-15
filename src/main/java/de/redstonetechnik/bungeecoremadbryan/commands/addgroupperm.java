package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.serverstatus;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class addgroupperm extends Command {
    public addgroupperm() {
        super("addgroupperm", "bungeecore.admin", new String[]{"addgroupperm"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.addgroupperm.Message.1.1"));
        } else {
            //String serverid1 = args[1] + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5] + " " + args[6];
            String permissions1 = args[1].replace(",", "");
            String serverid = args[2];
            WarkingGroup group = new WarkingGroup(args[0]);
            serverstatus permissions = new serverstatus(serverid, group.getGroupId1(), permissions1);
            if (group.getGroupName() == null) {
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Diese Gruppe existiert nicht.");
            }else {
                if (permissions.getGroupServerPermissions(serverid, group.getGroupId()).toString().contains(permissions1.toString()) && !permissions1.contains("!")) {
                    sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + " §7Diese Gruppe hat schon die Permission zugewiesen bekommen.");
                } else {
                    if (permissions.getGroupServerPermissions(serverid, group.getGroupId()).toString().contains(permissions1.toString()) == true) {
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Die Permission wurde zum Server §a" + serverid + " §7schon hinzugefügt ");
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "Aktuelle Server id laut Datenbank:" + permissions.getGroupServerPermissions(serverid, group.getGroupId()).toString().contains(String.valueOf(serverid)));
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "ServerID vorhanden : " + permissions.getGroupServerPermissions(serverid, group.getGroupId()).toString().equalsIgnoreCase(String.valueOf(serverid)));
                        permissions.clearserverpermissions(serverid, group.getGroupId());
                    } else {
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Die Permission §a" + args[1] + " §7wurde zu den Servern §a" + serverid.toString() + " §7hinzugefügt.");
                        String serverid1 = permissions.getGroupServerPermissions(serverid, group.getGroupId()).toString().replace("[", "").replace("]", "") + " " + permissions1.toString().replace("!", "").replace(",", "").replaceAll(permissions1, permissions1 + ", ");
                        permissions.groupserverpermissionadd(group.getGroupId(), serverid, permissions1.toString().replace("!", "").replace(",", "").replaceAll(permissions1, permissions1 + ", "), serverid1);
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + " §7Aktuelle Liste MYSQL Update : §a" + permissions.getGroupServerPermissions(serverid, group.getGroupId()).toString());
                        //serverstatus.permissioncreate(serverid, args[2], args[3]);
                        permissions.clearserverpermissions(serverid, group.getGroupId());
                    }
                }
            }
        }
    }
}
