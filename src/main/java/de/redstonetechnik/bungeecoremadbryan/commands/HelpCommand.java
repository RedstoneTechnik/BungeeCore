package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help", "", new String[]{"?"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            TextComponent tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.1.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.1.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.1.3")));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.2.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.2.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.2.3")));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.3.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.3.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.3.3")));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.4.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.4.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.4.3")));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.5.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.5.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.5.3")));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.6.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.6.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.6.3")));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.7.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.7.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.7.3")));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.8.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.8.2"))).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, BungeeCore_MadBryan.instance.configMessage.cfg.getString("Commands.help.Message.8.3")));
            sender.sendMessage(tc);
        } else {
            args[0].getClass();
        }

    }
}
