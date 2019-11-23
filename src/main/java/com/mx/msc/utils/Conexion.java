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

    static Conexion conexion;
    Connection cn;

    public Conexion(String esquema) {
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup(esquema);
            cn = ds.getConnection();
        } catch (SQLException | NamingException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }

    public static Conexion getInstance(String esquema) {
        if (conexion == null) {
            conexion = new Conexion(esquema);
        }
        return conexion;
    }

    public Connection getCn() {
        return cn;
    }

    public void cerrar() {
        try {
            if (cn != null) {
                cn.close();
            }
            conexion = null;
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
