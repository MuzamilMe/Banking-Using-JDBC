package org.bank.DAO;

import Service.ServiceAccount;
import org.bank.App;
import org.bank.helper.DBManager;
import org.bank.POJO.Account;
import org.bank.POJO.GetByPagination;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class AccountDAO {
    static Date date = Calendar.getInstance().getTime();
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static String now = dateFormat.format(date);
    static DBManager DB = new DBManager();
    static GetByPagination g = new GetByPagination();

    public static Account accountcreate(Account account) throws SQLException {
        try {
            DB.getConnection();
            String sql = "INSERT into account values(?,?,?,?,?,?)";
            PreparedStatement ps = DB.getConnection().prepareStatement(sql);
            ps.setInt(1, account.getAccountNo());
            ps.setInt(2, account.getPinCode());
            ps.setString(3, account.getDateOfCreate());
            ps.setString(4, account.getAccountType());
            ps.setInt(5, account.getAmout());
            ps.setInt(6, account.getCustomerid());
            ps.executeUpdate();
        } catch (Exception e) {
            account = null;
        } finally {
            DB.getConnection().close();
        }

        return account;
    }

    public static Account delete(Account account) {
        try {
            DB.getConnection();
            String sql = "DELETE from account where Account_no=?";
            PreparedStatement ps = DB.getConnection().prepareStatement(sql);
            ps.setInt(1, account.getAccountNo());
            int rowsaffected = ps.executeUpdate();
            if (rowsaffected > 0) {
                System.out.println("Data Deleted");
                System.out.println("Account No: " + account.getAccountNo());
            } else {
                account = null;
                System.out.println("Account Not Found");
                ServiceAccount.servicedelete();
            }
        }   //try off
        catch (Exception e) {
            account = null;
            e.printStackTrace();

        } // catch off
        finally {
            try {
                if (DB.getConnection() != null) {
                    DB.getConnection().close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } // finally off
        return account;
    }
        public static boolean check_Account(int account_No){
        boolean flag = false;
            DB.getConnection();
        try {
            String sql = "Select Account_No from account where Account_No=?";
            PreparedStatement ps = DB.getConnection().prepareStatement(sql);
                ps.setInt(1, account_No);
                ResultSet rs = ps.executeQuery();
                rs.next();
                if(rs.getInt("account_no")==account_No){
                   flag =true;

                }
                else {
                    flag=false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

              return flag;
        }
    public static Account read(Account account)  {
            // Getting Databse Connection
            DB.getConnection();
            try {
            String sql = "Select * from account where Account_No="+account.getAccountNo();
            Statement ps = DB.getConnection().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            rs.next();
                System.out.println();
                account.setAccountNo(rs.getInt("Account_No"));
                account.setDateOfCreate(rs.getString("Date_Of_Create"));
                account.setAccountType(rs.getString("Account_Type"));
                account.setCustomerid(rs.getInt("Customer_Customer_id"));
                account.setAmout(rs.getInt("Amount"));
                account.setPinCode(rs.getInt("pin_Code"));
        }
        catch (Exception e) {
                e.printStackTrace();
                  account = null;
        } finally {
            if (DB.getConnection()!= null) {
                try {
                    DB.getConnection().close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return account;
    }

    public static int totalPages() throws SQLException {
        DB.getConnection();
        float pages = 0;
        String query = "Select count(Account_No) from account";
        Statement st = DB.getConnection().createStatement();
        ResultSet rs = st.executeQuery(query);
        try {
            if (rs.next()) {
                //getting total rows from database
                float rows = rs.getInt(1);
                pages = rows / g.getItemsperPage();
                pages = Math.round(pages);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // it return totalpages
        return (int) pages;
    }

    public static List<Account> showbypage(GetByPagination getByPagination) throws SQLException {
        List<Account> list = new ArrayList<>();
        try {
            DB.getConnection();
            if (totalPages() < getByPagination.getCurrentPage()) {
                System.out.println("Only " + totalPages() + " Pages Exist");
                ServiceAccount.Servicepage();
            } else {
                int offset = getByPagination.getCurrentPage() * getByPagination.getItemsperPage();
                String sql = "SELECT * FROM account ORDER BY " + getByPagination.getSortBy() + " " + getByPagination.getDirection() + " LIMIT " + getByPagination.getItemsperPage() + " OFFSET " + offset;
                Statement statement = DB.getConnection().createStatement();
                ResultSet rs = statement.executeQuery(sql);

                while (rs.next()) {
                    Account account = new Account();
                    account.setAccountNo(rs.getInt(1));
                    account.setPinCode(rs.getInt(2));
                    account.setAccountType(rs.getString(4));
                    account.setAmout(rs.getInt(5));
                    account.setCustomerid(rs.getInt(6));
                    //adding all data to list
                    list.add(account);
                }
            }
        } catch (Exception e) {
            list = null;
            System.out.println("Data Not Found");
            new App().menu();

        }
        return list;
    }

    public static Account accountupdate(Account account) throws SQLException {
        try {
            DB.getConnection();
            account.setDateOfCreate(now);
            String sql = "UPDATE account SET  Pin_Code=?, Account_Type=? , Amount = ? , Customer_Customer_id=?   where Account_No = ?";
            PreparedStatement ps = DB.getConnection().prepareStatement(sql);
            ps.setInt(5, account.getAccountNo());
            ps.setInt(1, account.getPinCode());
            ps.setString(2, account.getAccountType());
            ps.setInt(3, account.getAmout());
            ps.setInt(4, account.getCustomerid());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
            }
        } catch (Exception e) {
            account = null;
            System.out.println("Account Not found");
        } finally {
            DB.getConnection().close();
        }

        return account;
    }

        }

