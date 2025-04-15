package de.redstonetechnik.bungeecoremadbryan.commands;

import java.util.ArrayList;
import java.util.HashMap;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.api.WarShipPlayer;
import de.redstonetechnik.bungeecoremadbryan.api.WarShipTeam;
import de.redstonetechnik.bungeecoremadbryan.api.WarShipTeam.TeamRang;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class teammsgCommand extends Command {
    protected static final HashMap<String, ProxiedPlayer> lastChats = new HashMap();

    public teammsgCommand() {
        super("cgchat", "", new String[]{"clanglobalchat"});
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            WarShipPlayer wsplayer = WarShipPlayer.getByUniqueId(player.getUniqueId().toString());
            ArrayList<String> warshipteam;
            warshipteam = new ArrayList();
            if(warshipteam.isEmpty()) {
            if(wsplayer.getTeam() == null) {
            	
            }else {
            warshipteam.add(wsplayer.getTeam().getName());
            }
            }
            ArrayList<String> teamrang;
            teamrang = new ArrayList();
            if(teamrang.isEmpty()) {
            if(wsplayer.getRang() == null) {
            	
            }else {
                if(wsplayer.getRang().name() == TeamRang.LEADER.name()) {
                    teamrang.add("§4Team-Leitung");	
                    }
                if(wsplayer.getRang().name() == TeamRang.ADMIN.name()) {
                    teamrang.add("§cAdmin");	
                    }
                
                if(wsplayer.getRang().name() == TeamRang.DESIGNER.name()) {
                    teamrang.add("§aDesigner");	
                    }
                
                if(wsplayer.getRang().name() == TeamRang.TECHNIKER.name()) {
                    teamrang.add("§bTechniker");	
                    }
                
                if(wsplayer.getRang().name() == TeamRang.FIGHTER.name()) {
                    teamrang.add("§9Fighter");	
                    }
                
                if(wsplayer.getRang().name() == TeamRang.MEMBER.name()) {
                    teamrang.add("§7Member");	
                    }
                
            }
            }
            if (args.length == 0) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + "/cgchat [Message]");
                return;
            }
            if (warshipteam.isEmpty()) {
            	player.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§7Du musst dafür in einem Clan sein.");
    		}else{
                StringBuilder msgBuilder = new StringBuilder();
                if(args.length >= 0) {
                msgBuilder.append("§6Clan §7| " + teamrang.toString().replace("[", "").replace("]", "") + " §e" + player.getName() + " §7: ");

                for(int i = 0; i < args.length; ++i) {
                    msgBuilder.append(args[i]).append(" ");
                }
                	WarShipTeam.getWarShipTeam(wsplayer.getTeam().getId()).broadcast(msgBuilder.toString());
                }
    		}
        }

    }

    public Iterable<String> onTabComplete(CommandSender commandSender, String[] args) {
        return (Iterable)(args.length == 1 ? this.allPlayers(args[0]) : new ArrayList());
    }
}

