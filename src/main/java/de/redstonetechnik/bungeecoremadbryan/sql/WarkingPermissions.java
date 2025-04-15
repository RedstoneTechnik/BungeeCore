package de.redstonetechnik.bungeecoremadbryan.sql;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class WarkingPermissions {
	public final List<WarkingPermissions> allGroupPermissions = new ArrayList();
	private static final List<WarkingPermissions> allGroupPermissions1 = new ArrayList();
    private int groupid;
    private int permissionid;
    private String GroupPermission;
	private static final List GroupPermission1 = new ArrayList();

    public WarkingPermissions (int groupid) {
        ResultSet dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM permisson WHERE GroupSortID = '" + groupid + "' ");
        init(dbplayer);
    }

    public ArrayList<String> getGroupServerids(int groupid) {
        ArrayList<String> list;
        String perms = "";
        try {
            PreparedStatement st = sql.getCon().prepareStatement("SELECT * FROM permisson WHERE GroupSortID=?");
            st.setString(1, String.valueOf(groupid));
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                perms = rs.getString("serverid");
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
    public void clearserverids(int groupid){
        getGroupServerids(groupid).clear();
    }
    private void init(ResultSet dbplayer) {
        try {
            if (!dbplayer.next()) {
                return;
            }

            this.permissionid = dbplayer.getInt("ID");
            this.groupid = dbplayer.getInt("GroupSortID");
            this.GroupPermission = dbplayer.getString("serverid");
            GroupPermission1.add(this.GroupPermission);
            allGroupPermissions.add(this);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }
    private void init1(ResultSet dbplayer1) {
        try {
            if (!dbplayer1.next()) {
                return;
            }

            this.permissionid = dbplayer1.getInt("id");
            this.groupid = dbplayer1.getInt("GroupSortID");
            this.GroupPermission = dbplayer1.getString("serverid");
            GroupPermission1.add(this.GroupPermission);
            allGroupPermissions.add(this);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }
    public static void grouppermissiondelete(String serverid, int groupid, String grouppermission) throws SQLException {
        BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("DELETE FROM permisson (serverid, GroupSortID, Permission) VALUES ('" + serverid + "', '" + groupid + "', '" + grouppermission + "')");
    }

	public void setgrouppermission(int groupid, String grouppermission) throws SQLException {
        PreparedStatement ps = BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("UPDATE `permisson` SET `Permission` = ? WHERE `permission`.`GroupSortID` = ?");
        ps.setInt(1, groupid);
        ps.setString(2, grouppermission);
        BungeeCore_MadBryan.instance.sql.executeUpdate(ps);
    }
	
    public void groupserveradd(int groupid, String serverid, String serverid1) {
        if (serverid == null){
            String serverids = serverid;
            if (getGroupServerids(groupid).isEmpty() == true) {
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO permisson (GroupSortID, serverid) VALUES ('" + groupid + "', '" + serverids.replace("[]", "") + "')");
                clearserverids(groupid);
            } else {
                BungeeCore_MadBryan.instance.sql.update("UPDATE `permisson` SET `serverid` = '" + serverids + "' WHERE `permission`.`GroupSortID` = '" + groupid + "'");
                clearserverids(groupid);
            }
        }else{
            String serverids1 = serverid1;
            if (getGroupServerids(groupid).isEmpty() == true) {
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO permisson (GroupSortID, serverid) VALUES ('" + groupid + "', '" + serverids1 + "')");
                clearserverids(groupid);
            } else {
                BungeeCore_MadBryan.instance.sql.update("UPDATE `permisson` SET `serverid` = '" + serverid1.replaceAll(serverid1, " " + serverid1) + "' WHERE `GroupSortID` = '" + groupid + "'");
                clearserverids(groupid);
            }
        }
    }

    public void groupserverremove(int groupid, String serverid, String serverid1) {
        if (serverid == null){
        }else{
            String serverids1 = serverid1;
            if (getGroupServerids(groupid).isEmpty() == true) {
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO permisson (GroupSortID, serverid) VALUES ('" + groupid + "', '" + serverids1 + "')");
                clearserverids(groupid);
            } else {
                BungeeCore_MadBryan.instance.sql.update("UPDATE `permisson` SET `serverid` = '" + serverid1.replaceAll(serverid1, " " + serverid1) + "' WHERE `GroupSortID` = '" + groupid + "'");
                clearserverids(groupid);
            }
        }
    }

    
    public int getGroupId() {
        return this.groupid;
    }

    public int getPermissionid() {
        return this.permissionid;
    }

    public String getGroupPermission() {
    	return GroupPermission1.toString();
    	}


    public void clearCache() {
        allGroupPermissions.clear();
    }
}
