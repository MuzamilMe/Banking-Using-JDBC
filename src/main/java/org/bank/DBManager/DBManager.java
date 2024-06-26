package org.bank.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {
    public Connection connection;
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","12345");
//            System.out.println("Connection Success");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


}
