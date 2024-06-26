package org.bank.Customer;

import org.bank.App;
import org.bank.DBManager.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CUpdate {
    public void customerupdate() throws SQLException {
        DBManager DB = new DBManager();
        org.bank.POJO.Customer a = new org.bank.POJO.Customer();
        Scanner scan = new Scanner(System.in);
        try {
            DB.connect();
            System.out.println("Enter Customer id");
            a.setCustomerId(scan.nextInt());
            String query = "select * from customer where Customer_id=?";
            PreparedStatement p = DB.connection.prepareStatement(query);
//            System.out.println(query);
            p.setInt(1,a.getCustomerId());
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                System.out.println("Enter First Name");
                a.setFirstName(scan.next());
                System.out.println("Enter Last Name");
                a.setLastName(scan.next());
                System.out.println("Enter City");
                a.setCity(scan.next());
                System.out.println("Enter State");
                a.setState(scan.next());
                System.out.println("Enter Country");
                a.setCountry(scan.next());
                System.out.println("Enter Address");
                a.setAddress(scan.nextLine());
                System.out.println("Enter Contact No");
                a.setContactNo(scan.next());

                String sql = "UPDATE customer SET  First_Name=?, Last_Name=? , City = ? , State=? , country=? , Address=? , ContactNo=?   where Customer_id = ?";
                PreparedStatement ps = DB.connection.prepareStatement(sql);
//                System.out.println(sql);
                ps.setInt(8, a.getCustomerId());
                ps.setString(1, a.getFirstName());
                ps.setString(2, a.getLastName());
                ps.setString(3, a.getCity());
                ps.setString(4, a.getState());
                ps.setString(5, a.getCountry());
                ps.setString(6,a.getAddress());
                ps.setString(7,a.getContactNo());
                int rowsAffected = ps.executeUpdate();
                if(rowsAffected>0){
                    System.out.println("Customer updated with Id No:"+a.getCustomerId());
                    new App().menu();
                }
                else{
                    System.out.printf("Not updated");
                }

            }
            else {
                System.out.println("Account not found");
               customerupdate();

            }
        }
        catch (Exception e) {
            e.printStackTrace();
            try {
                customerupdate();
            }
            catch (Exception es){
                es.printStackTrace();
            }
        } finally {
            DB.connection.close();
        }

    }



}
