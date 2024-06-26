package org.bank.Account;

import org.bank.App;
import org.bank.DBManager.DBManager;
import org.bank.POJO.Account;
import org.bank.POJO.GetByPagination;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ARead {
    Scanner scan = new Scanner(System.in);
    DBManager DB = new DBManager();
    GetByPagination g = new GetByPagination();
    public void read() throws SQLException {
        //pojo class object
        Account a = new Account();
        try {
            System.out.println("Enter Account No");
            a.setAccountNo(scan.nextInt());
            // Getting Databse Connection
            DB.connect();
            String sql = "Select * from account where Account_No=? ";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ps.setInt(1, a.getAccountNo());
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String DOC = rs.getString("Date_Of_Create");
                a.setAccountType(rs.getString("Account_Type"));
                a.setCustomerid(rs.getInt("Customer_Customer_id"));
                a.setAmout(rs.getInt("Amount"));
                a.setPinCode(rs.getInt("pin_Code"));
                System.out.println("Account Found");
                System.out.println("Account No: "+a.getAccountNo()+"\nAccount Type: "+a.getAccountType()+"\n" +
                        "Amount: "+a.getAmout()+"\nCustomer_id: "+a.getCustomerid()+"\nPin Code: "+a.getPinCode()+"\nDate of create"+DOC);
                new App().menu();
            }
//            System.out.println(sql);
        }
        catch (Exception e){
            System.out.println("Account Not Found");
            read();
        }
        finally {
            if(DB.connection!=null) {
                DB.connection.close();
            }
        }}
    public int tPage() throws SQLException {
        float pages = 0;
        String query = "Select count(Account_No) from account";
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

            String sql = "SELECT * FROM account ORDER BY "+g.getSortBy()+" "+g.getDirection()+" LIMIT "+g.getItemsperPage()+" OFFSET "+skip;
            Statement st = DB.connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            try {
                while(rs.next()){
                    System.out.println(rs.getInt(1));
                    System.out.println(rs.getInt(2));
                    System.out.println(rs.getDate(3));
                    System.out.println(rs.getString(4));
                    System.out.println(rs.getInt(5));
                    System.out.println(rs.getInt(6));
                }
                new App().menu();
            }
            catch(Exception e){
                System.out.println("Data Not Found");
                new App().menu();

            }

        }


}}

