package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingPermissions;
import de.redstonetechnik.bungeecoremadbryan.sql.serverstatus;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class setgroupserver extends Command {
    public setgroupserver() {
        super("setgroupserver", "bungeecore.admin", new String[]{"setgroupserver"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7/setgroupserver §a[GruppenName ServerName, ServerName, ... 6]");
        } else {
            //String serverid1 = args[1] + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5] + " " + args[6];
            String serverid = args[1].replace(",", "");
            WarkingGroup group1 = WarkingGroup.get(args[0]);
            group1.clearCacheplayergroup(group1);
        	WarkingGroup group = new WarkingGroup(args[0]);
                WarkingPermissions permissions = new WarkingPermissions(group.getGroupId());
                if (group.getGroupName() == null) {
                    sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Diese Gruppe existiert nicht.");
                }else {
                    if (permissions.getGroupServerids(group.getGroupId()).toString().contains(serverid.toString()) && !serverid.contains("!")) {
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + " §7Diese Gruppe hat schon Server zugewiesen bekommen.");
                    } else {
                        if (permissions.getGroupServerids(group.getGroupId()).toString().contains(serverid.toString()) == true) {
                            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Die Gruppe wurde zum Server §a" + serverid + " §7schon hinzugefügt ");
                            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "Aktuelle Server id laut Datenbank:" + permissions.getGroupServerids(group.getGroupId()).toString().contains(String.valueOf(serverid)));
                            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "ServerID vorhanden : " + permissions.getGroupServerids(group.getGroupId()).toString().equalsIgnoreCase(String.valueOf(serverid)));
                            permissions.clearserverids(group.getGroupId());
                        } else {
                            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Die Gruppe §a" + args[0] + " §7wurde zu den Servern §a" + serverid.toString() + " §7hinzugefügt.");
                            String serverid1 = permissions.getGroupServerids(group.getGroupId()).toString().replace("[", "").replace("]", "") + " " + serverid.toString().replace("!", "").replace(",", "").replaceAll(serverid, serverid + ", ");
                            permissions.groupserveradd(group.getGroupId(), serverid.toString().replace("!", "").replace(",", "").replaceAll(serverid, serverid + ", "), serverid1);
                            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + " §7Aktuelle Liste MYSQL Update : §a" + permissions.getGroupServerids(group.getGroupId()).toString());
                            //serverstatus.permissioncreate(serverid, args[2], args[3]);
                            permissions.clearserverids(group.getGroupId());
                        }
                    }
                }
        }
    }
}