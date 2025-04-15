package de.redstonetechnik.bungeecoremadbryan.api;
 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

import de.redstonetechnik.bungeecoremadbryan.sql.WarkingUser;
import de.redstonetechnik.bungeecoremadbryan.api.WarShipTeam.TeamRang;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer.ChatMode;


 
public class WarShipTeam {
   
    public enum TeamRang {
    	LEADER(6), ADMIN(5), DESIGNER(4), TECHNIKER(3), FIGHTER(2), MEMBER(1), UNBEKANNT(0);
        private final int id;
        TeamRang(int id) {
            this.id = id;
        }
        public int getId() {
            return id;
        }
        public static TeamRang getRang(Integer id) {
            if(id == null) return null;
            for(TeamRang tr : values()) {
                if(tr.id == id) return tr;
            }
            return null;
        }
    }
   
    public final static int NAME_MIN = 3;
    public final static int NAME_MAX = 14;
    public final static int ALIAS_MIN = 2;
    public final static int ALIAS_MAX = 4;
   
    private int wins,loses,public_loses,public_wins;
   
    public static WarShipTeam getWarShipTeam(Integer id) {
 
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT id FROM wstteams WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = SqlUtil.executeQuery(ps);
            if(rs.next()) return new WarShipTeam(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
    public static WarShipTeam getWarShipTeam(String name) {
        try {
            return getWarShipTeam(Integer.parseInt(name));
        } catch(NumberFormatException e) {}
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT id FROM wstteams WHERE name=?");
            ps.setString(1, name);
            ResultSet rs = SqlUtil.executeQuery(ps);
            if(rs.next()) return new WarShipTeam(rs.getInt("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
    
    public static WarShipTeam getWarShipTeams1() {
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT COUNT(id) as total FROM wstteams");
            ResultSet rs = SqlUtil.executeQuery(ps);
            rs.next();
            WarShipTeam teams = new WarShipTeam(rs.getInt("total"));
            ps = SqlUtil.prepareStatement("SELECT id FROM wstteams");
            rs = SqlUtil.executeQuery(ps);
            int i = 0;
            while(rs.next()) {
                teams = WarShipTeam.getWarShipTeam(rs.getInt("id"));
                i++;
            }
            return teams;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static WarShipTeam[] getWarShipTeams() {
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT COUNT(id) as total FROM wstteams");
            ResultSet rs = SqlUtil.executeQuery(ps);
            rs.next();
            WarShipTeam[] teams = new WarShipTeam[rs.getInt("total")];
            ps = SqlUtil.prepareStatement("SELECT id FROM wstteams");
            rs = SqlUtil.executeQuery(ps);
            int i = 0;
            while(rs.next()) {
                teams[i] = WarShipTeam.getWarShipTeam(rs.getInt("id"));
                i++;
            }
            return teams;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
   
    public int getId() {
        return id;
    }
   
    public static WarShipTeam createWarShipTeam(String name, String alias, String leader) {
        return new WarShipTeam(name, alias, leader);
    }
   
    private int id;
    private String name;
    private String alias;
    private String descripton;
    private int normal;
    private int minigames;
    private int ws;
    private int mwg;
    private int wg;
    private int br;
    private final HashSet<String> members = new HashSet<>();
    private final HashSet<String> leaders = new HashSet<>();
    private final HashSet<String> admins = new HashSet<>();
    private final HashSet<String> designers = new HashSet<>();
    private final HashSet<String> technikers = new HashSet<>();
    private final HashSet<String> fighters = new HashSet<>();
    private String leader;
   
    private WarShipTeam(int id) {
        this.id = id;
        reload();
    }
   
    private WarShipTeam(String name, String alias, String leader){
        this.name = name;
        this.alias = alias;
        members.add(this.leader);
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("INSERT INTO wstteams(name, alias) VALUES (?,?)");
            ps.setString(1, name);
            ps.setString(2, alias);
            SqlUtil.executeUpdate(ps);
            ps = SqlUtil.prepareStatement("SELECT id FROM wstteams WHERE name=?");
            ps.setString(1, name);
            ResultSet rs = SqlUtil.executeQuery(ps);
            rs.next();
            this.id = rs.getInt("id");
        } catch (SQLException e) {
            id = -1;
            e.printStackTrace();
        }
        setLeader(WarShipPlayer.getByUniqueId(leader));
    }
   
    public void update(String name1, String alias1, int wins1, int loses1, int public_wins1, int public_loses1, String descripton1, int id1) {
        try {
        	PreparedStatement ps = SqlUtil.prepareStatement("UPDATE wstteams SET name=?, alias=?, wins=?, loses=?, public_wins=?, public_loses=?, descripton=? WHERE id=?");
            ps.setString(1, name1);
            ps.setString(2, alias1);
            if(wins1 != 0) ps.setInt(3, wins1);
            else ps.setNull(3, java.sql.Types.INTEGER);
            if(loses1 != 0) ps.setInt(4, loses1);
            else ps.setNull(4, java.sql.Types.INTEGER);
            if(public_wins1 != 0) ps.setInt(5, public_wins1);
            else ps.setNull(5, java.sql.Types.INTEGER);
            if(public_loses1 != 0) ps.setInt(6, public_loses1);
            else ps.setNull(6, java.sql.Types.INTEGER);
            ps.setString(7, descripton1);
            ps.setInt(8, id1);
            SqlUtil.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void reload() {
        members.clear();
        admins.clear();
        fighters.clear();
        technikers.clear();
        designers.clear();
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT * FROM wstteams WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = SqlUtil.executeQuery(ps);
            rs.next();
            name = rs.getString("name");
            alias = rs.getString("alias");
            rs.getInt("wins");
            if(!rs.wasNull())
            wins = rs.getInt("wins");
            rs.getInt("loses");
            if(!rs.wasNull())
            loses = rs.getInt("loses");
            rs.getInt("public_wins");
            if(!rs.wasNull())
            public_wins = rs.getInt("public_wins");
            rs.getInt("public_loses");
            if(!rs.wasNull())
            public_loses = rs.getInt("public_loses");
            descripton = rs.getString("descripton");
            ps = SqlUtil.prepareStatement("SELECT uuid FROM wstplayers WHERE team=?");
            ps.setInt(1, id);
            rs = SqlUtil.executeQuery(ps);
            while(rs.next()) {
                WarShipPlayer p = WarShipPlayer.getByUniqueId(rs.getString("uuid"));
                if(p.isLeader()) leader = p.getUniqueId();
                if(p.getRang().getId() == TeamRang.DESIGNER.getId()) { 
                designers.add(p.getName());
                }
                if(p.getRang().getId() == TeamRang.LEADER.getId()) { 
                leaders.add(p.getName());
                }
                if(p.getRang().getId() == TeamRang.FIGHTER.getId()) { 
                fighters.add(p.getName());
                }
                if(p.getRang().getId() == TeamRang.TECHNIKER.getId()) { 
                technikers.add(p.getName());
                }
                if(p.getRang().getId() == TeamRang.ADMIN.getId()) { 
                admins.add(p.getName());
                }
                if(p.getRang().getId() == TeamRang.MEMBER.getId()) { 
                members.add(p.getName());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public WarShipPlayer[] getMembers() {
        HashSet<WarShipPlayer> players = new HashSet<>();
        for(String uuid : members) {
            players.add(WarShipPlayer.getByUniqueId(uuid));
        }
        return players.toArray(new WarShipPlayer[players.size()]);
    }
   
    public String[] getMembersUniqueIds() {
        return members.toArray(new String[members.size()]);
    }
   
    public WarShipPlayer[] getAdmins() {
        HashSet<WarShipPlayer> players = new HashSet<>();
        for(String uuid : admins) {
            players.add(WarShipPlayer.getByUniqueId(uuid));
        }
        return players.toArray(new WarShipPlayer[players.size()]);
    }
    
    public WarShipPlayer[] getDesigners() {
        HashSet<WarShipPlayer> players = new HashSet<>();
        for(String uuid : designers) {
            players.add(WarShipPlayer.getByUniqueId(uuid));
        }
        return players.toArray(new WarShipPlayer[players.size()]);
    }
   
    public WarShipPlayer[] getLeaders() {
        HashSet<WarShipPlayer> players = new HashSet<>();
        for(String uuid : leaders) {
            players.add(WarShipPlayer.getByUniqueId(uuid));
        }
        return players.toArray(new WarShipPlayer[players.size()]);
    }
    
    public WarShipPlayer[] getTechnikers() {
        HashSet<WarShipPlayer> players = new HashSet<>();
        for(String uuid : technikers) {
            players.add(WarShipPlayer.getByUniqueId(uuid));
        }
        return players.toArray(new WarShipPlayer[players.size()]);
    }
    
    public WarShipPlayer[] getFighters() {
        HashSet<WarShipPlayer> players = new HashSet<>();
        for(String uuid : fighters) {
            players.add(WarShipPlayer.getByUniqueId(uuid));
        }
        return players.toArray(new WarShipPlayer[players.size()]);
    }
    
    public String[] getAdminUniqueIds() {
        return admins.toArray(new String[admins.size()]);
    }
 
    public String getName() {
        return name;
    }
   
    public String getAlias() {
        return alias;
    }
   
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || !obj.getClass().equals(this.getClass())) return false;
        if(((WarShipTeam)obj).getId() == this.getId()) return true;
        return false;
    }
   
    @Override
    public String toString() {
        return "§7" + id + " - §c" + name + " §7[§a" + alias + "§7]: §e" + WarShipPlayer.getByUniqueId(leader).getName();
    }
   
    public WarShipPlayer getLeader() {
        return WarShipPlayer.getByUniqueId(leader);
    }
   
    public void setLeader(String leader) {
        setLeader(WarShipPlayer.getByUniqueId(leader));
    }
   
    public void setLeader(WarShipPlayer leader) {
        if(leader == null) throw new NullPointerException("Leader is null");
        leader.setTeam(id);
        leader.setTeamRang(TeamRang.LEADER);
        leader.update();
        this.leader = leader.getUniqueId();
    }
   
    public void disband() {
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("UPDATE wstplayers SET team=NULL, rang=NULL WHERE team=?");
            ps.setInt(1, id);
            SqlUtil.executeUpdate(ps);
            ps = SqlUtil.prepareStatement("DELETE FROM wstteams WHERE id=?");
            ps.setInt(1, id);
            SqlUtil.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public void broadcast(String message) {
    	Iterator var10 = ProxyServer.getInstance().getPlayers().iterator();
        ProxiedPlayer target = (ProxiedPlayer)var10.next();
        WarkingUser user = new WarkingUser(target.getUniqueId());
        WarShipPlayer wsplayer = WarShipPlayer.getByUniqueId(user.UUID.toString());

            if (wsplayer.hasTeam() && target.getChatMode() == ChatMode.SHOWN) {
                    for(WarShipPlayer w : getMembers()) {
                        ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
                        if(t!=null) {
                            t.sendMessage(message);
                        }
                    }
                    for(WarShipPlayer w : getFighters()) {
                        ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
                        if(t!=null) {
                            t.sendMessage(message);
                        }
                    }
                    for(WarShipPlayer w : getAdmins()) {
                        ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
                        if(t!=null) {
                            t.sendMessage(message);
                        }
                    }
                    for(WarShipPlayer w : getTechnikers()) {
                        ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
                        if(t!=null) {
                            t.sendMessage(message);
                        }
                    }
                    for(WarShipPlayer w : getDesigners()) {
                        ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
                        if(t!=null) {
                            t.sendMessage(message);
                        }
                    }
                    for(WarShipPlayer w : getLeaders()) {
                        ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
                        if(t!=null) {
                            t.sendMessage(message);
                        }
                    
                }
        }
    }
    
    public HashSet<String> getmember1() {
        for(WarShipPlayer w : getMembers()) {
            ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
            if(t!=null) {
                t.getName();
            }
        }
        return members;
    }
    
    public HashSet<String> getadmins1() {
        for(WarShipPlayer w : getAdmins()) {
        	ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
            if(t!=null) {
                t.getName();
            }
        }
        return admins;
    }
    
    public HashSet<String> getdesigners1() {
        for(WarShipPlayer w : getDesigners()) {
        	ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
            if(t!=null) {
                t.getName();
            }
        }
        return designers;
    }
   
    public HashSet<String> gettechnikers1() {
        for(WarShipPlayer w : getTechnikers()) {
        	ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
            if(t!=null) {
                t.getName();
            }
        }
        return technikers;
    }
    
    public HashSet<String> getfighters1() {
        for(WarShipPlayer w : getFighters()) {
        	ProxiedPlayer t = ProxyServer.getInstance().getPlayer(w.getUniqueId());
            if(t!=null) {
                t.getName();
            }
        }
        return fighters;
    }
    
    public static void log(int id, WST_Action action, String value) {
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("INSERT INTO wstlogs(team, action, value) VALUES (?,?,?)");
            ps.setInt(1, id);
            ps.setString(2, action.toString());
            ps.setString(3, value);
            SqlUtil.executeUpdate(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    public static String[] getLogs(int id) {
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT COUNT(id) as total FROM wstlogs WHERE team=?");
            ps.setInt(1, id);
            ResultSet rs = SqlUtil.executeQuery(ps);
            rs.next();
            String[] logs = new String[rs.getInt("total")];
           
            ps = SqlUtil.prepareStatement("SELECT * FROM wstlogs WHERE team=?");
            ps.setInt(1, id);
            rs = SqlUtil.executeQuery(ps);
            int i = 0;
            while(rs.next()) {
                logs[i] = rs.getString("id") + " : " + rs.getString("action") + " : " + rs.getString("value");
                i++;
            }
            return logs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
   
    public static String[] getLogs(int id, WST_Action action) {
        try {
            PreparedStatement ps = SqlUtil.prepareStatement("SELECT COUNT(id) as total FROM wstlogs WHERE team=? AND action=?");
            ps.setInt(1, id);
            ps.setString(2, action.toString());
            ResultSet rs = SqlUtil.executeQuery(ps);
            rs.next();
            String[] logs = new String[rs.getInt("total")];
           
            ps = SqlUtil.prepareStatement("SELECT * FROM wstlogs WHERE team=? AND action=?");
            ps.setInt(1, id);
            ps.setString(2, action.toString());
            rs = SqlUtil.executeQuery(ps);
            int i = 0;
            while(rs.next()) {
                logs[i] = rs.getString("id") + " : " + rs.getString("action") + " : " + rs.getString("value");
                i++;
            }
            return logs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
 
    public String getLeaderUniqueId() {
        return leader;
    }
 
    public String[] getLogs() {
        return getLogs(id);
    }
   
    public int getWins() {
        return wins;
    }
   
    public int getLoses() {
        return loses;
    }
   
    public int getPublicWins() {
        return public_wins;
    }
   
    public int getPublicLoses() {
        return public_loses;
    }
    
    public boolean hasdescripton() {
        return descripton != null;
    }
    
    public String getdescripton() {
    	return descripton;
    }
    
    public void win() {
        wins++;
    }
   
    public void lose() {
        loses++;
    }
   
    public void winPublic() {
        public_wins++;
    }
   
    public void losePublic() {
        public_loses++;
    }
   
 
}