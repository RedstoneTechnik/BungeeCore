package de.redstonetechnik.bungeecoremadbryan.sql;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class WarkingUser {
    public int id;
    public int id1;
    public UUID UUID;
    public String UserName;
    public int UserGroup;
    public Timestamp BanTime;

    public Timestamp UnBanTime;
    public String BanReason;

    public String UnBanReason;
    public String MuteReason;
    public Timestamp MuteTime;

    public Timestamp UnMuteTime;
    public int time;
    public String UnMuteReason;
    public String Banner;
    public String Muter;

    public String UnBanner;
    public String UnMuter;
    public static Timestamp PermaBan = Timestamp.from(Instant.ofEpochSecond(946684800L));
    public static Timestamp PermaMute = Timestamp.from(Instant.ofEpochSecond(946684800L));

    public WarkingUser(int id) {
        this.init(BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE id = '" + id + "'"));
    }

    public WarkingUser(UUID UUID) {
        this.init(BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE UUID = '" + UUID.toString() + "'"));
    }

    public WarkingUser(String UserName) {
        this.init(BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE lower(UserName) = '" + UserName.toLowerCase() + "'"));
    }

    public WarkingUser(PendingConnection connection) {
        ResultSet dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE UUID = '" + connection.getUniqueId().toString() + "'");

        try {
            if (dbplayer.next()) {
                dbplayer.beforeFirst();
                this.init(dbplayer);
            } else {
                this.UUID = connection.getUniqueId();
                this.UserName = connection.getName();
                this.UserGroup = BungeeCore_MadBryan.instance.config.getInt("Default.Group");
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO userdata (UUID, UserName, UserGroup) VALUES ('" + this.UUID.toString() + "', '" + this.UserName + "', '" + this.UserGroup + "')");
                dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE UUID = '" + this.UUID.toString() + "'");
                this.init(dbplayer);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public WarkingUser(ProxiedPlayer player) {
        ResultSet dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE UUID = '" + player.getUniqueId().toString() + "'");

        try {
            if (dbplayer.next()) {
                dbplayer.beforeFirst();
                this.init(dbplayer);
            } else {
                this.UUID = player.getUniqueId();
                this.UserName = player.getName();
                this.UserGroup = BungeeCore_MadBryan.instance.config.getInt("Default.Group");
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO userdata (UUID, UserName, UserGroup) VALUES ('" + this.UUID.toString() + "', '" + this.UserName + "', '" + this.UserGroup + "')");
                dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE UUID = '" + this.UUID.toString() + "'");
                this.init(dbplayer);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }
    
    public void GroupManager(String UserGroup) {
        ResultSet dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE UUID = '" + this.UUID.toString() + "'");
        try {
            if (dbplayer.next()) {
                dbplayer.beforeFirst();
                this.init(dbplayer);
            } else {
            	BungeeCore_MadBryan.instance.sql.update("UPDATE userdata (UUID, UserName, UserGroup) VALUES ('" + this.UUID.toString() + "', '" + this.UserName + "', '" + UserGroup + "')");
                dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE UUID = '" + this.UUID.toString() + "'");
                this.init(dbplayer);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }
            }

    private void init(ResultSet dbplayer) {
        try {
            if (!dbplayer.next()) {
                return;
            }

            this.id = dbplayer.getInt("id");
            this.UUID = java.util.UUID.fromString(dbplayer.getString("UUID"));
            this.UserName = dbplayer.getString("UserName");
            this.UserGroup = dbplayer.getInt("UserGroup");
            this.MuteReason = dbplayer.getString("MuteReason");
            this.UnMuteReason = dbplayer.getString("UnMuteReason");
            this.MuteTime = dbplayer.getTimestamp("MuteTime");
            this.UnMuteTime = dbplayer.getTimestamp("UnMuteTime");
            this.BanTime = dbplayer.getTimestamp("BanTime");
            this.UnBanTime = dbplayer.getTimestamp("UnBanTime");
            this.BanReason = dbplayer.getString("BanReason");
            this.UnBanReason = dbplayer.getString("UnBanReason");
            this.Banner = dbplayer.getString("Banner");
            this.Muter = dbplayer.getString("Muter");
            this.UnBanner = dbplayer.getString("UnBanner");
            this.UnMuter = dbplayer.getString("UnMuter");
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public boolean isBanned() {
        if (this.BanTime == null) {
            return false;
        } else if (!this.BanTime.after(Timestamp.valueOf(LocalDateTime.now())) && !this.BanTime.before(PermaBan)) {
        	BungeeCore_MadBryan.instance.sql.update("UPDATE userdata SET BanTime = NULL, Banner = NULL, BanReason = '', UnBanTime = NULL, UnBanner = NULL, UnBanReason = '' WHERE UUID = '" + this.UUID.toString() + "'");
        	BungeeCore_MadBryan.instance.sql.update("DELETE FROM BannedUserIPs WHERE UserID = '" + this.id + "'");
            this.BanTime = null;
            this.BanReason = "";
            return false;
        } else {
            return true;
        }
    }

    public int getTime(){
        return this.time;
    }

    public void isunBann(ProxiedPlayer unbanner, Timestamp unbanTime, String unbanreason) {
        String UnBannerName = unbanner.getDisplayName();
        if (this.BanTime != null) {
        	BungeeCore_MadBryan.instance.sql.update("UPDATE userdata SET BanTime = NULL, Banner = NULL, BanReason = NULL, UnBanner = '" + UnBannerName + "', UnBanReason = '" + unbanreason + "', UnBanTime = '" + unbanTime + "' WHERE UUID = '" + this.UUID.toString() + "'");
        	BungeeCore_MadBryan.instance.sql.update("DELETE FROM banneduserips WHERE UserID = '" + this.id + "'");
            this.BanTime = null;
            this.BanReason = "";
        } else {
        }
    }
    
	public static boolean allowMessages(String uuid) {
		try {
			PreparedStatement ps =sql.getCon().prepareStatement("SELECT MESSAGES FROM FriendSystem_Settings WHERE SpielerUUID = ?");
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getBoolean("MESSAGES");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

    public void updateBanIP(String ip) {
    	BungeeCore_MadBryan.instance.sql.update("INSERT INTO banneduserips  (UserID, Timestamp, IP)VALUES  (" + this.id + ", NOW(), '" + ip + "')ON DUPLICATE KEY UPDATE Timestamp=NOW()");
    }
    
    public void updateUserName(String username, UUID uuid) {
    	BungeeCore_MadBryan.instance.sql.update("UPDATE userdata SET UserName = '" + username + "' WHERE UUID = '" + uuid.toString() + "'");

    }

    public void banPlayer(String ip, LocalDateTime time, String banReason, String banner) {
    	BungeeCore_MadBryan.instance.sql.update("UPDATE userdata SET BanTime = '" + Timestamp.valueOf(time) + "',Banner = '" + banner + "', BanReason = '" + banReason + "' WHERE UUID = '" + this.UUID.toString() + "'");
        if (ip != null) {
            this.updateBanIP(ip);
        }

    }
    
    public boolean isMuted() {
        if (this.MuteTime == null) {
            return false;
        } else if (!this.MuteTime.after(new Date()) && !this.MuteTime.before(PermaMute)) {
        	BungeeCore_MadBryan.instance.sql.update("UPDATE userdata SET MuteTime = NULL, Muter = NULL, MuteReason = '', UnMuteTime = NULL, UnMuter = NULL, UnMuteReason = '' WHERE UUID = '" + this.UUID.toString() + "'");
        	BungeeCore_MadBryan.instance.sql.update("DELETE FROM muteuserips WHERE UserID = '" + this.id + "'");
            this.MuteTime = null;
            this.MuteReason = "";
            return false;
        } else {
            return true;
        }
    }
    
    public boolean isunMuted(ProxiedPlayer unmuter, Timestamp unmuteTime, String unmutereason) {
        String UnMuteName = unmuter.getDisplayName();
        if (this.MuteTime != null) {
        	BungeeCore_MadBryan.instance.sql.update("UPDATE userdata SET MuteTime = NULL, Muter = NULL, MuteReason = NULL, UnMuter = '" + UnMuteName + "', UnMuteReason = '" + unmutereason + "', UnMuteTime = '" + unmuteTime + "' WHERE UUID = '" + this.UUID.toString() + "'");
        	BungeeCore_MadBryan.instance.sql.update("DELETE FROM muteuserips WHERE UserID = '" + this.id + "'");
            this.MuteTime = null;
            this.MuteReason = "";
            return false;
        } else {
            return true;
        }
    }
    
    public void usercreate(UUID uuid ,String username) {
        ResultSet dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE UUID = '" + uuid.toString() + "'");

        try {
            if (dbplayer.next()) {
                dbplayer.beforeFirst();
                this.init(dbplayer);
            } else {
                int usergroup = BungeeCore_MadBryan.instance.config.getInt("Default.Group");
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO userdata (UUID, UserName, UserGroup) VALUES ('" + uuid.toString() + "', '" + username + "', '" + usergroup + "')");
                dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM userdata WHERE UUID = '" + uuid.toString() + "'");
                this.init(dbplayer);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }
    
    public void updateMuteIP(String ip) {
    	BungeeCore_MadBryan.instance.sql.update("INSERT INTO muteuserips (UserID, Timestamp, IP) VALUES  (" + this.id + ", NOW(), '" + ip + "') ON DUPLICATE KEY UPDATE Timestamp=NOW()");
    }

    public void mutePlayer(String ip, Timestamp time, String muteReason, String muter) {
    	BungeeCore_MadBryan.instance.sql.update("UPDATE userdata SET MuteTime = '" + time.toString() + "',Muter = '" + muter + "',  MuteReason = '" + muteReason + "' WHERE UUID = '" + this.UUID.toString() + "'");
        if (ip != null) {
            this.updateMuteIP(ip);
        }

    }
}
