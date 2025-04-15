package de.redstonetechnik.bungeecoremadbryan.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sql {
    private static Connection con;
    private static Connection webcon;
    private static String url;
    private static String weburl;
    private static String user;
    private static String password;
    

    public sql() {
    	   super();
    	}

    public static void connect(String url, String weburl, String user, String password) {
    	sql.url = url;
    	sql.weburl = weburl;
    	sql.user = user;
    	sql.password = password;

        try {
            con = DriverManager.getConnection(url + "?autoreconnect=true", user, password);
            webcon = DriverManager.getConnection(weburl + "?autoreconnect=true", user, password);
        } catch (SQLException var5) {
            var5.printStackTrace();
        }

    }

    public static void close() {
        try {
            if (con != null) {
                con.close();
            }

            if (webcon != null) {
                webcon.close();
            }
        } catch (SQLException var1) {
            var1.printStackTrace();
        }

    }

    public static void update(String qry) {
        try {
            PreparedStatement st = con.prepareStatement(qry);
            st.executeUpdate();
        } catch (SQLException var4) {
            close();
            connect(url, weburl, user, password);

            try {
                PreparedStatement st = con.prepareStatement(qry);
                st.executeUpdate();
            } catch (SQLException var3) {
                var3.printStackTrace();
            }
        }

    }

    public static ResultSet executeQuery(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }







    
    public static int executeUpdate(PreparedStatement ps) throws SQLException {
        return ps.executeUpdate();
    }
    
    public static ResultSet select(String qry) {
        try {
            PreparedStatement st = con.prepareStatement(qry);
            return st.executeQuery();
        } catch (SQLException var4) {
            close();
            connect(url, weburl, user, password);

            try {
                PreparedStatement st = con.prepareStatement(qry);
                return st.executeQuery();
            } catch (SQLException var3) {
                var3.printStackTrace();
                return null;
            }
        }
    }

    public static Connection getCon() {
        return con;
    }
    
    public static String disarmString(String s) {
        return s.replace("'", "");
    }

    public static void setWebpw(WarkingUser user, String password) {
        try {
            PreparedStatement st = webcon.prepareStatement("INSERT INTO User\n  (UID, WebPassword)\nVALUES\n  (?, password(?))\nON DUPLICATE KEY UPDATE\n  WebPassword = VALUES(WebPassword)");
            st.setInt(1, user.id);
            st.setString(2, password);
            st.executeUpdate();
        } catch (SQLException var5) {
            close();
            connect(url, weburl, user, password);

            try {
                PreparedStatement st = webcon.prepareStatement("INSERT INTO User\n  (UID, WebPassword)\nVALUES\n  (?, password(?))\nON DUPLICATE KEY UPDATE\n  WebPassword = VALUES(WebPassword)");
                st.setInt(1, user.id);
                st.setString(2, password);
                st.executeUpdate();
            } catch (SQLException var4) {
                var4.printStackTrace();
            }
        }

    }

	private static void connect(String url2, String weburl2, WarkingUser user2, String password2) {
		// TODO Auto-generated method stub
		
	}
}
