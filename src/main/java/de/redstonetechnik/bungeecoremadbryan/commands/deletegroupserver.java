package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingPermissions;
import de.redstonetechnik.bungeecoremadbryan.sql.serverstatus;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class deletegroupserver extends Command {
    public deletegroupserver() {
        super("removegroupserver", "bungeecore.admin", new String[]{"removegroupserver"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7/removegroupserver §a[GruppenName ServerName]");
        } else {
            //String serverid1 = args[1] + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5] + " " + args[6];
            String serverid = args[1].replace(",", "");
        	WarkingGroup group = new WarkingGroup(args[0]);
                WarkingPermissions permissions = new WarkingPermissions(group.getGroupId());
                serverstatus serverstatus = new serverstatus(serverid);
                if (group.getGroupName() == null) {
                    sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Diese Gruppe existiert nicht.");
                }else {
                    if (permissions.getGroupServerids(group.getGroupId()).toString().contains(serverid.toString()) && !serverid.contains("!")) {
                        if (!permissions.getGroupServerids(group.getGroupId()).toString().contains(serverid.toString()) == true) {
                            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Die Gruppe wurde vom Server §a" + serverid + " §7schon entfernt ");
                            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "Aktuelle Server id laut Datenbank:" + permissions.getGroupServerids(group.getGroupId()).toString().contains(String.valueOf(serverid)));
                            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "ServerID vorhanden : " + permissions.getGroupServerids(group.getGroupId()).toString().equalsIgnoreCase(String.valueOf(serverid)));
                            permissions.clearserverids(group.getGroupId());
                        } else {
                            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Die Gruppe §a" + args[0] + " §7wurde von den Servern §a" + serverid.toString() + " §7entfernt.");
                            String serverid1 = permissions.getGroupServerids(group.getGroupId()).toString().replace("[", "").replace("]", "").replace(serverid.toString().replace("!", "").replace(",", "").replaceAll(serverid, serverid + ", "), "") + " ";
                            permissions.groupserverremove(group.getGroupId(), serverid.toString().replace("!", "").replace(",", "").replaceAll(serverid, serverid + ", "), serverid1);
                            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + " §7Aktuelle Liste MYSQL Update : §a" + permissions.getGroupServerids(group.getGroupId()).toString());
                            //serverstatus.permissioncreate(serverid, args[2], args[3]);
                            permissions.clearserverids(group.getGroupId());
                        }
                    } else {
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + " §7Diese Gruppe hat diesen Server nicht zugewiesen bekommen.");
                    }
                }
        }
    }
}