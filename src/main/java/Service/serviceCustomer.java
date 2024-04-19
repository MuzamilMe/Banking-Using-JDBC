package Service;

import org.bank.DAO.CustomerDAO;
import org.bank.helper.DBManager;
import org.bank.POJO.Customer;
import org.bank.POJO.GetByPagination;
import org.bank.core.Response;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class serviceCustomer {
    static GetByPagination getByPagination = new GetByPagination();
    static Scanner scan = new Scanner(System.in);
    static Customer customer = new Customer();
    static DBManager DB = new DBManager();

    public static Response Servicecreate() throws SQLException {
        Response response = new Response();
        System.out.println("Enter Contact No");
        customer.setContactNo(scan.next());
        System.out.println("Enter First Name");
        customer.setFirstName(scan.next());
        System.out.println("Enter Last Name");
        customer.setLastName(scan.next());
        System.out.println("Enter City");
        customer.setCity(scan.next());
        System.out.println("Enter State");
        customer.setState(scan.next());
        System.out.println("Enter Country");
        customer.setCountry(scan.next());
        System.out.println("Enter Address");
        customer.setAddress(scan.nextLine());
        customer.setAddress(scan.nextLine());
        customer = CustomerDAO.customercreate(customer);
        if (customer == null) {
            response.check(1, "Got Exception");
        } else {
            response.check(0, "Customer Created", customer);
        }
        return response;
    }

    public static Response Servicepage() throws SQLException {
        Response response = new Response();
        List<Customer> list;
        System.out.println("Enter current page");
        getByPagination.setCurrentPage(scan.nextInt());
        System.out.println("Enter items per page");
        getByPagination.setItemsperPage(scan.nextInt());
        System.out.println("Sort By");
        getByPagination.setSortBy(scan.next());
        System.out.println("Enter Direction");
        getByPagination.setDirection(scan.next());
        list = CustomerDAO.showbypage(getByPagination);
        if (list == null) {
            response.check(1, "Got Exception");
        } else {
            response.check(0, "Got Data", customer);
        }
        return response;
    }

    public static Response delete() throws SQLException {
        Response response = new Response();
        System.out.println("Enter Customer id");
        customer.setCustomerId(scan.nextInt());
        customer = CustomerDAO.delete(customer);
        if (customer == null) {
            response.check(1, "Got Exception");
        } else {
            response.check(0, "Got Data", customer);
        }
        return response;
    }

    public static Response read() throws SQLException {
        Response response = new Response();
        System.out.println("Enter Customer id");
        customer.setCustomerId(scan.nextInt());
        customer = CustomerDAO.read(customer);
        if (customer != null) {
            response.check(0, "Got Data", customer);
        } else {
            response.check(1, "Got Exception");
        }
        return response;
    }

    public static Response serviceupdate() throws SQLException {
        Response response = new Response();
        DB.getConnection();
        System.out.println("Enter Customer id");
        customer.setCustomerId(scan.nextInt());
        String query = "select * from customer where Customer_id=?";
        PreparedStatement p = DB.getConnection().prepareStatement(query);
        p.setInt(1, customer.getCustomerId());
        ResultSet rs = p.executeQuery();
        if (rs.next()) {
            System.out.println("Enter First Name");
            customer.setFirstName(scan.next());
            System.out.println("Enter Last Name");
            customer.setLastName(scan.next());
            System.out.println("Enter City");
            customer.setCity(scan.next());
            System.out.println("Enter State");
            customer.setState(scan.next());
            System.out.println("Enter Country");
            customer.setCountry(scan.next());
            System.out.println("Enter Address");
            customer.setAddress(scan.nextLine());
            System.out.println("Enter Contact No");
            customer.setContactNo(scan.next());
            customer = CustomerDAO.customerupdate(customer);
            if (customer == null) {
                response.check(1, "Got Exception");
            } else {
                response.check(0, "Data Entered Successfully", customer);
            }
        }
        return response;
    }
}