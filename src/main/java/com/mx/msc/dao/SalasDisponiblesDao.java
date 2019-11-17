/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.msc.dao;

import com.mx.msc.utils.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergiov
 */
public class SalasDisponiblesDao {

    public List<Object[]> getSalasDisponibles(String fechaPresentacion, String horaInicio, String horaFin) throws Exception {
        List<Object[]> lista = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[] headers = new String[]{"idSala", "cveSala", "descripcion"};
        try {
            StringBuilder query = new StringBuilder();
            query.append("SELECT * FROM SALAS ");
            query.append("WHERE ID_SALA NOT IN( ");
            query.append("SELECT id_sala FROM ACTOS ");
            query.append("WHERE fecha_presentacion =  str_to_date(?, '%Y-%m-%d') ");
            query.append("and hora_inicio between str_to_date(?, '%H:%i:%s') and   str_to_date(?,'%H:%i:%s') ");
            query.append("or hora_fin between str_to_date(?, '%H:%i:%s') and   str_to_date(?,'%H:%i:%s'))");

            ps = Conexion.getInstance().getCn().prepareStatement(query.toString());
            ps.setString(1, fechaPresentacion);
            ps.setString(2, horaInicio);
            ps.setString(3, horaFin);
            ps.setString(4, horaInicio);
            ps.setString(5, horaFin);

            rs = ps.executeQuery();

            while (rs.next()) {
                Object[] record = new Object[headers.length];
                for (int i = 0, idxColumn = 1; i < headers.length; i++, idxColumn++) {
                    record[i] = rs.getObject(idxColumn);
                }
                lista.add(record);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return lista;
    }
}
