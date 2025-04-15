package de.redstonetechnik.bungeecoremadbryan.commands;

import java.util.Iterator;

import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingUser;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer.ChatMode;

public class TeamchatCommand extends Command {
    public TeamchatCommand() {
        super("tc", "bungeecore.teamchat", new String[]{"teamchat"});
    }

    public void execute(CommandSender sender, String[] args) {
        WarkingUser user = new WarkingUser(((ProxiedPlayer) sender).getUniqueId());
        WarkingGroup group = new WarkingGroup(user.UserGroup);
        String prefix = group.getGroupName();
        String suffix = user.UserName;

        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            if (args.length == 0) {
                player.sendMessage("§4Team §8» " + prefix.replace("&", "§") + " " + suffix + ((ProxiedPlayer) sender).getServer().getInfo().getName());
                return;
            }
            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append("§8[§aTC§8] §e").append(prefix.replace("&", "§") + " " + suffix + " §8| " + " §a➙  ");
            String[] var5 = args;
            int var6 = args.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String arg = var5[var7];
                msgBuilder.append(arg).append(" ");
            }

            String msg = msgBuilder.toString();
            msg = ChatColor.translateAlternateColorCodes('&', msg);
            Iterator var10 = ProxyServer.getInstance().getPlayers().iterator();

            while(var10.hasNext()) {
                ProxiedPlayer target = (ProxiedPlayer)var10.next();
                if (target.hasPermission("bungeecore.teamchat") && target.getChatMode() == ChatMode.SHOWN) {
                    target.sendMessage(msg);
                }
            }
        }

    }
}