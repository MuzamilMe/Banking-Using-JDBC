package org.bank.Customer;

import org.bank.DBManager.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CDelete {
    public void delete(){
        DBManager DB = new DBManager();
        //pojo class object
        org.bank.POJO.Customer a = new org.bank.POJO.Customer();
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Customer id");
           a.setCustomerId(scan.nextInt());
            // Getting Databse Connection
            DB.connect();
            String query = "select * from customer where Customer_id=?";
            PreparedStatement p = DB.connection.prepareStatement(query);
//            System.out.println(query);
            p.setInt(1,a.getCustomerId());
            ResultSet rs = p.executeQuery();
            if(rs.next()) {
                String sql = "DELETE from customer where Customer_id=?";
                PreparedStatement ps = DB.connection.prepareStatement(sql);
                ps.setInt(1, a.getCustomerId());
                int rowsaffected = ps.executeUpdate();
                if (rowsaffected > 0) {
                    System.out.println("Data Deleted");
                    System.out.println("Customer id: " + a.getCustomerId());
//                    System.out.println(sql);
                } else {
                    System.out.println("Enter Correct Data");
                    delete();
                }
            }
        }   //try off
        catch (Exception e){
            System.out.println("Customer id not found");
            try {
                //again calling method to rerun program on exception
                delete();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } // catch off
        finally {
            try {
                if(DB.connection!=null) {
                    DB.connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } // finally off
}


}
