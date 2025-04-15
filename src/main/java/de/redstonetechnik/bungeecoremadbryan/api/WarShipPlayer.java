package de.redstonetechnik.bungeecoremadbryan.api;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import de.redstonetechnik.bungeecoremadbryan.api.WarShipTeam.TeamRang;

 
public class WarShipPlayer {
   
    public static WarShipPlayer getByUniqueId(String uuid) {
        return new WarShipPlayer(uuid);
    }
   
    public static WarShipPlayer getByName(String name) {
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT UUID FROM userdata WHERE UserName=?");
            ps.setString(1, name);
            ResultSet rs = SqlUtil.executeQuery(ps);
            if(rs.next()) {
                return new WarShipPlayer(rs.getString("UUID"));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
   
    private TeamRang rang;
    private int team;
    private final String uuid;
    private String name;
    private final HashSet<Integer> invitations = new HashSet<>();
   
    private WarShipPlayer(String uuid) {
        if(uuid == null) throw new NullPointerException("UniqueId is null");
        this.uuid = uuid;
        reload();
    }
   
    public String getUniqueId() {
        return uuid;
    }
   
    public WarShipTeam getTeam() {
        return WarShipTeam.getWarShipTeam(team);
    }
   
    public int getTeamid() {
        return team;
    }
    
   
    public TeamRang getRang() {
        return rang;
    }
   
    public void setTeamRang(TeamRang leader) {
        this.rang = leader;
    }
   
    public boolean isLeader() {
        if(rang == null) return false;
        return rang.equals(TeamRang.LEADER);
    }
   
    public boolean isAdmin() {
        if(rang == null) return false;
        return rang.equals(TeamRang.ADMIN);
    }
    
    public boolean isDesigner() {
        if(rang == null) return false;
        return rang.equals(TeamRang.DESIGNER);
    }
    
    public boolean isTechniker() {
        if(rang == null) return false;
        return rang.equals(TeamRang.TECHNIKER);
    }
   
    public boolean isFighter() {
        if(rang == null) return false;
        return rang.equals(TeamRang.FIGHTER);
    }
    
    public boolean isMember() {
        if(rang == null) return false;
        return rang.equals(TeamRang.MEMBER);
    }
   
    public void setTeam(int team) {
        this.team = team;
    }
   
    public boolean hasTeam() {
        return team != 0;
    }
   
    public boolean hasRang() {
        return rang != null;
    }
   
    @Override
    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(obj == null || !obj.getClass().equals(this.getClass())) return false;
        if(((WarShipPlayer)obj).getUniqueId().equals(getUniqueId())) return true;
        return false;
    }
    
    public String getName() {
        return name;
    }
 
    @Override
    public String toString() {
        if(team == 0) {
            return "�7" + uuid + "[�e" + name + "�7]: �cIst in keinem WarShipTeam";
        }
        if(rang == null) {
            return "�7" + uuid + "[�e" + name + "�7]: �c" + WarShipTeam.getWarShipTeam(team).getName() + "�7(�a" + TeamRang.MEMBER + "�7)";
        }
        return "�7" + uuid + "[�e" + name + "�7]: �c" + WarShipTeam.getWarShipTeam(team).getName() + "�7(�a" + rang + "�7)";
    }
 
    public void update() {
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("UPDATE wstplayers SET team=?, rang=?, invitations=? WHERE uuid=?");
           
            if(team != 0) ps.setInt(1, team);
            else ps.setNull(1, java.sql.Types.INTEGER);
            if(rang != null) ps.setInt(2, rang.getId());
            else ps.setNull(2, java.sql.Types.INTEGER);
            String invs = invitations.toString();
            ps.setString(3, invs.substring(1, invs.length()-1));
            ps.setString(4, uuid);
            SqlUtil.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void reload() {
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT * FROM wstplayers WHERE uuid=?");
            ps.setString(1, uuid);
            ResultSet rs = SqlUtil.executeQuery(ps);
            if(rs.next()) {
                rs.getInt("team");
                if(rs.wasNull()) {
                    team = 0;
                    rang = null;
                    rs.getString("invitations");
                    if(!rs.wasNull()) {
                        for(String s : rs.getString("invitations").split(",")) {
                            try {
                                invitations.add(Integer.parseInt(s));
                            } catch(NumberFormatException e) {}
                        }
                    }
                } else {
                    team = rs.getInt("team");
                    rs.getInt("rang");
                    if(!rs.wasNull())
                    rang = TeamRang.getRang(rs.getInt("rang"));
                    else rang = TeamRang.MEMBER;
                }
            } else {
                ps = SqlUtil.prepareStatement("INSERT INTO wstplayers(uuid) VALUES (?)");
                ps.setString(1, uuid);
                SqlUtil.executeUpdate(ps);
                team = 0;
                rang = null;
            }
            ps = SqlUtil.prepareStatement("SELECT UserName FROM userdata WHERE UUID=?");
            ps.setString(1, uuid);
            rs = SqlUtil.executeQuery(ps);
            if(rs.next()) {
                name = rs.getString("UserName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void invite(int team) {
        invitations.add(team);
    }
 
    public void uninvite(int warShipTeam) {
        invitations.remove(warShipTeam);
    }
   
    public boolean isInvited(int team) {
        return invitations.contains(team);
    }
 
    public boolean hasInvites() {
        return invitations.size() > 0;
    }
}