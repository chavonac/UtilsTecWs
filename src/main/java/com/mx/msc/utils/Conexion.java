/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.msc.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author chavon
 */
public class Conexion {

//    public static Connection getMySQLConnection()
//            throws ClassNotFoundException, SQLException {
//        String hostName = "";
//        String dbName = "SIE_DB";
//        String userName = "admin";
//        String password = "admin";
//        return getMySQLConnection(hostName, dbName, userName, password);
//    }
//
//    public static Connection getMySQLConnection(String hostName, String dbName,
//            String userName, String password) throws SQLException,
//            ClassNotFoundException {
//
//        // Declare the class Driver for MySQL DB
//        // This is necessary with Java 5 (or older)
//        // Java6 (or newer) automatically find the appropriate driver.
//        // If you use Java> 5, then this line is not needed.
//        Class.forName("com.mysql.jdbc.Driver");
//
//        // Cấu trúc URL Connection dành cho Oracle
//        // Ví dụ: jdbc:mysql://localhost:3306/simplehr
//        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
//        
//        Connection conn = DriverManager.getConnection(connectionURL, userName,
//                password);
//        return conn;
//    }
    static Conexion conexion;
    Connection cn;

    public Conexion() {
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:app/jdbc/SIE_DB");
            cn = ds.getConnection();
        } catch (SQLException | NamingException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public static Conexion getInstance() {
        if (conexion == null) {
            conexion = new Conexion();
        }
        return conexion;
    }

    public Connection getCn() {
        return cn;
    }

    public void cerrar() {
        try {
            cn.close();
            conexion = null;
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
