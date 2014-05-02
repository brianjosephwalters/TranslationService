/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.translationservice.business;

import com.translationservice.db.TranslationDB;
import com.translationservice.dol.NonexistentOccupationException_Exception;
import com.translationservice.dol.Occupation;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bjw
 */
public class TranslationServiceImpl {
    private TranslationDB db;
    
    public TranslationServiceImpl() {
        this.db = new TranslationDB();
    }
    
    public List<Occupation> getCivilianOccupations(String branch, String MPC, String MOC) {
        List<String> occupationIDs = null;
        try {
            occupationIDs = db.getCivilianOccupationIDs(branch, MPC, MOC);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        List<Occupation> occupations = new ArrayList<Occupation>();
        if (occupationIDs != null) {
            for (String id : occupationIDs) {
                if (id != null) {
                    try {
                        occupations.add(getOccupation(id));
                    } catch (NonexistentOccupationException_Exception e) {
                        Occupation badOccupation = new Occupation();
                        badOccupation.setOccupationID(id);
                        badOccupation.setTitle("Bad OccupationID");
                        occupations.add(badOccupation);
                    }
                }
            }
        }
        return occupations;
    }

    private static Occupation getOccupation(java.lang.String occupationID) throws NonexistentOccupationException_Exception {
        com.translationservice.dol.DOLService_Service service = new com.translationservice.dol.DOLService_Service();
        com.translationservice.dol.DOLService port = service.getDOLServicePort();
        return port.getOccupation(occupationID);
    }
}
