package org.bank.Account;

import org.bank.App;
import org.bank.DBManager.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AUpdate {
    public void accountupdate() throws SQLException {
        DBManager DB = new DBManager();
        org.bank.POJO.Account a = new org.bank.POJO.Account();
        Scanner scan = new Scanner(System.in);
        try {
            DB.connect();
            System.out.println("Enter Account No");
            a.setAccountNo(scan.nextInt());
            String query = "select * from account where Account_No=?";
            PreparedStatement p = DB.connection.prepareStatement(query);
//            System.out.println(query);
            p.setInt(1,a.getAccountNo());
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                System.out.println("Enter Amount");
                a.setAmout( scan.nextInt());
                System.out.println("Enter Pin Code");
               a.setPinCode( scan.nextInt());
                System.out.println("Enter Customer Id");
                a.setCustomerid(scan.nextInt());

                System.out.println("Enter Account Type");
                a.setAccountType(scan.next());
                a.setDateOfCreate();

                String sql = "UPDATE account SET  Pin_Code=?, Account_Type=? , Amount = ? , Customer_Customer_id=?   where Account_No = ?";
                PreparedStatement ps = DB.connection.prepareStatement(sql);
//                System.out.println(sql);
                ps.setInt(5, a.getAccountNo());
                ps.setInt(1, a.getPinCode());
                ps.setString(2, a.getAccountType());
                ps.setInt(3, a.getAmout());
                ps.setInt(4, a.getCustomerid());
                int rowsAffected = ps.executeUpdate();
                if(rowsAffected>0){
                    System.out.println("Account updated with Account No:"+a.getAccountNo());
                    new App().menu();
                }
        }
        }
        catch (Exception e) {
            System.out.println("Account Not found");
            try {
                accountupdate();
            }
            catch (Exception es){
                es.printStackTrace();
            }
        } finally {
            DB.connection.close();
        }

    }


}
