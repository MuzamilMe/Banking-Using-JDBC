package org.bank.Account;

import org.bank.App;
import org.bank.DBManager.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class ACreate {
    public void accountcreate() throws SQLException {
        DBManager DB = new DBManager();
        org.bank.POJO.Account a = new org.bank.POJO.Account();
        Date currentDate = new Date();
        Scanner scan = new Scanner(System.in);
        try {
            DB.connect();
            System.out.println("Enter Customer Id");
            int Cid = scan.nextInt();
            String query = "select * from customer where Customer_id = ?";
            PreparedStatement p = DB.connection.prepareStatement(query);
            p.setInt(1,Cid);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
            System.out.println("Enter Amount");
            int amount = scan.nextInt();
            System.out.println("Enter Pin Code");
            int pin = scan.nextInt();
            System.out.println("Enter Account No");
            int accountno = scan.nextInt();
            System.out.println("Enter Account Type");
            String accounttype = scan.next();
            a.setAmout(amount);
            a.setCustomerid(Cid);
            a.setPinCode(pin);
            a.setDateOfCreate();
            a.setAccountType(accounttype);
            a.setAccountNo(accountno);
            String sql = "INSERT into account values(?,?,?,?,?,?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ps.setInt(1, a.getAccountNo());
            ps.setInt(2, a.getPinCode());
            ps.setString(3, a.getDateOfCreate());
            ps.setString(4, a.getAccountType());
            ps.setInt(5, a.getAmout());
            ps.setInt(6, a.getCustomerid());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Account created with Account No:"+a.getAccountNo());
                new App().menu();
            }}
            else {
                System.out.println("Customer id not found");
                accountcreate();
            }


        } catch (Exception e) {
            e.printStackTrace();
            try {
               accountcreate();
            }
            catch (Exception es){
                es.printStackTrace();
            }
        } finally {
            DB.connection.close();
        }

}
}
