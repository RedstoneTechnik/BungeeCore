package de.redstonetechnik.bungeecoremadbryan;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.GroupLayout.Group;

import de.redstonetechnik.bungeecoremadbryan.api.TimeHelper;
import de.redstonetechnik.bungeecoremadbryan.sql.BannedUserIPs;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingGroup;
import de.redstonetechnik.bungeecoremadbryan.sql.WarkingUser;
import de.redstonetechnik.bungeecoremadbryan.sql.serverstatus;
import net.md_5.bungee.api.AbstractReconnectHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.HoverEvent.Action;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer.ChatMode;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerListener implements Listener {
    public PlayerListener() {
    }

    
    
    @EventHandler
    public void onLogin(LoginEvent event) {
        WarkingUser user = new WarkingUser(event.getConnection().getUniqueId());
        if(user.UserName == null) {
            user.usercreate(event.getConnection().getUniqueId(), event.getConnection().getName());
        }else {
        }
        if(user.UserGroup == 0) {
        	PlayerListener.setgroup(event.getConnection().getUniqueId(), BungeeCore_MadBryan.instance.config.getInt("Default.Group"));
        }else {
        	
        }
        if(user.UserName == event.getConnection().getName().toString()) {
        	
        }else {
        	user.updateUserName(event.getConnection().getName(), event.getConnection().getUniqueId());
        }
        if (user.isBanned()) {
            user.updateBanIP(event.getConnection().getAddress().getAddress().getHostAddress());
            event.setCancelled(true);
            if (user.BanTime.before(Timestamp.valueOf(LocalDateTime.now()))) {
                event.setCancelReason(new BaseComponent[]{new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.1") + user.Banner + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.2") + user.BanReason + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.3") + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.4"))});
            } else {
                event.setCancelReason(new BaseComponent[]{new TextComponent(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.5") + user.BanTime.toLocalDateTime().format(TimeHelper.formatter) + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.6") + user.Banner + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.7") + user.BanReason + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.8") + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.9"))});
            }

        } else {
            List<BannedUserIPs> ips = BannedUserIPs.get(event.getConnection().getAddress().getAddress().getHostAddress());
            if (ips.size() > 0) {
                StringBuilder potentialBan = new StringBuilder();
                potentialBan.append(BungeeCore_MadBryan.ChatPrefix);
                potentialBan.append(BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.10"));
                potentialBan.append(user.UserName);
                potentialBan.append("§c:");
                Iterator var5 = ips.iterator();

                while(var5.hasNext()) {
                    BannedUserIPs banned = (BannedUserIPs)var5.next();
                    WarkingUser bannedUser = new WarkingUser(banned.getUserID());
                    long playerTime = bannedUser.BanTime.getTime();
                    String formattedPlaytime = convertTime(playerTime);
                    potentialBan.append(" §6");
                    potentialBan.append(formattedPlaytime);
                    potentialBan.append(" §c");
                    potentialBan.append(bannedUser.UserName);
                }

                TextComponent msg = new TextComponent(potentialBan.toString());
                msg.setHoverEvent(new HoverEvent(Action.SHOW_TEXT, (new ComponentBuilder(BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.ban.Message.1.11"))).create()));
                msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ban " + user.UserName + " perma Bannumgehung"));
                Iterator var9 = ProxyServer.getInstance().getPlayers().iterator();

                while(true) {
                    ProxiedPlayer target;
                    do {
                        do {
                            if (!var9.hasNext()) {
                                return;
                            }

                            target = (ProxiedPlayer)var9.next();
                        } while(!target.hasPermission("bungeecore.teamchat"));
                    } while(target.getChatMode() != ChatMode.COMMANDS_ONLY && target.getChatMode() != ChatMode.SHOWN);

                    target.sendMessage(msg);
                }
            }
        }
    }
    @EventHandler
    public void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        String servername = BungeeCore_MadBryan.instance.config.getString("ServerName");
        setPermission(player, servername);
        Map<String, String> modlist = player.getModList();
        Iterator var8 = modlist.keySet().iterator();

        while(var8.hasNext()) {
            String key = (String)var8.next();
            System.out.println(key + " > " + (String)modlist.get(key));
        }

        if (player.isForgeUser()) {
            System.out.println("Forge user!");
            if(player.getModList().containsValue("OptiFine")){

            }else if (player.hasPermission("bungeecore.mods")) {
                }else{
                    player.disconnect(BungeeCore_MadBryan.ChatPrefix + " §aBitte joine ohne Mods MfG das Server-Team.");
                }
        }
      }
    
	public static void setgroup(UUID player, int id){
		BungeeCore_MadBryan.instance.sql.update("UPDATE userdata Set UserGroup='" + id + "' WHERE UUID='" + player.toString() + "'");
	}

    public static void setPermission(ProxiedPlayer player, String servername) {
        WarkingUser user = new WarkingUser(player.getUniqueId());
        WarkingGroup group = new WarkingGroup(user.UserGroup);
        serverstatus permissions = new serverstatus(group.getGroupId());
        ArrayList<String> perms = permissions.getGroupServerPermissions(servername, group.getGroupId());
        for(String perm : perms) {
            player.setPermission(perm, true);
            BungeeCore_MadBryan.log(BungeeCore_MadBryan.ChatPrefix + "Diese Permission wurde gesetzt true oder false: " + perm + " :" + player.hasPermission(perm));
        }
        BungeeCore_MadBryan.log(BungeeCore_MadBryan.ChatPrefix + "Permissions die Gesetzt werden muessen: " + perms);
    }
    
    @EventHandler
    public void onServerKickEvent(ServerKickEvent ev) {
        ServerInfo kickedFrom;
        if (ev.getPlayer().getServer() != null) {
            kickedFrom = ev.getPlayer().getServer().getInfo();
        } else if (ProxyServer.getInstance().getReconnectHandler() != null) {
            kickedFrom = ProxyServer.getInstance().getReconnectHandler().getServer(ev.getPlayer());
        } else {
            kickedFrom = AbstractReconnectHandler.getForcedHost(ev.getPlayer().getPendingConnection());
            if (kickedFrom == null) {
                kickedFrom = ProxyServer.getInstance().getServerInfo(ev.getPlayer().getPendingConnection().getListener().getDefaultServer());
            }
        }

        ServerInfo kickTo = ProxyServer.getInstance().getServerInfo(BungeeCore_MadBryan.LobbyServer);
        if (kickedFrom == null || !kickedFrom.equals(kickTo)) {
            ev.setCancelled(true);
            ev.setCancelServer(kickTo);
        }
    }

//    @EventHandler
//    public void onServerSwitchEvent(ServerSwitchEvent e) {
//        ProxiedPlayer p = e.getPlayer();
//        Arena a = ArenaSystem.getArena(p);
//        if (a != null && !p.getServer().getInfo().equals(a.server)) {
//            a.removePlayer(p);
//        }
//
//    }

    @EventHandler
    public void onChatEvent(ChatEvent e) {
        if (e.isCommand()) {
            String[] command = e.getMessage().split(" ");
            ProxiedPlayer sender;
            if (command[0].contains(":")) {
                e.setCancelled(true);
                if (e.getSender() instanceof ProxiedPlayer) {
                    sender = (ProxiedPlayer)e.getSender();
                    sender.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("Deny.Message"));
                }
            }
        }

    }
    
    @EventHandler
    public void onChat(ChatEvent event) {
        WarkingUser user = new WarkingUser(event.getSender().toString());
        ProxiedPlayer p = (ProxiedPlayer)event.getSender();	
            if (user.isMuted()) {
            if (!event.getMessage().startsWith("/")) {
        	user.updateMuteIP(event.getSender().toString());
            event.setCancelled(true);
            if (user.MuteTime.before(WarkingUser.PermaMute)) {
            	p.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.1"));
                p.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.2") + user.MuteReason);
                p.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.3") + user.Muter);
            	p.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.4"));
            	p.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.5"));
                event.setCancelled(true);
            }else {
            	p.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.6") + user.MuteTime.toLocalDateTime().format(BungeeCore_MadBryan.DateFormat) + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.7"));
            	p.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.2") + user.MuteReason);
            	p.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.3") + user.Muter);
            	p.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.4"));
                p.sendMessage(BungeeCore_MadBryan.ChatPrefix + BungeeCore_MadBryan.instance.configMessage.cfg.getString("PlayerListener.mute.Message.1.5"));
                event.setCancelled(true);
            }
                event.setMessage(BungeeCore_MadBryan.ChatPrefix + "");
                event.setCancelled(true);
        }else {
        	if (user.isMuted() == user.MuteTime.equals(null)) {
        		p.sendMessage(BungeeCore_MadBryan.ChatPrefix + "Du hast");
        		event.setCancelled(true);
        		
        	}
        }
    }
    }

    @EventHandler
    public void onTabCompleteEvent(TabCompleteEvent e) {
        List<String> suggestions = e.getSuggestions();
        String[] cursor = e.getCursor().split(" ");
        String last = cursor[cursor.length - 1];
        Iterator var5 = ProxyServer.getInstance().getPlayers().iterator();

        while(true) {
            String cmd;
            do {
                if (!var5.hasNext()) {
                    if (e.getSender() instanceof ProxiedPlayer && cursor.length == 1 && cursor[0].startsWith("/")) {
                        ProxiedPlayer player = (ProxiedPlayer)e.getSender();
                        Iterator var9 = BungeeCore_MadBryan.commands.keySet().iterator();

                        while(var9.hasNext()) {
                            cmd = (String)var9.next();
                            if (cmd.startsWith(cursor[0]) && player.hasPermission((String)BungeeCore_MadBryan.commands.get(cmd))) {
                                suggestions.add(cmd);
                            }
                        }
                    }

                    return;
                }

                ProxiedPlayer player = (ProxiedPlayer)var5.next();
                cmd = player.getName();
            } while(!last.isEmpty() && !cmd.startsWith(last));

            suggestions.add(cmd);
        }
    }

    @EventHandler
    public void onTabCompleteResponseEvent(TabCompleteResponseEvent e) {
        List<String> suggestions = e.getSuggestions();

        for(int i = 0; i < suggestions.size(); ++i) {
            String suggestion = (String)suggestions.get(i);
            if (suggestion.startsWith("/") && suggestion.contains(":")) {
                suggestions.remove(suggestion);
                --i;
            }
        }

    }
    private String convertTime(long secondsx) {
        int days = (int) TimeUnit.MINUTES.toDays(secondsx);
        int hours = (int) (TimeUnit.MINUTES.toHours(secondsx) - TimeUnit.DAYS.toHours(days));
        int minutes = (int) (TimeUnit.MINUTES.toMinutes(secondsx) - TimeUnit.HOURS.toMinutes(hours) - TimeUnit.DAYS.toMinutes(days));

        if (days != 0) {
            return days + " Tage, " + hours + " Stunden, " + minutes + " Minuten";
        } else {
            if (hours != 0) {
                return hours + " Stunden, " + minutes + " Minuten";
            } else {
                return minutes + " Minuten";
            }
        }

    }
}

