package Service;

import org.bank.DAO.AccountDAO;
import org.bank.POJO.Account;
import org.bank.POJO.GetByPagination;
import org.bank.core.Account_Type;
import org.bank.core.Response;
import org.bank.helper.DBManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import static Service.serviceTransaction.serviceCreate;
public class ServiceAccount {

    static GetByPagination getByPagination = new GetByPagination();
    static Scanner scan = new Scanner(System.in);
     static Account account = new Account();
    static DBManager DB = new DBManager();
    public static Response Servicecreate() throws SQLException {
//        Account account = new Account();
        Response response = new Response();
        try {
            System.out.println("Enter Customer Id");
            account.setCustomerid(scan.nextInt());
            System.out.println("Enter Amount");
            account.setAmout(scan.nextInt());
            System.out.println("Enter Pin Code");
            account.setPinCode(scan.nextInt());
            System.out.println("Enter Account No");
            account.setAccountNo(scan.nextInt());
            System.out.println("SELECT ACCOUNT TYPE\n1.CURRENT \n2.SAVINGS");
            account.setAccountType(scan.next());
            if(account.getAccountType().equals("1")){
              account.setAccountType(String.valueOf(Account_Type.CURRENT));
            } else if (account.getAccountType().equals("2")) {
                account.setAccountType(String.valueOf(Account_Type.SAVINGS));

            }
            else{
                response.check(1,"Incorrect selected type");
            }

            account = AccountDAO.accountcreate(account);
            if (account == null) {
                response.check(1, "Got Exception");
            } else {
                response.check(0, "Data Entered Successfully", account);
            }
        } catch (Exception e) {
            account = null;
            serviceCreate();
        }
        return response;
    }


    public static Response Serviceread() {
        boolean flag =false;
        Response response = new Response();
        try {
            System.out.println("Enter Account No");
            account.setAccountNo(scan.nextInt());
             flag =AccountDAO.check_Account(account.getAccountNo());
            if (!flag) {
                account=null;
                response.check(1, "Got Exception");
            } else {
                account=AccountDAO.read(account);
                response.check(0, "Successfully", account);
            }
        } catch (Exception e) {
            account = null;
        }
        return response;
    }

    public static Response Servicepage() throws SQLException {
//        Account account = new Account();
        Response response = new Response();
        List<Account> list;
        System.out.println("Enter current page");
        getByPagination.setCurrentPage(scan.nextInt());
        System.out.println("Enter items per page");
        getByPagination.setItemsperPage(scan.nextInt());
        System.out.println("Sort By");
        getByPagination.setSortBy(scan.next());
        System.out.println("Enter Direction");
        getByPagination.setDirection(scan.next());
        list = AccountDAO.showbypage(getByPagination);
        if (list == null) {
            response.check(1, "Got Exception");
        } else {
            response.check(0, "Data Entered Successfully", account);
        }
        return response;
    }

    public static Response servicedelete(){
//        Account account = new Account();
        Response response = new Response();
        System.out.println("EnterAccount No");
        account.setAccountNo(scan.nextInt());
        account = AccountDAO.delete(account);
        if (account == null) {
            response.check(1, "Got Exception");
        } else {
            response.check(0, "Data Entered Successfully", account);
        }
        return response;
    }

    public static Response serviceupdate() throws SQLException {
//        Account account = new Account();
        Response response = new Response();
        DB.getConnection();
        System.out.println("Enter Account No");
        account.setAccountNo(scan.nextInt());
        String query = "select * from account where Account_No=?";
        PreparedStatement p = DB.getConnection().prepareStatement(query);
        p.setInt(1, account.getAccountNo());
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            System.out.println("Enter Amount");
            account.setAmout(scan.nextInt());
            System.out.println("Enter Pin Code");
            account.setPinCode(scan.nextInt());
            System.out.println("Enter Customer Id");
            account.setCustomerid(scan.nextInt());
            System.out.println("Enter Account Type");
            account.setAccountType(scan.next());
            if(account.getAccountType().equals("1")){
                account.setAccountType(String.valueOf(Account_Type.CURRENT));
            } else if (account.getAccountType().equals("2")) {
                account.setAccountType(String.valueOf(Account_Type.SAVINGS));

            }
            else{
                response.check(1,"Incorrect selected type");
            }
            account = AccountDAO.accountupdate(account);
            if (account == null) {
                response.check(1, "Got Exception");
            } else {
                response.check(0, "Data Entered Successfully", account);
            }

        }
        return response;
    }
}


