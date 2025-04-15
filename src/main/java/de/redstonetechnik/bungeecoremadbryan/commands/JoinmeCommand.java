package de.redstonetechnik.bungeecoremadbryan.commands;

import java.util.ArrayList;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class JoinmeCommand extends Command {
    public JoinmeCommand() {
        super("join", "", new String[]{"joinme"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            if (player.hasPermission("bungeecore.joinme")) {
                TextComponent tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.joinme.Message.1.1") + player.getName() + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.joinme.Message.1.2") + player.getServer().getInfo().getName() + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.joinme.Message.1.3"));
                tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.joinme.Message.1.4"))).create()));
                tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/join " + player.getName()));
                ProxyServer.getInstance().broadcast(tc);
                StringBuilder msgBuilder = new StringBuilder();
                if(args.length >= 1) {
                msgBuilder.append(BungeeCore_MadBryan.ChatPrefix).append(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.joinme.Message.1.5"));

                for(int i = 0; i < args.length; ++i) {
                    msgBuilder.append(args[i]).append(" ");
                }
                if(player.hasPermission("bungeecore.joinme.message")) {
                	ProxyServer.getInstance().broadcast(msgBuilder.toString());
                }
                }
            }
        }

    }

    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {
        return (Iterable)(args.length == 1 ? this.allPlayers(args[0]) : new ArrayList());
    }
}
