package de.redstonetechnik.bungeecoremadbryan.api;


import de.redstonetechnik.bungeecoremadbryan.sql.sql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SqlUtil {

    public static PreparedStatement prepareStatement(final String string) throws SQLException {
        return sql.getCon().prepareStatement(string);
    }

    public static ResultSet executeQuery(final PreparedStatement ps) throws SQLException {
        return sql.executeQuery(ps);
    }

    public static int executeUpdate(final PreparedStatement ps) throws SQLException {
        return sql.executeUpdate(ps);
    }

}
