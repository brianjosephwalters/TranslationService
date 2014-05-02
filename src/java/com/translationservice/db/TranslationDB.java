/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.translationservice.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bjw
 */
public class TranslationDB {
    //connection
    public Connection dbConnection() {
    Connection conn = null;
    String url = "jdbc:oracle:thin:@dbsvcs.cs.uno.edu:1521:orcl";

        String driver = "oracle.jdbc.driver.OracleDriver";
        String userName = "jphobbs"; 
        String password = "fPCHfZZn";
        try {
              Class.forName(driver).newInstance();
              conn = DriverManager.getConnection(url,userName, password);
              } catch (ClassNotFoundException | 
                       InstantiationException | 
                       IllegalAccessException | 
                       SQLException e) {
                      e.printStackTrace();
              }
              return conn;
    }
    
    public List<String> getCivilianOccupationIDs(String branch, String MPC, String MOC) 
            throws SQLException {
        Connection connection = this.dbConnection();
        String query = " SELECT * FROM jh_translate WHERE " +
                       " svc = ? AND mpc = ? AND moc = ? ";
        
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, branch);
        stmt.setString(2, MPC);
        stmt.setString(3, MOC);
        ResultSet results = stmt.executeQuery();
        
        List<String> list = new ArrayList<String>();
        while (results.next()) {
            list.add(results.getString("onet1"));
            list.add(results.getString("onet2"));
            list.add(results.getString("onet3"));
            list.add(results.getString("onet4"));
        }
        return list;
    }
}
