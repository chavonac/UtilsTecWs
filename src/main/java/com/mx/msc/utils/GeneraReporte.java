/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.msc.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author chavon
 */
public class GeneraReporte {

    public void generaReporte(String nombreReporte, Map<String, Object> parametrosReporte) throws ClassNotFoundException, SQLException {
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        String rutaOrigenJRXML = "/home/chavon/workspace-project/JasperReports-MSC/src/resources/jaspers/";
        Connection conn = null;
        try {
            conn = Conexion.getInstance().getCn();
            jasperReport = JasperCompileManager.compileReport(rutaOrigenJRXML.concat(nombreReporte).concat(".jrxml"));
            jasperPrint = JasperFillManager.fillReport(jasperReport, parametrosReporte, conn);
            creaReporte(nombreReporte, jasperPrint, parametrosReporte.get("extension").toString());
        } catch (JRException e) {
            System.out.println("Error:" + e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }

    private void creaReporte(String nombreReporte, JasperPrint jasperPrint, String extension) {
        File directorioSalida;
        String rutaReporteSalida = "/home/chavon/workspace-project/JasperReports-MSC/src/resources/jaspers/";
        try {
            directorioSalida = new File(rutaReporteSalida);
            directorioSalida.mkdir();
            if (extension.equals("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, rutaReporteSalida.concat(nombreReporte).concat(".pdf"));
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

    }

}
