package de.redstonetechnik.bungeecoremadbryan.commands;

import java.util.ArrayList;
import java.util.HashMap;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingUser;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer.ChatMode;

public class MsgCommand extends Command {
    protected static final HashMap<String, ProxiedPlayer> lastChats = new HashMap();

    public MsgCommand() {
        super("msg", "", new String[]{"w", "tell"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            if (args.length < 2) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.msg.Message.1.1"));
                return;
            }

            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.msg.Message.1.2"));
                return;
            }

            if(WarkingUser.allowMessages(target.getUniqueId().toString())) {
            if (target.getChatMode() != ChatMode.SHOWN) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.msg.Message.1.3"));
                return;
            }

            if (target.equals(player)) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.msg.Message.1.4"));
                return;
            }

            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append("§5").append(player.getName()).append("§8>§5").append(target.getName()).append(" §r§7");

            for(int i = 1; i < args.length; ++i) {
                msgBuilder.append(args[i]).append(" ");
            }

            String msg = msgBuilder.toString();
            player.sendMessage(msg);
            target.sendMessage(msg);
            BungeeCore_MadBryan.log(msg);
            lastChats.put(player.getName().toLowerCase(), target);
            lastChats.put(target.getName().toLowerCase(), player);
        }else {
        	player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.msg.Message.1.5"));
        }
        }

    }

    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {
        return (Iterable)(args.length == 1 ? this.allPlayers(args[0]) : new ArrayList());
    }
}

