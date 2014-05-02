/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.translationservice;

import com.translationservice.business.TranslationServiceImpl;
import com.translationservice.dol.Occupation;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author bjw
 */
@WebService(serviceName = "TranslationService")
public class TranslationService {
    private TranslationServiceImpl impl;
    public TranslationService() {
        this.impl = new TranslationServiceImpl();
    }
    
    @WebMethod(operationName = "getCivilianOccupations")
    public List<Occupation> getCivilianOccupations(
            @WebParam(name = "branch") String branch, 
            @WebParam(name = "mpc") String MPC, 
            @WebParam(name = "moc") String MOC) {
        return impl.getCivilianOccupations(branch, MPC, MOC);
    }
    
}
