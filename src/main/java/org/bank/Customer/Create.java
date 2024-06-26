package org.bank.Customer;

import org.bank.DBManager.DBManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class Create {
    public void customercreate() throws SQLException {
        DBManager DB = new DBManager();
        int customerId, contactNo;
        String firstName, lastName, city, state, country, address;
        org.bank.POJO.Customer a = new org.bank.POJO.Customer();
        Date currentDate = new Date();

        Scanner scan = new Scanner(System.in);
        try {
            DB.connect();
//            System.out.println("Enter Customer Id");
//            a.setCustomerId(scan.nextInt());
            System.out.println("Enter Contact No");
            a.setContactNo(scan.next());
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
            a.setAddress(scan.nextLine());

            String sql = "INSERT into Customer values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ps.setInt(1, a.getCustomerId());
            ps.setString(2, a.getFirstName());
            ps.setString(3, a.getLastName());
            ps.setString(4, a.getCity());
            ps.setString(5, a.getState());
            ps.setString(6, a.getCountry());
            ps.setString(7, a.getAddress());
            ps.setString(8, a.getContactNo());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected>0){
                System.out.println("Customer Created ");
            }


        } catch (Exception e) {
            System.out.println("Enter Correct Data");
            try {
               customercreate();
            }
            catch (Exception es){
                es.printStackTrace();
            }
        } finally {
            DB.connection.close();
        }

}


}
