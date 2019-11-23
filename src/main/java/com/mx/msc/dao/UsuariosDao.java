/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mx.msc.dao;

import com.mx.msc.utils.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author chavon
 */
public class UsuariosDao {
    
        public int getValidaUsuario(String correo, String password) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs;
        int total = 0;
        try {
            StringBuilder query = new StringBuilder();
            query.append("select count(*) as total \n"
                    + "from titulatec.usuarios\n"
                    + "where email = ?\n"
                    + "and clave = ? ");
            ps = Conexion.getInstance("java:app/jdbc/SIE_DB").getCn().prepareStatement(query.toString());
            ps.setString(1, correo);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return total;
    }
}
