package de.redstonetechnik.bungeecoremadbryan.sql;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class WarkingGroup {
	private static final List<WarkingGroup> allGroups = new ArrayList();
    private String groupid1;
    private int groupid;

    UUID UUID;
    private static int group;
    private String GroupName;
    private String GroupSuffix;

    private String GroupPrefix;

    private String GlobalMute;
    
    private int BannPower;
    private int BenBannPower;
    private int MutePower;
    private int BenMutePower;

    public WarkingGroup(int id) {
        ResultSet dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM usergroups WHERE id = '" + id + "'");
        this.init(dbplayer);
    }

    public WarkingGroup(String groupname) {
        ResultSet dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM usergroups WHERE GroupName = '" + groupname + "'");
        this.init(dbplayer);
    }

    private void init(ResultSet dbplayer) {
        try {
            if (!dbplayer.next()) {
                return;
            }

            this.groupid = dbplayer.getInt("id");
            this.groupid1 = dbplayer.getString("id");
            this.GroupName = dbplayer.getString("GroupName");
            this.GroupPrefix = dbplayer.getString("GroupPrefix");
            this.GroupSuffix = dbplayer.getString("GroupSuffix");
            this.GlobalMute = dbplayer.getString("GlobalMute");
            this.BannPower = dbplayer.getInt("BannPower");
            this.BenBannPower = dbplayer.getInt("BenBannPower");
            this.MutePower = dbplayer.getInt("MutePower");
            this.BenMutePower = dbplayer.getInt("BenMutePower");
            allGroups.add(this);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }

    }

    public void groupcreate(String GroupName) {
        ResultSet dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM usergroups WHERE GroupName = '" + GroupName + "'");

        try {
            if (dbplayer.next()) {
                dbplayer.beforeFirst();
                this.init(dbplayer);
            } else {
                BungeeCore_MadBryan.instance.sql.update("INSERT INTO usergroups (GroupName) VALUES ('" + GroupName + "')");
                dbplayer = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM usergroups WHERE GroupName = '" + GroupName + "'");
                this.init(dbplayer);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public static void groupdelete(int id) throws SQLException {

        PreparedStatement ps = BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("DELETE FROM usergroups WHERE id=?");
        ps.setInt(1, id);
        BungeeCore_MadBryan.instance.sql.executeUpdate(ps);
    }

	public void setgroup(UUID uuid, int groupid) throws SQLException {

    	UUID = uuid;
        group = groupid;
        PreparedStatement ps = BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("UPDATE `userdata` SET `UserGroup` = ? WHERE `userdata`.`UUID` = ?");
        ps.setInt(1, group);
        ps.setString(2, UUID.toString());
        BungeeCore_MadBryan.instance.sql.executeUpdate(ps);
    }
	
    public void setgroupbannpower(int groupid, int bannpower) throws SQLException {

        groupid = groupid;
        bannpower = bannpower;
        PreparedStatement ps = BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("UPDATE `usergroups` SET `BannPower` = ? WHERE `usergroups`.`id` = ?");
        ps.setString(1, String.valueOf(bannpower));
        ps.setString(2, String.valueOf(groupid));
        BungeeCore_MadBryan.instance.sql.executeUpdate(ps);
    }

    public void setgroupbenbannpower(int groupid, int benbannpower) throws SQLException {

        groupid = groupid;
        benbannpower = benbannpower;
        PreparedStatement ps = BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("UPDATE `usergroups` SET `BenBannPower` = ? WHERE `usergroups`.`id` = ?");
        ps.setString(1, String.valueOf(benbannpower));
        ps.setString(2, String.valueOf(groupid));
        BungeeCore_MadBryan.instance.sql.executeUpdate(ps);
    }

    public void setgroupmutepower(int groupid, int mutepower) throws SQLException {

        groupid = groupid;
        mutepower = mutepower;
        PreparedStatement ps = BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("UPDATE `usergroups` SET `MutePower` = ? WHERE `usergroups`.`id` = ?");
        ps.setString(1, String.valueOf(mutepower));
        ps.setString(2, String.valueOf(groupid));
        BungeeCore_MadBryan.instance.sql.executeUpdate(ps);
    }

    public void setgroupbenmutepower(int groupid, int benmutepower) throws SQLException {

        groupid = groupid;
        benmutepower = benmutepower;
        PreparedStatement ps = BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("UPDATE `usergroups` SET `BenMutePower` = ? WHERE `usergroups`.`id` = ?");
        ps.setString(1, String.valueOf(benmutepower));
        ps.setString(2, String.valueOf(groupid));
        BungeeCore_MadBryan.instance.sql.executeUpdate(ps);
    }

    public void setgroupsuffix(int id, String groupsuffix) throws SQLException {

        groupid = id;
        groupsuffix = groupsuffix;
        PreparedStatement ps = BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("UPDATE `usergroups` SET `GroupSuffix` = ? WHERE `usergroups`.`id` = " + groupid);
        ps.setString(1, groupsuffix);
        BungeeCore_MadBryan.instance.sql.executeUpdate(ps);
    }

    public void setgroupprefix(int id, String groupprefix) throws SQLException {

        groupid = id;
        groupprefix = groupprefix;
        PreparedStatement ps = BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("UPDATE `usergroups` SET `GroupPrefix` = ? WHERE `usergroups`.`id` = " + groupid);
        ps.setString(1, groupprefix);
        BungeeCore_MadBryan.instance.sql.executeUpdate(ps);
    }

    public void setgroupglobalmute(int id, String globalmute) throws SQLException {

        groupid = id;
        globalmute = globalmute;
        PreparedStatement ps = BungeeCore_MadBryan.instance.sql.getCon().prepareStatement("UPDATE `usergroups` SET `GlobalMute` = ? WHERE `usergroups`.`id` = " + groupid);
        ps.setString(1, globalmute);
        BungeeCore_MadBryan.instance.sql.executeUpdate(ps);
    }

    
    public String getGroupId1() {
        return this.groupid1;
    }
    
    public int getGroupId() {
        return this.groupid;
    }
    public String getGroupName() {
        return this.GroupName;
    }

    public int getGroup() {
        return this.groupid;
    }

    public String getGroupSuffix() {return this.GroupSuffix;}

    public String getGroupPrefix() {return this.GroupPrefix;}

    public String getGroupGlobalMute() {return this.GlobalMute;}
    public int getGroupBannPower() {return this.BannPower;}
    public int getGroupBenBannPower() {return this.BenBannPower;}
    public int getGroupMutePower() {return this.MutePower;}
    public int getGroupBenMutePower() {return this.BenMutePower;}
    public void clearCacheplayergroup(WarkingGroup group) {
        this.groupid = group.getGroupId();
        this.groupid1 = group.getGroupId1();
        this.GroupName = group.getGroupName();
        this.GroupPrefix = group.getGroupPrefix();
        this.GroupSuffix = group.getGroupSuffix();
        this.BannPower = group.getGroupBannPower();
        this.BenBannPower = group.getGroupBenBannPower();
        this.MutePower = group.getGroupMutePower();
        this.BenMutePower = group.getGroupBenMutePower();
        allGroups.remove(this);
    }

    public static WarkingGroup get(String GroupName) {
        GroupName = BungeeCore_MadBryan.instance.sql.disarmString(GroupName);
        Iterator var1 = allGroups.iterator();

        WarkingGroup user;
        do {
            if (!var1.hasNext()) {
                WarkingGroup offline = new WarkingGroup(GroupName);
                return offline;
            }

            user = (WarkingGroup)var1.next();
        } while(!user.GroupName.equalsIgnoreCase(GroupName));

        return user;
    }

    public static WarkingGroup get(int id) {
        Iterator var1 = allGroups.iterator();

        WarkingGroup group;
        do {
            if (!var1.hasNext()) {
                WarkingGroup offline = new WarkingGroup(id);
                return offline;
            }

            group = (WarkingGroup)var1.next();
        } while(group.groupid != id);

        return group;
    }

    public static void clearCache() {
        allGroups.clear();
    }
}
