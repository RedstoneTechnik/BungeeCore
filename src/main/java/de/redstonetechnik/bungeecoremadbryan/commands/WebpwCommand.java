package de.redstonetechnik.bungeecoremadbryan.commands;


import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class WebpwCommand extends Command {
    public WebpwCommand() {
        super("webpw", "", new String[0]);
    }

    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer)sender;
            if (args.length != 2) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + "/webpw [Passwort] [Passwort wiederholen]");
                return;
            }

            if (!args[0].equals(args[1])) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§cDie Passwörter stimmen nicht überein!");
                return;
            }

            if (args[0].length() < 6) {
                player.sendMessage(BungeeCore_MadBryan.ChatPrefix + "§cDas Passwort muss mindestens 6 Zeichen lang sein!");
                return;
            }

//            sql.setWebpw(new WarkingUser(player), args[0]);
            player.sendMessage(BungeeCore_MadBryan.ChatPrefix + "Webpasswort gesetzt!");
        }

    }
}