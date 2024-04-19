package org.bank.DAO;

import Service.serviceCustomer;
import org.bank.App;
import org.bank.helper.DBManager;
import org.bank.POJO.Customer;
import org.bank.POJO.GetByPagination;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerDAO {
    static DBManager DB = new DBManager();
    static GetByPagination getByPagination = new GetByPagination();

    //create customer
    public static Customer customercreate(Customer customer) throws SQLException {
        try {
            DB.getConnection();
            String sql = "INSERT into Customer values(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = DB.getConnection().prepareStatement(sql);
            ps.setInt(1, customer.getCustomerId());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getCity());
            ps.setString(5, customer.getState());
            ps.setString(6, customer.getCountry());
            ps.setString(7, customer.getAddress());
            ps.setString(8, customer.getContactNo());
            ps.executeUpdate();
        }
        catch (Exception e) {
            customer = null;
            System.out.println("Enter Correct Data");
            new App().menu();

        } finally {
            DB.getConnection().close();
        }

        return customer;
    }

    // read customer

    public static float totalPages() throws SQLException {
        float pages = 0;
        String query = "Select count(Customer_id) from Customer";
        Statement statement = DB.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(query);
        try {
            if (rs.next()) {
                //getting total rows from database
                float rows = rs.getInt(1);
                pages = rows / getByPagination.getItemsperPage();
                pages = Math.round(pages);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        // it return totalpages
        return pages;
    }

    public static List<Customer> showbypage(GetByPagination getByPagination) throws SQLException {
        DB.getConnection();
        List<Customer> list = new ArrayList<>();

        if (totalPages() < getByPagination.getCurrentPage()) {
            System.out.println("Only " + totalPages() + " Pages Exist");
            serviceCustomer.Servicepage();
        } else {
            int offset = getByPagination.getCurrentPage() * getByPagination.getItemsperPage();
            String sql = "SELECT * FROM customer ORDER BY " + getByPagination.getSortBy() + " " + getByPagination.getDirection() + " LIMIT " + getByPagination.getItemsperPage() + " OFFSET " + offset;
            Statement st = DB.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            try {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt(1));
                    customer.setFirstName(rs.getString(2));
                    customer.setLastName(rs.getString(3));
                    customer.setCity(rs.getString(4));
                    customer.setState(rs.getString(5));
                    customer.setCountry(rs.getString(6));
                    customer.setAddress(rs.getString(7));
                    customer.setContactNo(rs.getString(8));
                    list.add(customer);
                    System.out.println(customer.toString());
                }

            } catch (Exception e) {
                list = null;
                System.out.println("Data Not Found");

            }

        }
        return list;
    }

    public static Customer read(Customer customer) throws SQLException {
        try {
            // Getting Databse getConnection()
            DB.getConnection();
            String sql = "Select * from customer where Customer_id="+customer.getCustomerId();
            Statement statement = DB.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                customer.setFirstName(rs.getString("First_Name"));
                customer.setLastName(rs.getString("Last_Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setCity(rs.getString("City"));
                customer.setState(rs.getString("State"));
                customer.setCountry(rs.getString("Country"));
                customer.setContactNo(rs.getString("ContactNo"));
            } else {
                customer = null;
            }

        } catch (Exception e) {
            customer = null;
            System.out.println("Customer Not Found");

        } finally {
            if (DB.getConnection() != null) {
                DB.getConnection().close();
            }
        }
        return customer;
    }

    //customer update
    public static Customer customerupdate(Customer customer) throws SQLException {
        try {
            String sql = "UPDATE customer SET  First_Name=?, Last_Name=? , City = ? , State=? , country=? , Address=? , ContactNo=?   where Customer_id = ?";
            PreparedStatement ps = DB.getConnection().prepareStatement(sql);
            ps.setInt(8, customer.getCustomerId());
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getCity());
            ps.setString(4, customer.getState());
            ps.setString(5, customer.getCountry());
            ps.setString(6, customer.getAddress());
            ps.setString(7, customer.getContactNo());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
            } else {
                System.out.print("Not updated");
                customer = null;
            }

        } catch (Exception e) {
            customer = null;
            e.printStackTrace();
        } finally {
            DB.getConnection().close();
        }

        return customer;
    }

    // customer delete
    public static Customer delete(Customer customer) throws SQLException {
        try {
            // Getting Databse getConnection()
            DB.getConnection();
            String query = "select * from customer where Customer_id=?";
            PreparedStatement ps = DB.getConnection().prepareStatement(query);
            ps.setInt(1, customer.getCustomerId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer.setFirstName(rs.getString("First_Name"));
                customer.setLastName(rs.getString("Last_Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setCity(rs.getString("City"));
                customer.setState(rs.getString("State"));
                customer.setCountry(rs.getString("Country"));
                customer.setContactNo(rs.getString("ContactNo"));
                String sql = "DELETE from customer where Customer_id=?";
                PreparedStatement preparedStatement = DB.getConnection().prepareStatement(sql);
                ps.setInt(1, customer.getCustomerId());
                int rowsaffected = preparedStatement.executeUpdate();
                if (rowsaffected > 0) {
                    System.out.println("Data Deleted");
                    System.out.println(customer.toString());
                } else {
                    System.out.println("Enter Correct Data");
                    new App().menu();
                }
            }
        }   //try off
        catch (Exception e) {
            customer = null;
            System.out.println("Customer id not found");
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
        return customer;
    }
}
