package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer.ChatMode;

public class RCommand extends Command {
    public RCommand() {
        super("r", "", new String[]{"reply"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            if (args.length == 0) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.r.Message.1.1"));
                return;
            }

            ProxiedPlayer target = (ProxiedPlayer)MsgCommand.lastChats.get(player.getName().toLowerCase());
            if (target == null) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.r.Message.1.2"));
                return;
            }

            if (!target.isConnected()) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.r.Message.1.3"));
                return;
            }

            if (target.getChatMode() != ChatMode.SHOWN) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.r.Message.1.4"));
                return;
            }

            StringBuilder msgBuilder = new StringBuilder();
            msgBuilder.append("§5").append(player.getName()).append("§8>§5").append(target.getName()).append(" §r§7");
            String[] var6 = args;
            int var7 = args.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                String arg = var6[var8];
                msgBuilder.append(arg).append(" ");
            }

            String msg = msgBuilder.toString();
            player.sendMessage(msg);
            target.sendMessage(msg);
            BungeeCore_MadBryan.log(msg);
            MsgCommand.lastChats.put(target.getName().toLowerCase(), player);
        }

    }
}
