package org.bank.Customer;

import org.bank.App;
import org.bank.DBManager.DBManager;
import org.bank.POJO.Customer;
import org.bank.POJO.GetByPagination;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRead {
    Scanner scan = new Scanner(System.in);
    DBManager DB = new DBManager();
    GetByPagination g = new GetByPagination();
    Customer a = new Customer();
    public int tPage() throws SQLException {
        float pages = 0;
        String query = "Select count(Customer_id) from Customer";
        Statement st = DB.connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        try {
            if (rs.next()){
                //getting total rows from database
                float rows = rs.getInt(1);
                pages = rows/g.getItemsperPage();
                pages = Math.round(pages);


            }



        }
        catch (Exception e){
            e.printStackTrace();
        }
        // it return totalpages
        return (int) pages;
    }
    public void showbypage() throws SQLException {
        DB.connect();
        System.out.println("Enter current page");
        g.setCurrentPage(scan.nextInt());
        System.out.println("Enter items per page");
        g.setItemsperPage(scan.nextInt());
        if(tPage()<g.getCurrentPage()){
            System.out.println("Only "+tPage()+" Pages Exist");
            showbypage();
        }
        else{
            System.out.println("Sort By");
            g.setSortBy(scan.next());
            System.out.println("Enter Direction");
            g.setDirection(scan.next());
            int skip= g.getCurrentPage()*g.getItemsperPage();

            String sql = "SELECT * FROM customer ORDER BY "+g.getSortBy()+" "+g.getDirection()+" LIMIT "+g.getItemsperPage()+" OFFSET "+skip;
            Statement st = DB.connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            try {
                while(rs.next()){
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getString(2));
                    System.out.println(rs.getString(3));
                    System.out.println(rs.getString(4));
                    System.out.println(rs.getString(5));
                    System.out.println(rs.getString(6));
                    System.out.println(rs.getString(7));
                    System.out.println(rs.getString(8));
                }
                new App().menu();
            }
            catch(Exception e){
                System.out.println("Data Not Found");
                new App().menu();

            }

        }}

    public void read() throws SQLException {
        try {

            System.out.println("Enter Customer id");
            a.setCustomerId(scan.nextInt());
            // Getting Databse Connection
            DB.connect();
            String sql = "Select * from customer where Customer_id=? ";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ps.setInt(1, a.getCustomerId());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                a.setFirstName(rs.getString("First_Name"));
                a.setLastName( rs.getString("Last_Name"));
                a.setAddress(rs.getString("Address"));
                a.setCity(rs.getString("City"));
                a.setState(rs.getString("State"));
                a.setCountry( rs.getString("Country"));
                a.setContactNo(rs.getString("ContactNo"));
                System.out.println("Data Found");
                System.out.println("Customer Name: "+a.getFirstName()+" "+a.getLastName());
                System.out.println("City : "+a.getCity()+"\nState: "+a.getState()+"" +
                        "\nCountry: "+a.getCountry()+"\nAddress: "+a.getAddress());
                System.out.println("Phone No: "+a.getContactNo());

//                toString();

            }

        }
        catch (Exception e){
            System.out.println("Customer Not Found");
            try {
                read();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        }
        finally {
            if(DB.connection!=null) {
                DB.connection.close();
            }
        }
}



}
