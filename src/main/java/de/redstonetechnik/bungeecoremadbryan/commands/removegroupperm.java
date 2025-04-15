package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.serverstatus;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class removegroupperm extends Command {
    public removegroupperm() {
        super("removegroupperm", "bungeecore.admin", new String[]{"removegroupperm"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7/removegroupperm §a[GruppenName Permission ServerName]");
        } else {
            //String serverid1 = args[1] + " " + args[2] + " " + args[3] + " " + args[4] + " " + args[5] + " " + args[6];
            String serverid = args[1].replace(",", "");
            String servername = args[2];
            WarkingGroup group = new WarkingGroup(args[0]);
            serverstatus permissions = new serverstatus(group.getGroupId());
            if (group.getGroupName() == null) {
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Diese Gruppe existiert nicht.");
            }else {
                if (permissions.getGroupServerPermissions(servername, group.getGroupId()).toString().contains(serverid.toString()) && !serverid.contains("!")) {
                    if (!permissions.getGroupServerPermissions(servername, group.getGroupId()).toString().contains(serverid.toString()) == true) {
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Die Gruppe wurde vom Server §a" + serverid + " §7schon entfernt ");
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "Aktuelle Server id laut Datenbank:" + permissions.getGroupServerPermissions(servername, group.getGroupId()).toString().contains(String.valueOf(serverid)));
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "ServerID vorhanden : " + permissions.getGroupServerPermissions(servername, group.getGroupId()).toString().equalsIgnoreCase(String.valueOf(serverid)));
                        permissions.clearserverpermissions(servername, group.getGroupId());
                    } else {
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Die Gruppe §a" + args[0] + " §7hat die Permission §a" + serverid.toString() + " §7entfernt bekommen auf dem Server:§a " + servername);
                        String serverid1 = permissions.getGroupServerPermissions(servername, group.getGroupId()).toString().replace("[", "").replace("]", "").replace(serverid.toString().replace("!", "").replace(",", "").replaceAll(serverid, serverid + ", "), "") + " ";
                        permissions.grouppermissionremove(group.getGroupId(), servername, serverid1);
                        sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + " §7Aktuelle Liste MYSQL Update : §a" + permissions.getGroupServerPermissions(servername, group.getGroupId()).toString());
                        //serverstatus.permissioncreate(serverid, args[2], args[3]);
                        permissions.clearserverpermissions(servername, group.getGroupId());
                    }
                } else {
                    sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + " §7Diese Gruppe hat diese Permission nicht zugewiesen bekommen.");
                }
            }
        }
    }
}
