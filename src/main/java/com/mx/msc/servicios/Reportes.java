/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.msc.servicios;

import com.mx.msc.utils.GeneraReporte;
import java.sql.SQLException;
import java.util.Map;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author chavon
 */
@WebService(serviceName = "Reportes")
public class Reportes {

    private final GeneraReporte generaReporte;

    public Reportes() {
        generaReporte = new GeneraReporte();
    }

    /**
     * This is a sample web service operation
     *
     * @param nombreReporte
     * @param parametros
     * @return
     */
    @WebMethod(operationName = "generaReporte")
    public String generaReporte(@WebParam(name = "nombreReporte", mode = WebParam.Mode.IN) String nombreReporte,
            @WebParam(name = "parametros", mode = WebParam.Mode.IN) Map<String, Object> parametros) {
        try {
            generaReporte.generaReporte(nombreReporte, parametros);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }

        return "Reporte Generado";
    }
}
