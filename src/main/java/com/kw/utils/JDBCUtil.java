package com.kw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCUtil {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/KW";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "123456";
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws Exception {
        Class.forName(DB_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        return conn;
    }

    public static PreparedStatement getPreparedStatement(Connection conn, String sql) throws Exception {
        PreparedStatement pstmt = conn.prepareStatement(sql);
        return pstmt;
    }

    public static void closeConnection(Connection conn, PreparedStatement pstmt) throws Exception {
        if (pstmt!= null) {
            pstmt.close();
        }
        if (conn!= null) {
            conn.close();
        }
    }
}
