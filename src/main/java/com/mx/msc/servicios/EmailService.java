/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.msc.servicios;

import com.mx.msc.utils.EnviaEmail;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sergiov
 */
@WebService(serviceName = "EmailService")
public class EmailService {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "enviaCorreo")
    public boolean hello(@WebParam(name = "remitente") String remitente, 
                        @WebParam(name = "destinatario") String destinatario,
                        @WebParam(name = "asunto") String asunto,
                        @WebParam(name = "mensaje") String mensaje) {
        return new EnviaEmail().enviaEmail(remitente, destinatario, asunto, mensaje);
    }
}
