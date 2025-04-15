package de.redstonetechnik.bungeecoremadbryan.commands;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;

public class YouTuberCommand extends Command {
    public YouTuberCommand() {
        super("youtuber", "");
    }

    public void execute(CommandSender sender, String[] args) {
        if (args.length != 1) {
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.1") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.1"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.2") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.2"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.3") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.3"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.4") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.4"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.5") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.5"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.6") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.6"));
            }
            if(BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.7") == null){

            }else{
                sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.get("Commands.youtuber.Message.1.7"));
            }
        } else {
            args[0].getClass();
        }

    }
}
