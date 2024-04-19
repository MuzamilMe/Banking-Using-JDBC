package org.bank.DAO;

import org.bank.helper.DBManager;
import org.bank.POJO.GetByPagination;
import org.bank.POJO.Transaction;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class TransactionDAO {
    static Date date = Calendar.getInstance().getTime();
    static DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    static String now = dateFormat.format(date);
    //Transaction create
    static Transaction transaction = new Transaction();
    static DBManager DB = new DBManager();
    static GetByPagination g = new GetByPagination();
    public static void addition(int tAmount, int accountNo) throws SQLException {
        Transaction t = new Transaction();
        // credit
        String sql = "select Amount from account where Account_No=" + accountNo;
        Statement statement = DB.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            int remAmount = rs.getInt("amount") + tAmount;
            String query = "update account set Amount=? where Account_No=?";
            PreparedStatement ps = DB.getConnection().prepareStatement(query);
            ps.setInt(1, remAmount);
            ps.setInt(2, accountNo);
            ps.executeUpdate();
        }

    }

    public static void deduct(int tAmount, int accountNo) throws SQLException {
        DB.getConnection();
        String query = "select Amount from account where Account_No=" + transaction.getReciever_Acc();
        Statement statement = DB.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            int remAmount = rs.getInt("amount") - tAmount;
            String sql = "update account set Amount=? where Account_No=?";
            PreparedStatement ps = DB.getConnection().prepareStatement(sql);
            ps.setInt(1, remAmount);
            ps.setInt(2, accountNo);
            ps.executeUpdate();
        }

    }

    public static void perform_Transaction(Transaction transaction) throws SQLException {
        DB.getConnection();
        int counter = 1;
        String tId = "TRXID-" + counter + "-" + transaction.getDateOfTransfer();
        transaction.setTransectionId(tId);
        String qry = "select Transaction_id from transaction";
        PreparedStatement s1 = DB.getConnection().prepareStatement(qry);
        ResultSet resultSet = s1.executeQuery(qry);
        transaction.setDateOfTransfer(now);
        while (resultSet.next()) {
            if (resultSet.getString("Transaction_id").equals(transaction.getTransectionId())) {
                counter++;
                tId = "TRXID-" + counter + "-" + transaction.getDateOfTransfer();
                transaction.setTransectionId(tId);
            }
        }

        String sql = "Insert into transaction values(?,?,?,?,?,?)";
        PreparedStatement l = DB.getConnection().prepareStatement(sql);
        l.setString(1, transaction.getTransectionId());
        l.setString(2, transaction.getTransferType());
        l.setString(3, transaction.getDateOfTransfer());
        l.setInt(4, transaction.getAmount());
        l.setInt(5, transaction.getSender_Acc());
        l.setInt(6, transaction.getReciever_Acc());
        l.executeUpdate();

    }

    public static Transaction create(Transaction transaction) throws SQLException {
        try {
            perform_Transaction(transaction);
            DB.getConnection();
            String sql = "select * from account where Account_No=?";
            PreparedStatement p = DB.getConnection().prepareStatement(sql);
            p.setInt(1, transaction.getReciever_Acc());
            ResultSet rx = p.executeQuery();
            if (rx.next()) {
                deduct(transaction.getAmount(), transaction.getSender_Acc());
                addition(transaction.getAmount(), transaction.getReciever_Acc());
            } else {
                transaction = null;
                System.out.println("Receiver Not Found");
            }

        } catch (SQLException ex) {
            transaction = null;
            throw new RuntimeException(ex);
        }
        return transaction;
    }

    public static int tPage() throws SQLException {
        float pages = 0;
        String query = "Select count(Transaction_id) from Transaction";
        Statement st = DB.getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        try {
            if (rs.next()) {
                //getting total rows from database
                float rows = rs.getInt(1);
                pages = rows / g.getItemsperPage();
                pages = Math.round(pages);
            }

            // it return totalpages

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (int) pages;
    }

    public static List<Transaction> showbypage(GetByPagination g, Transaction t) throws SQLException {
        List<Transaction> list = new ArrayList<Transaction>();
        DB.getConnection();
        if (tPage() < g.getCurrentPage()) {
            System.out.println("Only " + tPage() + " Pages Exist");
        } else {
            int skip = g.getCurrentPage() * g.getItemsperPage();
            try {
                String sql = "SELECT * FROM TRANSACTION ORDER BY " + g.getSortBy() + " " + g.getDirection() + " LIMIT " + g.getItemsperPage() + " OFFSET " + skip;
                Statement st = DB.getConnection().createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    t.setTransectionId(rs.getString(1));
                    t.setTransferType(rs.getString(2));
                    t.setDateOfTransfer(rs.getString(3));
                    t.setAmount(rs.getInt(4));
                    t.setSender_Acc(rs.getInt(5));
                    t.setReciever_Acc(rs.getInt(6));
                    list.add(t);

                }


            } catch (Exception e) {
                list = null;
                e.printStackTrace();
                System.out.println("Error ");

            }

        }
        return list;
    }

    public static List<Transaction> showall() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        DB.getConnection();
        String sql = "Select * from transaction";
        PreparedStatement ps = DB.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            transaction.setTransectionId(rs.getString("Transaction_id"));
            transaction.setTransferType(rs.getString("Transfer_Type"));
            transaction.setDateOfTransfer(rs.getString("date_of_Transfer"));
            transaction.setAmount(rs.getInt("Amount"));
            transaction.setSender_Acc(rs.getInt("Sender_Account_No"));
            transaction.setReciever_Acc(rs.getInt("Reciever_Account_no"));
            list.add(transaction);
            transaction = new Transaction();

        }
        return list;
    }

    public static Transaction read(Transaction transaction) throws SQLException {
        try {
            // Getting Databse getConnection()ion
            DB.getConnection();

            String sql = "Select * from transaction where Transaction_id='"+transaction.getTransectionId()+"'";
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            if (rs.getString("Transaction_id")== transaction.getTransectionId()) {
                transaction.setTransferType(rs.getString("Transfer_Type"));
                transaction.setDateOfTransfer(rs.getString("date_of_Transfer"));
                transaction.setAmount(rs.getInt("Amount"));
                transaction.setSender_Acc(rs.getInt("Sender_Account_No"));
                transaction.setSender_Acc(rs.getInt("Reciever_Account_No"));
            } else {
                transaction = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction = null;
            System.out.println("Enter correct TRX id as TRXID-id-10-JAN-1980");
        } finally {
            if (DB.getConnection() != null) {
                DB.getConnection().close();
            }
        }
        return transaction;
    }

}
