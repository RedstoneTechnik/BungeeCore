package de.redstonetechnik.bungeecoremadbryan.sql;

import de.redstonetechnik.bungeecoremadbryan.BungeeCore_MadBryan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class MuteUserIPs {
    private int UserID;
    private Timestamp Timestamp;
    private String IP;

    private MuteUserIPs(int userID, Timestamp timestamp, String ip) {
        this.UserID = userID;
        this.Timestamp = timestamp;
        this.IP = ip;
    }

    public static List<MuteUserIPs> get(int userID) {
        List<MuteUserIPs> userIPs = new ArrayList();
        ResultSet dbentry = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM muteuserips WHERE UserID = '" + userID + "' ORDER BY Timestamp ASC");

        try {
            while(dbentry.next()) {
                userIPs.add(new MuteUserIPs(userID, dbentry.getTimestamp("Timestamp"), dbentry.getString("IP")));
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return userIPs;
    }

    public static List<MuteUserIPs> get(String ip) {
        List<MuteUserIPs> userIDs = new ArrayList();
        ResultSet dbentry = BungeeCore_MadBryan.instance.sql.select("SELECT * FROM muteuserips WHERE IP = '" + ip + "' ORDER BY Timestamp DESC");

        try {
            while(dbentry.next()) {
                userIDs.add(new MuteUserIPs(dbentry.getInt("UserID"), dbentry.getTimestamp("Timestamp"), ip));
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return userIDs;
    }

    public int getUserID() {
        return this.UserID;
    }

    public Timestamp getTimestamp() {
        return this.Timestamp;
    }

    public String getIP() {
        return this.IP;
    }
}

