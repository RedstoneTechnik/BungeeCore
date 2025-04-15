package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingUser;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;

public class AdminHelpCommand extends Command {
    public AdminHelpCommand() {
        super("adminhelp", "bungeecore.admin", new String[]{"am"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
    		WarkingUser target1 = new WarkingUser(sender.getName());
    		WarkingGroup group = new WarkingGroup(target1.UserGroup);
        	TextComponent tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + "§7Kehre von überall mit §6/l §7zur Lobby zurück!");
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.1.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.1.2").toString())).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/creategroup Test"));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.2.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.2.2").toString())).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/deletegroup Test"));
            sender.sendMessage(tc);
        	tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.3.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.3.2").toString())).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/setgroupban"));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.4.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.4.2").toString())).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/setgroupmute"));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.5.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.5.2").toString())).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/setgroupprefix"));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.6.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.6.2").toString())).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/setgroupsuffix"));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.7.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.7.2").toString())).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/checkgroup " + group.getGroupName()));
            sender.sendMessage(tc);
            tc = new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.8.1"));
            tc.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.AdminHelpCommand.Message.8.2").toString())).create()));
            tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/setgroup " + sender.getName() + " " + group.getGroupName()));
            sender.sendMessage(tc);
        } else {
            args[0].getClass();
        }

    }
}
