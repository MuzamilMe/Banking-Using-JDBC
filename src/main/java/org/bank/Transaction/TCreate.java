package org.bank.Transaction;

import org.bank.DBManager.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TCreate {
      Scanner scan = new Scanner(System.in);
    org.bank.POJO.Transaction a = new org.bank.POJO.Transaction();
    DBManager DB = new DBManager();
    public void addition(int Tamount,int accountNo) throws SQLException {
        // credit
        String q = "select Amount from account where Account_No=" + accountNo;
        Statement statement=DB.connection.createStatement();
        ResultSet rs=statement.executeQuery(q);
        if (rs.next()){
            System.out.println("done");
            System.out.println(rs.getInt("amount"));
            int remAmount = rs.getInt("amount")+ Tamount;
            String qq = "update account set Amount=? where Account_No=?";
            System.out.println("Amount transferred"+a.getAmount());
            PreparedStatement ps=DB.connection.prepareStatement(qq);
            ps.setInt(1,remAmount);
            ps.setInt(2,accountNo);
            ps.executeUpdate();
        }

    }
    public void deduct(int Tamount,int accountNo) throws SQLException {
        String q = "select Amount from account where Account_No=" + accountNo;
        Statement statement=DB.connection.createStatement();
        ResultSet rs=statement.executeQuery(q);
        if (rs.next()){
            System.out.println("done");
            System.out.println(rs.getInt("amount"));
            int remAmount = rs.getInt("amount")- Tamount;
            String qq = "update account set Amount=? where Account_No=?";
            PreparedStatement ps=DB.connection.prepareStatement(qq);
            ps.setInt(1,remAmount);
            ps.setInt(2,accountNo);
            ps.executeUpdate();
        }

    }
    public void setTransaction() throws SQLException {
        int counter=1;
        String tId = "TRXID-"+counter+"-"+a.getDateOfTransfer();
        a.setTransectionId(tId);
         String qry = "select Transaction_id from transaction";
            PreparedStatement s1 = DB.connection.prepareStatement(qry);
            ResultSet resultSet = s1.executeQuery(qry);
            while(resultSet.next()){
                if(resultSet.getString("Transaction_id").equals(a.getTransectionId())){
                    counter++;
                    tId = "TRXID-"+counter+"-"+a.getDateOfTransfer();
                    a.setTransectionId(tId);

                }

            }
         a.setDateOfTransfer();
        String qy = "Insert into transaction values(?,?,?,?,?,?)";
        PreparedStatement l = DB.connection.prepareStatement(qy);
        l.setString(1, tId);
        l.setString(2, a.getTransferType());
        l.setString(3, a.getDateOfTransfer());
        l.setInt(4, a.getAmount());
        l.setInt(5, a.getSender_Acc());
        l.setInt(6,a.getReciever_Acc());
        l.executeUpdate();
        System.out.println("Transaction done");
        System.out.println(a.getDateOfTransfer());
        System.out.println(a.getSender_Acc());
        System.out.println(a.getTransectionId());
    }
    public void create() throws SQLException {
        try {
            DB.connect();
            System.out.println("Enter Sender Account No");
            a.setSender_Acc(scan.nextInt());
            String sql = "select * from account Where Account_No=?";
            PreparedStatement ps = DB.connection.prepareStatement(sql);
            ps.setInt(1, a.getSender_Acc());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                System.out.println("Enter Transaction Amount");
                a.setAmount(scan.nextInt());
                if (rs.getInt("amount") < a.getAmount()) {
                    System.out.println("Insufficient Balance");
                    create();
                }
                else {
                    System.out.println("Enter Receiver Account");
                    a.setReciever_Acc(scan.nextInt());
                    System.out.println("Enter Transfer Type");
                    a.setTransferType(scan.next());
                    String s = "select * from account where Account_No=?";
                    PreparedStatement p = DB.connection.prepareStatement(s);
                    p.setInt(1, a.getReciever_Acc());
                    ResultSet rx = p.executeQuery();
                    if (rx.next()) {
                        deduct(a.getAmount(),a.getSender_Acc());
                        addition(a.getAmount(),a.getReciever_Acc());

                    }
                    else{
                        System.out.println("Receiver Not Found");
                        create();
                    }

                }
            }
            else{
                System.out.println("Sender Account Not found");
                create();
            }
        }
        catch (Exception e) {
            System.out.println("Re Enter Data You Got an Error ");
            try {
              create();
            }
            catch (Exception es){
                es.printStackTrace();
            }
        } finally {
            DB.connection.close();
        }

}
}
