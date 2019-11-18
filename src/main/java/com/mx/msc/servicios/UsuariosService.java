/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.msc.servicios;

import com.mx.msc.dao.UsuariosDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author chavon
 */
@WebService(serviceName = "UsuariosService")
public class UsuariosService {

    @WebMethod(operationName = "validaUsuario")
    public int validaUsuario(@WebParam(name = "correo") String correo,
            @WebParam(name = "password") String password) {
        UsuariosDao dao = new UsuariosDao();
        int total = 0;
        try {
            total = dao.getValidaUsuario(correo, password);
        } catch (Exception ex) {
            Logger.getLogger(UsuariosService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

}
