package Service;

import org.bank.core.Account_Type;
import org.bank.core.Transaction_Type;
import org.bank.helper.DBManager;
import org.bank.POJO.GetByPagination;
import org.bank.POJO.Transaction;
import org.bank.core.Response;
import org.bank.DAO.TransactionDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class serviceTransaction {
    static Scanner scan = new Scanner(System.in);
    static Transaction transaction = new Transaction();
    static DBManager DB = new DBManager();

    public static Response serviceCreate() throws SQLException {
        Response response = new Response();
        DB.getConnection();
        System.out.println("Enter Sender Account No");
        transaction.setSender_Acc(scan.nextInt());
        String sql = "select * from account Where Account_No=?";
        PreparedStatement ps = DB.getConnection().prepareStatement(sql);
        ps.setInt(1, transaction.getSender_Acc());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            System.out.println("Enter Transaction Amount");
            transaction.setAmount(scan.nextInt());
            if (rs.getInt("amount") < transaction.getAmount()) {
                System.out.println("Insufficient Balance");
                serviceCreate();
            } else {
                System.out.println("Enter Receiver ServiceAccount");
                transaction.setReciever_Acc(scan.nextInt());
                System.out.println("Enter Transfer Type\n1.DEBIT\n2.CREDIT");
                transaction.setTransferType(scan.next());
                if(transaction.getTransferType().equals("1")){
                    transaction.setTransferType(String.valueOf(Transaction_Type.DEBIT));
                } else if (transaction.getTransferType().equals("2")) {
                    transaction.setTransferType(String.valueOf(Transaction_Type.CREDIT));
                }
                else{
                    response.check(1,"Incorrect selected type");
                }
                transaction = TransactionDAO.create(transaction);
                if (transaction == null) {
                    response.check(1, "Got Exception");
                } else {
                    response.check(0, "Data Entered Successfully", transaction);
                }
            }
        } else {
            System.out.println("Sender Not found");
            serviceCreate();
        }
        return response;
    }

    public static Response ServicepRead() throws SQLException {
        Response response = new Response();
        GetByPagination getByPagination = new GetByPagination();
        List<Transaction> list;
        System.out.println("Enter current page");
        getByPagination.setCurrentPage(scan.nextInt());
        System.out.println("Enter items per page");
        getByPagination.setItemsperPage(scan.nextInt());
        System.out.println("Sort By");
        getByPagination.setSortBy(scan.next());
        System.out.println("Enter Direction");
        getByPagination.setDirection(scan.next());
        list = TransactionDAO.showbypage(getByPagination, transaction);
        if (list == null) {
            response.check(1, "Got Exception");
        } else {
            response.check(0, "Data Got Successfully", list);
        }
        return response;
    }

    public static Response serviceRead() throws SQLException {
        Response response = new Response();
        System.out.println("Enter Transaction id AS TRXID-id-10-JAN-1980");
        transaction.setTransectionId(scan.next().toUpperCase());
        transaction = TransactionDAO.read(transaction);
        return response;
    }

    public static Response serviceReadAll() throws SQLException {
        Response response = new Response();
        List<Transaction> list;
        list = TransactionDAO.showall();
        if (list == null) {
            response.check(1, "Got Exception");
        } else {
            response.check(0, "Successfully", list);
        }
        return response;


    }
}

