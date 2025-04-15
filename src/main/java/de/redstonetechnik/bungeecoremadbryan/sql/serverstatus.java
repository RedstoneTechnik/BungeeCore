package de.redstonetechnik.bungeecoremadbryan.sql;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;
import de.redstonetechnik.bungeecoremadbryan.api.SqlUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class serverstatus {
    public String serverid;
    public int id1;
    public String GroupSortID;
    public String MuteReason;

    public String PermissionName;

    public String PermissionValue;
    public Timestamp MuteTime;
    public String Muter;

    private static final List GroupPermissions = new ArrayList();

    public static void iniCache() {
        grouppermissions = new ConcurrentHashMap<Integer, serverstatus>();
    }

    public static ConcurrentHashMap<Integer, serverstatus> grouppermissions;

    private static final List GroupPermissionName = new ArrayList();

    public serverstatus(int serverid) {
        ResultSet dbplayer = (BungeeCore_MadBryan.instance.sql.select("SELECT * FROM serverstatus WHERE serverid = '" + serverid + "'"));
        try {
            if (!dbplayer.next()) {
                this.init(dbplayer);
            } else {
                this.init(dbplayer);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }
    }

    public serverstatus(String ServerName) {
        this.init(BungeeCore_MadBryan.instance.sql.select("SELECT * FROM serverstatus WHERE lower(ServerName) = '" + ServerName.toLowerCase() + "'"));
    }

    public serverstatus(String serverid, String GruppenName, String Permissions) {
        ResultSet dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM serverstatus WHERE serverid = '" + serverid + "'");
        try {
            if (dbplayer.next()) {
                dbplayer.beforeFirst();
                this.init(dbplayer);
            } else {
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO serverstatus (serverid, GroupSortID, PermissionName) VALUES ('" + serverid.toString() + "', '" + GruppenName + "', '" + PermissionName + "')");
                dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM serverstatus WHERE serverid = '" + serverid.toString() + "'");
                this.init(dbplayer);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public static ArrayList<serverstatus> getPermissionsAsList(int limit) {
        ArrayList<serverstatus> list = new ArrayList<serverstatus>();
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT COUNT(serverid) as total FROM serverstatus LIMIT " + limit);
            ResultSet rs = SqlUtil.executeQuery(ps);
            while (rs.next()) {
                serverstatus team = new serverstatus(rs.getInt("total"));
                ps = SqlUtil.prepareStatement("SELECT serverid FROM serverstatus");
                rs = SqlUtil.executeQuery(ps);
                int i = 0;
                while(rs.next()) {
                    team = new serverstatus(rs.getInt("serverid"));
                    list.add(team);
                    i++;
                }
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void clearserverpermissions(String servername, int groupid){
        getGroupServerPermissions(servername, groupid).clear();
    }

    public ArrayList<String> getGroupServerPermissions(String servername, int groupid) {
        ArrayList<String> list;
        String perms = "";
        try {
            PreparedStatement st = sql.getCon().prepareStatement("SELECT * FROM serverstatus WHERE serverid=? AND GroupSortID=?");
            st.setString(1, servername);
            st.setString(2, String.valueOf(groupid));
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                perms = rs.getString("PermissionName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(perms.length() > 0) {
            perms = perms.substring(1, perms.length()-1);
        }else {
            return new ArrayList<String>();
        }
        String[] array  = perms.split(", ");
        list = new ArrayList<String>(Arrays.asList(array));
        return list;

    }
    public void groupserverpermissionadd(int groupid, String servername ,String permission, String permission1) {
        if (permission == null){
            String permissions = permission;
            if (getGroupServerPermissions(servername, groupid).isEmpty() == true) {
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO serverstatus (serverid, GroupSortID, PermissionName) VALUES ('" + servername + "', '" + groupid + "' + '" + permissions.replace("[]", "") + "')");
                clearserverpermissions(servername, groupid);
            } else {
                BungeeCore_MadBryan.instance.sql.update("UPDATE `serverstatus` SET `PermissionName` = '" + permissions + "' WHERE `serverid` = '" + servername + "' AND `GroupSortID` = '" + groupid + "'");
                clearserverpermissions(servername, groupid);
            }
        }else{
            String permissions1 = permission1;
            if (getGroupServerPermissions(servername, groupid).isEmpty() == true) {
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO serverstatus (serverid, GroupSortID, PermissionName) VALUES ('" + servername + "', '" + groupid + "' + '" + permissions1.replace("[]", "") + "')");
                clearserverpermissions(servername, groupid);
            } else {
                BungeeCore_MadBryan.instance.sql.update("UPDATE `serverstatus` SET `PermissionName` = '" + permissions1.replaceAll(permission1, " " + permission1) + "' WHERE `serverid` = '" + servername + "' AND `GroupSortID` = '" + groupid + "'");
                clearserverpermissions(servername, groupid);
            }
        }
    }
    public void grouppermissionremove(int groupid, String ServerName, String serverid1) {
        if (ServerName == null){
        }else{
            if (getGroupServerPermissions(ServerName, groupid).isEmpty() == true) {
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO serverstatus (serverid, GroupSortID) VALUES ('" + ServerName + "', '" + groupid + "')");
                clearserverpermissions(ServerName, groupid);
            } else {
                BungeeCore_MadBryan.instance.sql.update("UPDATE `serverstatus` SET `PermissionName` = '" + serverid1.replaceAll(serverid1, " " + serverid1) + "' WHERE `GroupSortID` = '" + groupid + "'");
                clearserverpermissions(ServerName, groupid);
            }
        }
    }
    public serverstatus[] getGroupPermissions1() {
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT COUNT(ID) as total FROM serverstatus");
            ResultSet rs = SqlUtil.executeQuery(ps);
            rs.next();
            serverstatus[] teams = new serverstatus[rs.getInt("total")];
            ps = SqlUtil.prepareStatement("SELECT ID FROM serverstatus");
            rs = SqlUtil.executeQuery(ps);
            int i = 0;
            while(rs.next()) {
                teams[i] = new serverstatus(rs.getInt("ID"));
                i++;
            }
            return teams;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void init(ResultSet dbplayer) {
        try {
            if (!dbplayer.next()) {
                return;
            }

            this.serverid = dbplayer.getString("serverid");
            this.GroupSortID = dbplayer.getString("GroupSortID");
            this.PermissionName = dbplayer.getString("PermissionName");
            this.PermissionValue = dbplayer.getString("PermissionValue");
            this.MuteReason = dbplayer.getString("MuteReason");
            this.MuteTime = dbplayer.getTimestamp("MuteTime");
            this.Muter = dbplayer.getString("Muter");
            GroupPermissionName.add(PermissionName);

        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }
    
    public void updateServerName(String username, int serverid) {
    	BungeeCore_MadBryan.instance.sql.update("UPDATE userdata SET UserName = '" + username + "' WHERE serverid = '" + serverid + "'");

    }
    public boolean isserverMuted() {
        if (this.MuteTime == null) {
            return false;
        } else if (!this.MuteTime.after(new Date())) {
        	BungeeCore_MadBryan.instance.sql.update("UPDATE serverstatus SET MuteTime = NULL, Muter = NULL, MuteReason = ''");
            this.MuteTime = null;
            this.MuteReason = "";
            return false;
        } else {
            return true;
        }
    }
    
    public boolean isserverunMuted() {
        if (this.MuteTime != null) {
        	BungeeCore_MadBryan.instance.sql.update("UPDATE serverstatus SET MuteTime = NULL, Muter = NULL, MuteReason = NULL");
            this.MuteTime = null;
            this.MuteReason = "";
            return false;
        } else {
            return true;
        }
    }
    
    public void permissioncreate(int serverid ,String PermissionName, String PermissionValue) {
        ResultSet dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM serverstatus WHERE serverid = '" + serverid + "'AND `PermissionName` = '" + PermissionName + "'");
        try {
            if (dbplayer.next()) {
                dbplayer.beforeFirst();
                this.init(dbplayer);
            } else {
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO serverstatus (serverid, PermissionName, PermissionValue) VALUES ('" + serverid + "', '" + PermissionName + "', '" + PermissionValue + "')");
                dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM serverstatus WHERE serverid = '" + serverid + "'");
                this.init(dbplayer);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public String getGroupPermissionName() {
        return GroupPermissionName.toString();
    }

    public String getGroupPermissions() {
        return GroupPermissions.toString();
    }
    public void muteserver(Timestamp time, String muteReason, String muter) {
    	BungeeCore_MadBryan.instance.sql.update("UPDATE serverstatus SET MuteTime = '" + time.toString() + "',Muter = '" + muter + "',  MuteReason = '" + muteReason + "'");

    }
}
