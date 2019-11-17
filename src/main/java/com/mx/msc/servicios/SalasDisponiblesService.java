/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.msc.servicios;

import com.mx.msc.dao.SalasDisponiblesDao;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sergiov
 */
@WebService(serviceName = "SalasDisponiblesService")
public class SalasDisponiblesService {

    private SalasDisponiblesDao dao;

    public SalasDisponiblesService() {
        dao = new SalasDisponiblesDao();
    }

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "consultaSalasDisponibles")
    public List<Object[]> consultaSalasDisponibles(@WebParam(name = "fechaPresentacion") String fechaPresentacion,
            @WebParam(name = "horaInicio") String horaInicio,
            @WebParam(name = "horaFin") String horaFin) throws Exception {
        return dao.getSalasDisponibles(fechaPresentacion, horaInicio, horaFin);
    }
}
