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

public class BewerbenCommand extends Command {
    public BewerbenCommand() {
        super("bewerben", "");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.1") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.1"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.2") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.2"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.3") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.3"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.4") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.4"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.5") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.5"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.6") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.6"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.7") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.bewerben.Message.1.7"));
            }
        } else {
            args[0].getClass();
        }

    }
}
