package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class TeamHelpCommand extends Command {
    public TeamHelpCommand() {
        super("teamhelp", "bungeecore.teamchat", new String[]{"tm"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
        	TextComponent tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + "§7Kehre von überall mit §6/l §7zur Lobby zurück!");
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.1.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.1.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.1.3")));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.2.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.2.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.2.3") + sender.getName()));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.3.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.3.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.3.3")));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.4.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.4.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.teamhelp.Message.4.3")));
            sender.sendMessage(tc);
        } else {
            args[0].getClass();
        }

    }
}
