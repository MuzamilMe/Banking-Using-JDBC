package org.bank.Account;

import org.bank.DBManager.DBManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ADelete {
    public void delete(){
        DBManager DB = new DBManager();
        //pojo class object
        org.bank.POJO.Account a = new org.bank.POJO.Account();
        try {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter Account No");
            a.setAccountNo(scan.nextInt());
            // Getting Databse Connection
            DB.connect();
//            a.setAccountNo(Sid);
            String sql = "DELETE from account where Account_no=?";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ps.setInt(1, a.getAccountNo());
            int rowsaffected = ps.executeUpdate();
            if (rowsaffected > 0) {
                System.out.println("Data Deleted");
                System.out.println("Account No: " + a.getAccountNo());
                System.out.println(sql);
            } else {
                System.out.println("Account Not Found");
                delete();
            }
        }   //try off
        catch (Exception e){
            e.printStackTrace();

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
}}

