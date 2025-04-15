package de.redstonetechnik.bungeecoremadbryan.messagemanager;
import java.io.File;
import java.io.IOException;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class messagemanager {
    private File file;
    public Configuration cfg;
    
    public messagemanager() {
        file = new File(BungeeCore_MadBryan.instance.getDataFolder() + "/messages.yml");
    }

public void initConfig() {
	if (!file.exists()) {
		try {
			file.createNewFile();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	try {
		this.cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	//PLayerListener Mute
	this.cfg.set("PlayerListener.mute.Message.1.1", "§cDu bist permanent gemuted.");
	this.cfg.set("PlayerListener.mute.Message.1.2", "§r§lGrund§r: §c");
	this.cfg.set("PlayerListener.mute.Message.1.3", "§r§lGemuted von : §c");
	this.cfg.set("PlayerListener.mute.Message.1.4", "§7Ein EntMute Antrag kannst du jederzeit im Forum stellen oder auch im TS.");
	this.cfg.set("PlayerListener.mute.Message.1.5", "§r§lForum §r: §eforum.WarKing.de");
	this.cfg.set("PlayerListener.mute.Message.1.6", "§7Du bist bis zum ");
	this.cfg.set("PlayerListener.mute.Message.1.7", " gemuted.");
	//PlayerListener Ban
	this.cfg.set("PlayerListener.ban.Message.1.1", "§cDu wurdest permanent gebannt von §e");
	this.cfg.set("PlayerListener.ban.Message.1.2", "                                                           §r§lGrund§r: §c");
	this.cfg.set("PlayerListener.ban.Message.1.3", "                                        §7Stelle ein Entbanungs Antrag im Forum : §b");
	this.cfg.set("PlayerListener.ban.Message.1.4", "WarKing.de/forum");
	this.cfg.set("PlayerListener.ban.Message.1.5", "§7Du wurdest bis zum §e");
	this.cfg.set("PlayerListener.ban.Message.1.6", " §7gebannt von §e");
	this.cfg.set("PlayerListener.ban.Message.1.7", "                                                           §r§lGrund§r : §c");
	this.cfg.set("PlayerListener.ban.Message.1.8", "                                                 §7Stelle ein Entbanungs Antrag im Forum: ");
	this.cfg.set("PlayerListener.ban.Message.1.9", "§bWarKing.de/forum");
	this.cfg.set("PlayerListener.ban.Message.1.10", "§cMögliche Banumgehung durch §r");
	this.cfg.set("PlayerListener.ban.Message.1.11", "§cBanne Spieler wegen Banumgehung");
   //AdminHelp
	this.cfg.set("Commands.AdminHelpCommand.Message.1.1", "§7Mit §6/creategroup <GruppenName> §7kannst du Gruppen erstellen");
    this.cfg.set("Commands.AdminHelpCommand.Message.1.2", "§cGruppen Beispiel erstellung");
    this.cfg.set("Commands.AdminHelpCommand.Message.2.1", "§7Mit §6/deletegroup <GruppenName> §7kannst du Gruppen Löschen");
    this.cfg.set("Commands.AdminHelpCommand.Message.2.2", "§cGruppen Beispiel Löschung");
    this.cfg.set("Commands.AdminHelpCommand.Message.3.1", "§7Mit §6/setgroupban §7kannst du BannPower und BenBannPower setzen");
    this.cfg.set("Commands.AdminHelpCommand.Message.3.2", "§cBanPower setzen");
    this.cfg.set("Commands.AdminHelpCommand.Message.4.1", "§7Mit §6/setgroupmute §7kannst du MutePower und BenMutePower setzen");
    this.cfg.set("Commands.AdminHelpCommand.Message.4.2", "§cMutePower setzen");
    this.cfg.set("Commands.AdminHelpCommand.Message.5.1", "§7Mit §6/setgroupprefix §7kannst du Gruppen-Prefix setzen");
    this.cfg.set("Commands.AdminHelpCommand.Message.5.2", "§cGruppen-Prefix setzen");
    this.cfg.set("Commands.AdminHelpCommand.Message.6.1", "§7Mit §6/setgroupsuffix §7kannst du Gruppen-Suffix setzen");
    this.cfg.set("Commands.AdminHelpCommand.Message.6.2", "§cGruppen-Suffix setzen");
    this.cfg.set("Commands.AdminHelpCommand.Message.7.1", "§7Mit §6/checkgroup §7kannst du Gruppen-Infos ansehen wie BannPower");
    this.cfg.set("Commands.AdminHelpCommand.Message.7.2", "§cGruppen-Info Beispiel");
    this.cfg.set("Commands.AdminHelpCommand.Message.8.1", "§7Mit §6/setgroup <Spieler Gruppe> §7kannst du die Gruppe setzen");
    this.cfg.set("Commands.AdminHelpCommand.Message.8.2", "§cGruppe setzen eines Spielers");
    this.cfg.set("Commands.ClearChat.Message", "§7Der §cChat §7wurde geleert!");
    //Ban Befehl
    this.cfg.set("Commands.ban.Message.1.1", "§cDer Spieler existiert nicht.");
    this.cfg.set("Commands.ban.Message.1.2", "§aDu hast nicht genügend §cBanPower §aum die Person zu bannen.");
    this.cfg.set("Commands.ban.Message.1.3", "§cDu bist permanent gebannt worden von ");
    this.cfg.set("Commands.ban.Message.1.4", " §r§lGrund§r: §c");
    this.cfg.set("Commands.ban.Message.1.5", " Du bist bis zum ");
    this.cfg.set("Commands.ban.Message.1.6", " gebannt.");
    this.cfg.set("Commands.ban.Message.1.7", " §r§lGrund§r: §c");
    this.cfg.set("Commands.ban.Message.1.8", " §7Wenn dies ein Fehler ist melde dich im Forum ");
    this.cfg.set("Commands.ban.Message.1.9", "§7forum.WarKing.de gebannt von");
    this.cfg.set("Commands.ban.Message.1.10", "§cUngültige Zeitangabe.");
    this.cfg.set("Commands.ban.Message.1.11", "Du hast ");
    this.cfg.set("Commands.ban.Message.1.12", " gebannt. Grund: §c");
    //unban Befehl
    this.cfg.set("Commands.unban.Message.1.1", "/unban [Spieler] [Grund]");
    this.cfg.set("Commands.unban.Message.1.2", "§cDer Spieler existiert nicht.");
    this.cfg.set("Commands.unban.Message.1.3", "§eDer Spieler §b");
    this.cfg.set("Commands.unban.Message.1.4", " §ewurde entbannt");
    this.cfg.set("Commands.unban.Message.1.5", "§eDer Spieler §b");
    this.cfg.set("Commands.unban.Message.1.6", " §eist aktuell nicht gebannt.");
    this.cfg.set("Deny.Message", "Unbekannter Befehl.");
    //Mute
    this.cfg.set("Commands.mute.Message.1.1", "§cDer Spieler existiert nicht.");
    this.cfg.set("Commands.mute.Message.1.2", "§aDu hast nicht genügend §cMutePower §aum die Person zu muten.");
    this.cfg.set("Commands.mute.Message.1.3", "§cDu bist permanent gemuted. §r§lGrund§r: §c");
    this.cfg.set("Commands.mute.Message.1.4", " Du bist bis zum ");
    this.cfg.set("Commands.mute.Message.1.5", " gemuted. §r§lGrund: §c");
    this.cfg.set("Commands.mute.Message.1.6", "§cUngültige Zeitangabe.");
    this.cfg.set("Commands.mute.Message.1.7", "Du hast ");
    this.cfg.set("Commands.mute.Message.1.8", " gemuted. Grund: §c");
    //UnMute
    this.cfg.set("Commands.unmute.Message.1.1", "/unmute [Spieler] [Grund]");
    this.cfg.set("Commands.unmute.Message.1.2", "§cDer Spieler existiert nicht.");
    this.cfg.set("Commands.unmute.Message.1.3", "§eDer Spieler §b");
    this.cfg.set("Commands.unmute.Message.1.4", " §ewurde entmuted");
    this.cfg.set("Commands.unmute.Message.1.5", "§eDer Spieler §b");
    this.cfg.set("Commands.unmute.Message.1.6", " §eist aktuell nicht gemuted.");
    //Join
    this.cfg.set("Commands.join.Message.1.1", "§cDieser Spieler ist offline.");
    this.cfg.set("Commands.join.Message.1.2", "§cSei eins mit dir selbst!");
    this.cfg.set("Commands.join.Message.1.3", "§7Mit §r/join §8[§rSpieler§8] §7kannst du einem Spieler folgen §8(z.B. in eine Arena)§7.");
    //joinme
    this.cfg.set("Commands.joinme.Message.1.1", "§7Klicke §6hier§8, §7um zu §6");
    this.cfg.set("Commands.joinme.Message.1.2", " §7auf §6");
    this.cfg.set("Commands.joinme.Message.1.3", " §7zu §7kommen§8!");
    this.cfg.set("Commands.joinme.Message.1.4", "§aSpieler folgen");
    this.cfg.set("Commands.joinme.Message.1.5", "§6Info: §7");
    //creategroup
    this.cfg.set("Commands.creategroup.Message.1.1", "§7/creategroup §a[GruppenName]");
    this.cfg.set("Commands.creategroup.Message.1.2", "§7Die Gruppe §a");
    this.cfg.set("Commands.creategroup.Message.1.3", " §7wurde §aerstellt.");
    this.cfg.set("Commands.creategroup.Message.1.4", "§7Diese Gruppe existiert breits.");
    //deletegroup
    this.cfg.set("Commands.deletegroup.Message.1.1", "§7/deletegroup §a[GruppenName]");
    this.cfg.set("Commands.deletegroup.Message.1.2", "§7Diese Gruppe existiert nicht.");
    this.cfg.set("Commands.deletegroup.Message.1.3", "§7Die Gruppe §a");
    this.cfg.set("Commands.deletegroup.Message.1.4", " §7wurde §agelöscht.");
    //creategroup
    this.cfg.set("Commands.addgroupperm.Message.1.1", "§7/addgroupperm §a[GruppenName Permission ServerName]");
    this.cfg.set("Commands.addgroupperm.Message.1.2", "§7Die Permission §a");
    this.cfg.set("Commands.addgroupperm.Message.1.3", " §7wurde §ahinzugefügt.");
    this.cfg.set("Commands.addgroupperm.Message.1.4", "§7Diese Permission existiert breits.");
    //kick
    this.cfg.set("Commands.kick.Message.1.1", "/kick [Spieler] [Nachricht]");
    this.cfg.set("Commands.kick.Message.1.2", "§cDieser Spieler ist derzeit nicht online!");
    this.cfg.set("Commands.kick.Message.1.3", "§cDu wurdest gekickt.");
    this.cfg.set("Commands.kick.Message.1.4", "Der Spieler ");
    this.cfg.set("Commands.kick.Message.1.5", " wurde gekickt.");
    //setgroup
    this.cfg.set("Commands.setgroup.Message.1.1", "§7/setgroup §a[Spieler Gruppe]");
    this.cfg.set("Commands.setgroup.Message.1.2", "§7Der Spieler §a");
    this.cfg.set("Commands.setgroup.Message.1.3", " §4existiert nicht.");
    this.cfg.set("Commands.setgroup.Message.1.4", "§7Die Gruppe §a");
    this.cfg.set("Commands.setgroup.Message.1.5", " §4existiert nicht.");
    this.cfg.set("Commands.setgroup.Message.1.6", "§7Dem Spieler §a");
    this.cfg.set("Commands.setgroup.Message.1.7", " §7wurde die Gruppe §a");
    this.cfg.set("Commands.setgroup.Message.1.8", " §7gesetzt.");
    this.cfg.set("Commands.setgroup.Message.1.9", "§aDu hast jetzt den Rang ");
    this.cfg.set("Commands.setgroup.Message.1.10", "§a.");
    //setgroupban
    this.cfg.set("Commands.setgroupban.Message.1.1", "§7/setgroupban §a[GruppenName BanPower BenBanPower]");
    this.cfg.set("Commands.setgroupban.Message.1.2", "§7Die Gruppe §a");
    this.cfg.set("Commands.setgroupban.Message.1.3", " §4existiert nicht.");
    this.cfg.set("Commands.setgroupban.Message.1.4", "§7Bei der Gruppe §a");
    this.cfg.set("Commands.setgroupban.Message.1.5", " §7wurde die BanPower auf §a");
    this.cfg.set("Commands.setgroupban.Message.1.6", " §7gesetzt und die BenBanPower wurde auf §a");
    this.cfg.set("Commands.setgroupban.Message.1.7", " §7gesetzt");
    this.cfg.set("Commands.setgroupban.Message.1.8", "§7Bei der Gruppe §a");
    this.cfg.set("Commands.setgroupban.Message.1.9", " §7wurde die BanPower auf §a");
    this.cfg.set("Commands.setgroupban.Message.1.10", " §7gesetzt und die BenBanPower wurde auf §a");
    this.cfg.set("Commands.setgroupban.Message.1.11", " §7gesetzt");
    //setgroupmute
    this.cfg.set("Commands.setgroupmute.Message.1.1", "§7/setgroupmute §a[GruppenName MutePower BenMutePower]");
    this.cfg.set("Commands.setgroupmute.Message.1.2", "§7Die Gruppe §a");
    this.cfg.set("Commands.setgroupmute.Message.1.3", " §4existiert nicht.");
    this.cfg.set("Commands.setgroupmute.Message.1.4", "§7Bei der Gruppe §a");
    this.cfg.set("Commands.setgroupmute.Message.1.5", " §7wurde die MutePower auf §a");
    this.cfg.set("Commands.setgroupmute.Message.1.6", " §7gesetzt und die BenMutePower wurde auf §a");
    this.cfg.set("Commands.setgroupmute.Message.1.7", " §7gesetzt");
    this.cfg.set("Commands.setgroupmute.Message.1.8", "§7Bei der Gruppe §a");
    this.cfg.set("Commands.setgroupmute.Message.1.9", " §7wurde die MutePower auf §a");
    this.cfg.set("Commands.setgroupmute.Message.1.10", " §7gesetzt und die BenMutePower wurde auf §a");
    this.cfg.set("Commands.setgroupmute.Message.1.11", " §7gesetzt");
    //setgroupprefix
    this.cfg.set("Commands.setgroupprefix.Message.1.1", "§7/setgroupprefix §a[GruppenName Prefix]");
    this.cfg.set("Commands.setgroupprefix.Message.1.2", "§7Die Gruppe §a");
    this.cfg.set("Commands.setgroupprefix.Message.1.3", " §4existiert nicht.");
    this.cfg.set("Commands.setgroupprefix.Message.1.4", "§7Bei der Gruppe §a");
    this.cfg.set("Commands.setgroupprefix.Message.1.5", " §7wurde der Prefix ");
    this.cfg.set("Commands.setgroupprefix.Message.1.6", " §7gesetzt.");
    this.cfg.set("Commands.setgroupprefix.Message.1.7", "§7Bei der Gruppe §a");
    this.cfg.set("Commands.setgroupprefix.Message.1.8", " §7wurde der Prefix ");
    this.cfg.set("Commands.setgroupprefix.Message.1.9", " §7gesetzt.");
    //setgroupglobalmute
    this.cfg.set("Commands.setgroupglobalmute.Message.1.1", "§7/setgroupglobalmute §a[GruppenName true/false]");
    this.cfg.set("Commands.setgroupglobalmute.Message.1.2", "§7Die Gruppe §a");
    this.cfg.set("Commands.setgroupglobalmute.Message.1.3", " §4existiert nicht.");
    this.cfg.set("Commands.setgroupglobalmute.Message.1.4", "§7Bei der Gruppe §a");
    this.cfg.set("Commands.setgroupglobalmute.Message.1.5", " §7wurde der GlobalMute ");
    this.cfg.set("Commands.setgroupglobalmute.Message.1.6", " §7gesetzt.");
    this.cfg.set("Commands.setgroupglobalmute.Message.1.7", "§7Bei der Gruppe §a");
    this.cfg.set("Commands.setgroupglobalmute.Message.1.8", " §7wurde der GlobalMute ");
    this.cfg.set("Commands.setgroupglobalmute.Message.1.9", " §7gesetzt.");
        this.cfg.set("Commands.setgroupglobalmute.Message.1.10", " §7Bitte benutze true oder false zum gestlegen.");
    //setgroupsuffix
    this.cfg.set("Commands.setgroupsuffix.Message.1.1", "§7/setgroupprefix §a[GruppenName Suffix]");
    this.cfg.set("Commands.setgroupsuffix.Message.1.2", "§7Die Gruppe §a");
    this.cfg.set("Commands.setgroupsuffix.Message.1.3", " §4existiert nicht.");
    this.cfg.set("Commands.setgroupsuffix.Message.1.4", "§7Bei der Gruppe §a");
    this.cfg.set("Commands.setgroupsuffix.Message.1.5", " §7wurde der Suffix ");
    this.cfg.set("Commands.setgroupsuffix.Message.1.6", " §7gesetzt.");
    this.cfg.set("Commands.setgroupsuffix.Message.1.7", "§7Bei der Gruppe §a");
    this.cfg.set("Commands.setgroupsuffix.Message.1.8", " §7wurde der Suffix ");
    this.cfg.set("Commands.setgroupsuffix.Message.1.9", " §7gesetzt.");
    //msg
    this.cfg.set("Commands.msg.Message.1.1", "/msg [Benutzer] [Nachricht]");
    this.cfg.set("Commands.msg.Message.1.2", "§cDieser Spieler ist derzeit nicht online!");
    this.cfg.set("Commands.msg.Message.1.3", "§cDieser Spieler empfängt derzeit keine Chatnachrichten!");
    this.cfg.set("Commands.msg.Message.1.4", "§cNachrichten an dich selbst hast du wirklich nicht nötig!");
    this.cfg.set("Commands.msg.Message.1.5", "§cDieser Spieler möchte keine Nachrichten erhalten.");
    //r
    this.cfg.set("Commands.r.Message.1.1", "/r [Antwort]");
    this.cfg.set("Commands.r.Message.1.2", "§cDu hast bisher mit niemandem geschrieben!");
    this.cfg.set("Commands.r.Message.1.3", "§cDieser Spieler ist derzeit nicht online!");
    this.cfg.set("Commands.r.Message.1.4", "§cDieser Spieler empfängt derzeit keine Chatnachrichten!");
    //check
    this.cfg.set("Commands.check.Message.1.1", "/check [Spieler]");
    this.cfg.set("Commands.check.Message.1.2", "§cDer Spieler existiert nicht.");
    this.cfg.set("Commands.check.Message.1.3", "§e<§e§l===========_§bSpieler Info§e§l_===========§e>");
    this.cfg.set("Commands.check.Message.1.4", "§7Spieler : ");
    this.cfg.set("Commands.check.Message.1.5", "§7Spieler Name : ");
    this.cfg.set("Commands.check.Message.1.6", "§7Gebant : §a✔");
    this.cfg.set("Commands.check.Message.1.7", "§7Gebant : §4✘");
    this.cfg.set("Commands.check.Message.1.8", "§7Gebant bis : §9");
    this.cfg.set("Commands.check.Message.1.9", "§7Gebant bis : §9nicht gebannt");
    this.cfg.set("Commands.check.Message.2.9", "§7Entbant am : §4");
    this.cfg.set("Commands.check.Message.1.10", "§7Gebant von : §9");
    this.cfg.set("Commands.check.Message.1.11", "§7Grund des Bans : §9");
    this.cfg.set("Commands.check.Message.1.12", "§7Gebant von : §4✘");
    this.cfg.set("Commands.check.Message.2.12", "§7Entbant von : §4");
    this.cfg.set("Commands.check.Message.1.13", "§7Grund des Bans : §4✘");
    this.cfg.set("Commands.check.Message.2.13", "§7Grund des EntBans : §4");
    this.cfg.set("Commands.check.Message.1.14", "§7Gemuted : §a✔");
    this.cfg.set("Commands.check.Message.1.15", "§7Gemuted bis : §9Permanent");
    this.cfg.set("Commands.check.Message.1.16", "§7Gemuted bis : §9");
    this.cfg.set("Commands.check.Message.1.17", "§7Gemuted bis : §9nicht gemuted");
    this.cfg.set("Commands.check.Message.2.17", "§7Entmuted am : §4");
    this.cfg.set("Commands.check.Message.1.18", "§7Gemuted von : §9");
    this.cfg.set("Commands.check.Message.2.18", "§7Entmuted von : §4");
    this.cfg.set("Commands.check.Message.1.19", "§7Grund des Mutes : §9");
    this.cfg.set("Commands.check.Message.1.20", "§7Gemuted : §4✘");
    this.cfg.set("Commands.check.Message.1.21", "§7Grund des Mutes : §4✘");
    this.cfg.set("Commands.check.Message.2.21", "§7Grund des EntMutes : §4");
    //checkgroup
    this.cfg.set("Commands.checkgroup.Message.1.1", "/checkgroup [Gruppe]");
    this.cfg.set("Commands.checkgroup.Message.1.2", "§cDie Gruppe existiert nicht.");
    this.cfg.set("Commands.checkgroup.Message.1.3", "§e<§e§l===========_§bGruppen Info§e§l_===========§e>");
    this.cfg.set("Commands.checkgroup.Message.1.4", "§7Gruppen Name : ");
    this.cfg.set("Commands.checkgroup.Message.1.5", "§7Gruppen Prefix : ");
    this.cfg.set("Commands.checkgroup.Message.1.6", "§7Gruppen Suffix : ");
    this.cfg.set("Commands.checkgroup.Message.1.7", "§7Gruppen BanPower : ");
    this.cfg.set("Commands.checkgroup.Message.1.8", "§7Gruppen BenBanPower : ");
    this.cfg.set("Commands.checkgroup.Message.1.9", "§7Gruppen MutePower : ");
    this.cfg.set("Commands.checkgroup.Message.1.10", "§7Gruppen BenMutePower : ");
    //help
    this.cfg.set("Commands.help.Message.1.1", "§7Kehre von überall mit §6/l §7zur Lobby zurück!");
    this.cfg.set("Commands.help.Message.1.2", "§cZurück zur Lobby");
    this.cfg.set("Commands.help.Message.1.3", "/l");
    this.cfg.set("Commands.help.Message.2.1", "§7Komme mit §6/bauserver §7auf den Bauserver!");
    this.cfg.set("Commands.help.Message.2.2", "§cZum Bauserver");
    this.cfg.set("Commands.help.Message.2.3", "/bauserver");
    this.cfg.set("Commands.help.Message.3.1", "§7Verwalte Clans mit §6/clan §7!");
    this.cfg.set("Commands.help.Message.3.2", "§cZum Bauserver");
    this.cfg.set("Commands.help.Message.3.3", "/clan");
    this.cfg.set("Commands.help.Message.4.1", "§7Starte mit §6/fight §7einen neuen Kampf!");
    this.cfg.set("Commands.help.Message.4.2", "§cZum Kampfsystem");
    this.cfg.set("Commands.help.Message.4.3", "/fight");
    this.cfg.set("Commands.help.Message.5.1", "§7Trete mit §6/join [Spieler] §7einem Kampf bei!");
    this.cfg.set("Commands.help.Message.5.2", "§cSpieler nachjoinen");
    this.cfg.set("Commands.help.Message.5.3", "/join");
    this.cfg.set("Commands.help.Message.6.1", "§7Trete mit §6/friend  §7verwalte deine Freunde!");
    this.cfg.set("Commands.help.Message.6.2", "§cFreunde verwalten");
    this.cfg.set("Commands.help.Message.6.3", "/friend");
    this.cfg.set("Commands.help.Message.7.1", "§7Mit den Befehl §6/youtuber §7kannst du die Voraussetzungen sehen.");
    this.cfg.set("Commands.help.Message.7.2", "§cVoraussetzungen sehen für YouTuber.");
    this.cfg.set("Commands.help.Message.7.3", "/youtuber");
    this.cfg.set("Commands.help.Message.8.1", "§7Mit §6/bewerben  §7kannst du den Link aufrufen zu unserer Website");
    this.cfg.set("Commands.help.Message.8.2", "§cInfos zum Bewerben");
    this.cfg.set("Commands.help.Message.8.3", "/bewerben");
    //bewerben
    this.cfg.set("Commands.bewerben.Info", "Wenn nur ein l drinne steht ist es ein platzhalter und wird nicht im chat ausgegeben.");
    this.cfg.set("Commands.bewerben.Message.1.1", "§7Du kannst dich hier bei uns Bewerben in unserem Forum §6Link");
    this.cfg.set("Commands.bewerben.Message.1.2", "l");
    this.cfg.set("Commands.bewerben.Message.1.3", "l");
    this.cfg.set("Commands.bewerben.Message.1.4", "l");
    this.cfg.set("Commands.bewerben.Message.1.5", "l");
    this.cfg.set("Commands.bewerben.Message.1.6", "l");
    this.cfg.set("Commands.bewerben.Message.1.7", "l");
    //youtuber
    this.cfg.set("Commands.youtuber.Message.1.1", "§7Du kannst dich hier bei uns Bewerben in unserem Forum §6Link  §7als Youtuber");
    this.cfg.set("Commands.youtuber.Message.1.2", "§6Voraussetzungen für den YouTuber Rang: ");
    this.cfg.set("Commands.youtuber.Message.1.3", "§7Abonnenten min. : §6xxx");
    this.cfg.set("Commands.youtuber.Message.1.4", "§7Videos Veröffentlicht : §6xxx");
    this.cfg.set("Commands.youtuber.Message.1.5", "§7Aufrufe im Schnitt : §6xxx");
    this.cfg.set("Commands.youtuber.Message.1.6", "§7");
    this.cfg.set("Commands.youtuber.Message.1.7", "§7");
    //teamhelp
    this.cfg.set("Commands.teamhelp.Message.1.1", "§7Mit §6/ban §7kannst du spieler bannen und mit §6/unban §7entbannen");
    this.cfg.set("Commands.teamhelp.Message.1.2", "§cBannen");
    this.cfg.set("Commands.teamhelp.Message.1.3", "/ban");
    this.cfg.set("Commands.teamhelp.Message.2.1", "§7Mit §6/check [player] §7Kannst du die Akte abfragen von den Spieler");
    this.cfg.set("Commands.teamhelp.Message.2.2", "§cAkte Beispiel");
    this.cfg.set("Commands.teamhelp.Message.2.3", "/check ");
    this.cfg.set("Commands.teamhelp.Message.3.1", "§7Mit §6/mute §7kannst du einen Spieler aus den Chat verbannen. Mit §6/unmute §7entbannnen");
    this.cfg.set("Commands.teamhelp.Message.3.2", "§cMute System");
    this.cfg.set("Commands.teamhelp.Message.3.3", "/mute");
    this.cfg.set("Commands.teamhelp.Message.4.1", "§7Trete mit §6/join [Spieler] §7einem Kampf bei!");
    this.cfg.set("Commands.teamhelp.Message.4.2", "§cSpieler nachjoinen");
    this.cfg.set("Commands.teamhelp.Message.4.3", "/join");
    //timeformat ban/mute
    this.cfg.set("timeformat.years", " Jahre ");
    this.cfg.set("timeformat.year", " Jahr ");
    this.cfg.set("timeformat.days", " Tage ");
    this.cfg.set("timeformat.day", " Tag ");
    this.cfg.set("timeformat.hours", " Stunden ");
    this.cfg.set("timeformat.hour", " Stunde ");
    this.cfg.set("timeformat.minutes", " Minuten ");
    this.cfg.set("timeformat.minute", " Minute ");
    this.cfg.set("timeformat.seconds", " Sekunden ");
    this.cfg.set("timeformat.second", " Sekunde ");
    this.cfg.set("timeformat.permanent", "PERMANENT");
    
    try {
		ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.cfg, file);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}else {
        try {
			cfg = YamlConfiguration.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
}
