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
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.olap4j.impl.ArrayMap;

/**
 *
 * @author chavon
 */
public class GeneraReporte {

    public String generaReporte(String nombreReporte, String extension, Map<String, Object> parametrosReporte) throws ClassNotFoundException, SQLException {
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        String reporte = null;
        try {
            Connection conn = Conexion.getInstance().getCn();
            jasperReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream(File.separator.concat(nombreReporte).concat(".jrxml")));
            jasperPrint = JasperFillManager.fillReport(jasperReport, getParamtersJasper(jasperReport, parametrosReporte), conn);
            reporte = creaReporte(jasperPrint, extension);
        } catch (JRException e) {
            System.out.println("Error titulatec:" + e.getMessage());
        } finally {
            Conexion.getInstance().cerrar();
        }
        return reporte;
    }

    private Map<String, Object> getParamtersJasper(JasperReport jasperReport, Map<String, Object> parametrosReporte) {
        Map<String, Object> paramsTmp = new ArrayMap<>();
        JRParameter[] jRParameter;
        try {
            jRParameter = jasperReport.getParameters();
            for (JRParameter parameter : jRParameter) {
                String nombreParametro = parameter.getName();
                if (nombreParametro.startsWith("P_")) {
                    String valor = parametrosReporte.get(nombreParametro).toString();
                    paramsTmp.put(nombreParametro, valor);
                }
            }
        } catch (Exception e) {
            System.out.println("Error titulatec:" + e.getMessage());
        }
        return paramsTmp;
    }

    private String creaReporte(JasperPrint jasperPrint, String extension) {
        byte[] bs = null;
        String reporte = null;
        try {
            if (extension.equals("pdf")) {
                bs = JasperExportManager.exportReportToPdf(jasperPrint);
            }
            if (bs != null) {
                reporte = new sun.misc.BASE64Encoder().encode(bs);
            }
        } catch (Exception e) {
            System.out.println("Error titulatec:" + e.getMessage());
        }
        return reporte;
    }

}
