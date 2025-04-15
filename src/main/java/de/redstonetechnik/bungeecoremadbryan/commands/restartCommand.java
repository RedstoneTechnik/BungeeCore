package de.redstonetechnik.bungeecoremadbryan.commands;
import java.util.concurrent.TimeUnit;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class restartCommand extends Command {
    public restartCommand() {
        super("restartnetwork", "bungeecore.restart", new String[0]);
    }
	 public void execute(CommandSender sender, String[] args) {
	  
	  if(sender != null) {
		  BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + "§7Der Spieler §e" + sender.getName() + " §7hat einen Neustart des Netzwerks ausgelöst");
		  
			 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §ceiner Minute §7neu.");
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
			  
			  
			 @Override
			 public void run() {
				 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c30 Sekunden §7neu.");
			  
			 }
			 
			 }, 30, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
			  
			  
			 @Override
			 public void run() {
				 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c10 Sekunden §7neu.");
			  
			 }
			 
			 }, 50, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
			  
			  
			 @Override
			 public void run() {
				 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c5 Sekunden §7neu.");
			  
			 }
			 
			 }, 55, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
			 
				 
		     @Override
			 public void run() {
				 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c4 Sekunden §7neu.");
			  
			 }
			 
			 }, 56, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
				 @Override
				 public void run() {
					 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c3 Sekunden §7neu.");
				  
				 }
				 
			 }, 57, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
				 @Override
				 public void run() {
					 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c2 Sekunden §7neu.");
				  
				 }
				 
			 }, 58, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
				 @Override
				 public void run() {
					 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c1 Sekunden §7neu.");
				  
				 }
				 
			 }, 59, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
			 
			 
			 @Override
			 public void run() {
			        for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
			        	p.disconnect(BungeeCore_MadBryan.ChatPrefix + "§7Das Netzwerk startet jetzt neu bitte habt einen Moment Geduld.");
			          }
			 }
			 
			 }, 60, TimeUnit.SECONDS);
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
				 
				 
			 @Override
			 public void run() {
		ProxyServer.getInstance().stop();
			 }
			 
			 }, 61, TimeUnit.SECONDS);
	  }else {
		  BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + "§7Der Server §e" + " §7hat einen Neustart des Netzwerks ausgelöst");
		  
			 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §ceiner Minute §7neu.");
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
			  
			  
			 @Override
			 public void run() {
				 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c30 Sekunden §7neu.");
			  
			 }
			 
			 }, 30, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
			  
			  
			 @Override
			 public void run() {
				 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c10 Sekunden §7neu.");
			  
			 }
			 
			 }, 50, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
			  
			  
			 @Override
			 public void run() {
				 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c5 Sekunden §7neu.");
			  
			 }
			 
			 }, 55, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
			 
				 
		     @Override
			 public void run() {
				 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c4 Sekunden §7neu.");
			  
			 }
			 
			 }, 56, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
				 @Override
				 public void run() {
					 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c3 Sekunden §7neu.");
				  
				 }
				 
			 }, 57, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
				 @Override
				 public void run() {
					 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c2 Sekunden §7neu.");
				  
				 }
				 
			 }, 58, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
				 @Override
				 public void run() {
					 BungeeCord.getInstance().broadcast(BungeeCore_MadBryan.ChatPrefix + " §7startet in §c1 Sekunden §7neu.");
				  
				 }
				 
			 }, 59, TimeUnit.SECONDS);
			 
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
			 
			 
			 @Override
			 public void run() {
			        for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
			        	p.disconnect(BungeeCore_MadBryan.ChatPrefix + "§7Das Netzwerk startet jetzt neu bitte habt einen Moment Geduld.");
			          }
			 }
			 
			 }, 60, TimeUnit.SECONDS);
			 ProxyServer.getInstance().getScheduler().schedule(BungeeCore_MadBryan.instance, new Runnable() {
				 
				 
			 @Override
			 public void run() {
		ProxyServer.getInstance().stop();
			 }
			 
			 }, 61, TimeUnit.SECONDS);
	  }
	 }
	 }

