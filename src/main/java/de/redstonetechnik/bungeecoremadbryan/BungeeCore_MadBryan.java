package de.redstonetechnik.bungeecoremadbryan;

import de.redstonetechnik.bungeecoremadbryan.commands.*;
import net.md_5.bungee.api.plugin.Plugin;
import de.redstonetechnik.bungeecoremadbryan.messagemanager.messagemanager;
import de.redstonetechnik.bungeecoremadbryan.sql.*;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Command;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public final class BungeeCore_MadBryan extends Plugin {
    public Configuration config;
    public static BungeeCore_MadBryan instance;
    public static String ChatPrefix;
    public messagemanager configMessage;
    public static final String TeamchatPrefix = "§4T§7c§r §e";
    public static final DateTimeFormatter DateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static String LobbyServer;
    public static String[] BroadCastMsgs;
    public static int LastBroadCast = 0;
    public static sql sql;
    public static final HashMap<String, String> serverPermissions = new HashMap();
    public static final HashMap<String, String> commands = new HashMap();

    public BungeeCore_MadBryan() {
    }
    @SuppressWarnings({ "deprecation", "deprecation" })

    @Override
    public void onEnable() {
        instance = this;

        new UpdateChecker(this, 114853).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version) == true) {
                this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + "Es gibt aktuell noch kein Update.");
            } else {
                this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + "Es gibt ein Update schaue auf SpigotMC vorbei.");
                this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + "https://www.spigotmc.org/resources/bungeecore-system.114853/");
                this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + "Plugin Version:" + this.getDescription().getVersion().toString() + " SpigotMC Version: " + version.toString());
            }
        });
        try {
            if (!this.getDataFolder().exists()) {
                this.getDataFolder().mkdir();
            }
            File configFile = new File(this.getDataFolder().getPath(), "config.yml");
            if (!configFile.exists()) {
                configFile.createNewFile();
                this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
                this.config.set("prefix", "§6War§7King» §r ");
                this.config.set("ServerName", "Bungee");
                this.config.set("lobbyserver", "Lobby");
                this.config.set("db.url", "jdbc:mysql://127.0.0.1:3306/db_2847664_1");
                this.config.set("db.weburl", "jdbc:mysql://127.0.0.1:3306/db_2847664_1");
                this.config.set("db.username", "redstonetechnik");
                this.config.set("db.password", "1509#Ingo");
                this.config.set("Default.Group", 3);
                this.config.set("System.Broadcast", false);
                this.config.set("System.BanSystem", true);
                this.config.set("System.MuteSystem", true);
                this.config.set("System.Joinme", true);
                this.config.set("System.Join", true);
                this.config.set("System.Clan", true);
                this.config.set("WartungsModus.Status", true);
                String[] cmd = new String[] { "l", "hub", "lobby" };
                this.config.set("servers.HunjyLobby.commands", cmd);
                String[] bcs = new String[] { "Standardmitteilung!" };
                this.config.set("broadcasts", bcs);
                this.config.set("servers.HunjyLobby.permission", "bungeecore.server.user");
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(this.config, configFile);
            }

            this.config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
        } catch (IOException var7) {
            System.err.println("Error not create/load config.yml, Aborting!");
            return;
        }
        ChatPrefix = this.config.getString("prefix");
        LobbyServer = this.config.getString("lobbyserver");
        configMessage = new messagemanager();
        configMessage.initConfig();
        this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + " §aMessages config loaded");
        sql.connect(this.config.getString("db.url"), this.config.getString("db.weburl"), this.config.getString("db.username"), this.config.getString("db.password"));
        this.getProxy().getPluginManager().registerListener(this, new PlayerListener());
        sql.update("CREATE TABLE IF NOT EXISTS permisson(ID INT(16) PRIMARY KEY AUTO_INCREMENT NOT NULL, GroupSortID VARCHAR(64),serverid VARCHAR(64));");
        sql.update("CREATE TABLE IF NOT EXISTS userdata(ID INT(16) PRIMARY KEY AUTO_INCREMENT NOT NULL,UUID VARCHAR(64), UserName VARCHAR(64), UserGroup INT(64),UnBanTime datetime, BanTime datetime, BanReason VARCHAR(64),UnBanReason VARCHAR(64) , MuteReason VARCHAR(64), UnMuteReason VARCHAR(64), MuteTime datetime, UnMuteTime datetime, Banner VARCHAR(64), Muter VARCHAR(64), UnBanner VARCHAR(64), UnMuter VARCHAR(64), UserSuffix VARCHAR(64), Coins INT(18), time INT(16));");
        sql.update("CREATE TABLE IF NOT EXISTS serverstatus(ID INT(16) PRIMARY KEY AUTO_INCREMENT NOT NULL,serverid VARCHAR(64), GroupSortID VARCHAR(64), PermissionName VARCHAR(1000), PermissionValue VARCHAR(64), MuteReason VARCHAR(64), MuteTime datetime, Muter VARCHAR(64));");
        sql.update("CREATE TABLE IF NOT EXISTS usergroups(ID INT(16) PRIMARY KEY AUTO_INCREMENT NOT NULL,GroupName VARCHAR(64), GroupPrefix VARCHAR(64), GroupSuffix VARCHAR(64), GlobalMute VARCHAR(64), BannPower INT(11), BenBannPower INT(11), MutePower INT(11), BenMutePower INT(11));");
        sql.update("CREATE TABLE IF NOT EXISTS banneduserips(UserID INT(11),Timestamp TIMESTAMP, IP VARCHAR(16));");
        sql.update("CREATE TABLE IF NOT EXISTS muteuserips(UserID INT(11),Timestamp TIMESTAMP, IP VARCHAR(16));");
        Configuration servers = this.config.getSection("servers");
        Iterator var9 = servers.getKeys().iterator();

        while (var9.hasNext()) {
            String serverName = (String) var9.next();
            Configuration server = servers.getSection(serverName);
            List<String> commands = server.getStringList("commands");
            serverPermissions.put(serverName, "bungeecore.server." + server.getString("permission"));
            String cmd = (String) commands.remove(0);
            this.addCmd(new ServerSwitchCommand(cmd, serverName, (String) serverPermissions.get(serverName),(String[]) commands.toArray(new String[0])));
        }

        commands.put("/cb", "");
        commands.remove("/citybuild");
        commands.remove("/pl");
        commands.remove("/plugins");
        commands.remove("/?");
        commands.remove("/bukkit:pl");
        commands.remove("/bukkit:plugins");
        commands.remove("/gamemode");
        commands.remove("/bukkit:gamemode");
        commands.remove("/bukkit:");
        commands.remove("/bukkit");
        this.addCmd(new TeamchatCommand());
        this.addCmd(new addgroupperm());
        this.addCmd(new removegroupperm());
        this.addCmd(new setgroupserver());
        this.addCmd(new deletegroupserver());
        this.addCmd(new removegroupperm());
        this.addCmd(new creategroup());
        this.addCmd(new deletegroup());
        this.addCmd(new setgrouprefix());
        this.addCmd(new setgroupsuffix());
        this.addCmd(new setgroupban());
        this.addCmd(new setgroupmute());
        this.addCmd(new setgroup());
        this.addCmd(new CheckGroupCommand());
        this.addCmd(new AdminHelpCommand());
        this.addCmd(new TeamHelpCommand());
        this.addCmd(new ClearChat());
        this.addCmd(new MsgCommand());
        this.addCmd(new RCommand());
        this.addCmd(new PingCommand());
        this.addCmd(new AlertCommand());
        this.addCmd(new KickCommand());
        this.addCmd(new setgroupglobalmute());
        if(config.getBoolean("System.Join") == true) {
            this.addCmd(new JoinCommand());
        }else {
            this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + "Join Deakiviert/Disabled");
        }
        if(config.getBoolean("System.Joinme") == true) {
            this.addCmd(new JoinmeCommand());
        }else {
            this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + "Joinme Deakiviert/Disabled");
        }
        this.addCmd(new HelpCommand());
        this.addCmd(new BewerbenCommand());
        this.addCmd(new YouTuberCommand());
        if(config.getBoolean("System.BanSystem") == true) {
            this.addCmd(new Ban1Command());
            this.addCmd(new UnBanCommand());
        }else {
            this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + "BanSystem Deakiviert/Disabled");
        }
        if(config.getBoolean("System.MuteSystem") == true) {
            this.addCmd(new MuteCommand());
            this.addCmd(new UnMuteCommand());
        }else {
            this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + "MuteSystem Deakiviert/Disabled");
        }
        if(config.getBoolean("System.Clan") == true) {
            this.addCmd(new teammsgCommand());
        }else {
            this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + "ClanSystem Deakiviert/Disabled");
        }
        this.addCmd(new restartCommand());
        this.addCmd(new Check1Command());
        //LuckPerms api = LuckPermsProvider.get();
        this.getProxy().getConsole().sendMessage(BungeeCore_MadBryan.ChatPrefix + "Versuche Verbindung mit MYSQL DatenBank herzustellen ...");
        try {
            if (sql.getCon().isClosed()) {
                this.getProxy().getConsole().sendMessage(
                        BungeeCore_MadBryan.ChatPrefix + "Versuche Verbindung mit MYSQL DatenBank Fehlgeschlagen... ");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(config.getBoolean("System.Broadcast") == true){
        BroadCastMsgs = (String[]) this.config.getStringList("broadcasts").toArray(new String[1]);
        this.getProxy().getScheduler().schedule(this, () -> {
            this.getProxy().broadcast(ChatPrefix + BroadCastMsgs[LastBroadCast]);
            ++LastBroadCast;
            if (LastBroadCast == BroadCastMsgs.length) {
                LastBroadCast = 0;
            }

        }, 5L, 5L, TimeUnit.MINUTES);
        }else {

        }
    }

    @Override
    public void onDisable() {
        sql.close();
        this.getProxy().getPluginManager().unregisterCommands(this);
        this.getProxy().getPluginManager().unregisterListeners(this);
    }

    public static void log(String message, ServerInfo server) {
        log("[" + server.getName() + "] " + message);
    }

    public static void log(String message) {
        instance.getLogger().log(Level.INFO, message);
    }

    private void addCmd(Command cmd) {
        this.getProxy().getPluginManager().registerCommand(this, cmd);
    }

    public static long getMilliseconds(String date) {
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        Date d;
        try {
            d = simpleDate.parse(date);
            return d.getTime();
        } catch (ParseException e) {
            // Irgendwas machen, das Datum konnte nicht gefunden werden.
        }
        return 0; // Kannst du gegen irgendwas anderes ersetzen, wenn das für dich mehr Sinn macht.
    }

    public static LocalDate getDate() {
        LocalDate todaysDate = LocalDate.now();
        return todaysDate;
    }
}
