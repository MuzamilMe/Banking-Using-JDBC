package org.bank.Transaction;


import org.bank.App;
import org.bank.DBManager.DBManager;
import org.bank.POJO.GetByPagination;
import org.bank.POJO.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TRead {
    Scanner scan = new Scanner(System.in);
    DBManager DB = new DBManager();

    //pojo class object
    Transaction a = new Transaction();
    GetByPagination g = new GetByPagination();
    public int tPage() throws SQLException {
        float pages = 0;
        String query = "Select count(Transaction_id) from Transaction";
        Statement st = DB.connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        try {
        if (rs.next()){
            //getting total rows from database
            float rows = rs.getInt(1);
            pages = rows/g.getItemsperPage();
            pages = Math.round(pages);


        }

        // it return totalpages

            }
        catch (Exception e){
            e.printStackTrace();
        }
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

        String sql = "SELECT * FROM TRANSACTION ORDER BY "+g.getSortBy()+" "+g.getDirection()+" LIMIT "+g.getItemsperPage()+" OFFSET "+skip;
        Statement st = DB.connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        try {
        while(rs.next()){
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getInt(4));
            System.out.println(rs.getInt(5));
            System.out.println(rs.getString(6));
        }
            new App().menu();
    }
        catch(Exception e){
            System.out.println("Data Not Found");
            new App().menu();

        }

        }
    }
    public void showall() throws SQLException {
        DB.connect();
        String sql = "Select * from transaction";
        PreparedStatement ps = DB.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            a.setTransferType(rs.getString("Transfer_Type"));
            String date = rs.getString("date_of_Transfer");
            a.setAmount(rs.getInt("Amount"));
            a.setSender_Acc(rs.getInt("Sender_Account_No"));
            System.out.println("Transfer Type: " + a.getTransferType() + "\nDate of Transfer " + date);
            System.out.println("Amount : " + a.getAmount() + "\nSender Account: " + a.getSender_Acc());
        }
        new App().menu();
    }
    public void read() throws SQLException {

        try {

            System.out.println("Enter Transaction id AS TRXID-id-10-JAN-1980");
            a.setTransectionId(scan.next().toUpperCase());
            // Getting Databse Connection
            DB.connect();
            String sql = "Select * from transaction where Transaction_id=? ";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ps.setString(1, a.getTransectionId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                a.setTransferType(rs.getString("Transfer_Type"));
                String date = rs.getString("date_of_Transfer");
                a.setAmount(rs.getInt("Amount"));
                a.setSender_Acc(rs.getInt("Sender_Account_No"));
                System.out.println("Data Found");
                System.out.println("Transfer Type: " + a.getTransferType() + "\nDate of Transfer " + date);
                System.out.println("Amount : " +a.getAmount()  + "\nSender Account: " + a.getSender_Acc());
                System.out.println();
                new App().menu();
            } else {
                System.out.println("Data Not Found");
                new App().menu();
            }
            System.out.println(sql);
        } catch (Exception e) {
            System.out.println("Enter correct TRX id as TRXID-id-10-JAN-1980");
            read();
        } finally {
            if (DB.connection != null) {
                DB.connection.close();
            }
        }}


}

