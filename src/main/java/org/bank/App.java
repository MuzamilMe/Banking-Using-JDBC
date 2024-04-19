package org.bank;

import Service.ServiceAccount;
import Service.serviceCustomer;
import Service.serviceTransaction;
import org.bank.POJO.Account;
import org.bank.core.Response;

import java.sql.SQLException;
import java.util.Scanner;


/**
 * @author Muzamil-M
 */
public class App {
    public static void main(String[] args) throws SQLException {
        new App().menu();
    }
    Response response = new Response();
    public void menu() {
        try {
        int choice;
        int choice2;
        Scanner scan = new Scanner(System.in);
        System.out.println("========== Enter Choice ==========");
        System.out.println("1. Customer Operations");
        System.out.println("2. Account Operations");
        System.out.println("3. Transaction Operations");
        System.out.println("4. Exit");
        System.out.println("Enter Choice");
        choice = scan.nextInt();
        switch (choice) {
            case 1:   // case for org.bank.Customer opreations
                System.out.println("Customer Operations");
                System.out.println("1. Create");
                System.out.println("2. Read");
                System.out.println("3. Get Customer By Pagination");
                System.out.println("4. Update");
                System.out.println("5. Delete");
                System.out.println("6. Back");
                System.out.println("Enter Opreation");
                choice2 = scan.nextInt();
                switch (choice2) {
                    case 1: // case Customer create
                        Response create = serviceCustomer.Servicecreate();
                        System.out.println(create.toString());
                        menu();
                        break;
                    case 2: // case for customer read
                        Response read = serviceCustomer.read();
                        System.out.println(read.toString());
                        menu();
                        break;
                    case 3:
                        Response page = serviceCustomer.Servicepage();
                        System.out.println(page.toString());
                        menu();
                        break;
                    case 4: // case for customer update
                        Response update = serviceCustomer.serviceupdate();
                        System.out.println(update.toString());
                        menu();
                        break;
                    case 5: // case for customer delete
                        Response delete = serviceCustomer.delete();
                        System.out.println(delete.toString());
                        menu();
                        break;
                    case 6: // case for back
                        menu();
                    default:
                        response.check(0,"Invalid choice");
                        System.out.println(response.toString());
                        menu();
                }
                // break for case 1: at first switch
                break;
            // case for course opreations
            case 2:
                System.out.println("Account Operations");
                System.out.println("1. Create");
                System.out.println("2. Read");
                System.out.println("3. Get Account By Pagination");
                System.out.println("4. Update");
                System.out.println("5. Delete");
                System.out.println("6. Back");
                System.out.println("Enter Opreation");
                choice2 = scan.nextInt();
                switch (choice2) {
                    case 1: // case for account create
                        Response create = ServiceAccount.Servicecreate();
                        System.out.println(create.toString());
                        menu();
                        break;
                    case 2:
                        Response read = ServiceAccount.Serviceread();
                        System.out.println(read.toString());
                        menu();
                        break;
                    case 3:
                        Response page = ServiceAccount.Servicepage();
                        System.out.println(page.toString());
                        menu();
                        break;
                    case 4:
                        Response update = ServiceAccount.serviceupdate();
                        System.out.println(update.toString());
                        menu();
                        break;
                    case 5:
                        Response delete = ServiceAccount.servicedelete();
                        System.out.println(delete.toString());
                        menu();
                        break;
                    case 6:
                        menu();
                        break;
                    default:
                        response.check(0,"Invalid choice");
                        System.out.println(response.toString());
                        menu();
                }
                // break for case2 at first switch
                break;
            // case 3 for transactions
            case 3:
                System.out.println("Transaction Operations");
                System.out.println("1. Perform Transaction");
                System.out.println("2. Get Transaction by id");
                System.out.println("3. Get All Transactions");
                System.out.println("4. Get By Pagination");
                System.out.println("5. Back");
                System.out.println("Enter Opreation");
                choice2 = scan.nextInt();
                switch (choice2) {
                    case 1:
                        Response create = serviceTransaction.serviceCreate();
                        System.out.println(create.toString());
                        menu();
                        break;
                    case 2:
                        Response read = serviceTransaction.serviceRead();
                        System.out.println(read.toString());
                        menu();
                        break;
                    case 3:
                        Response t = serviceTransaction.serviceReadAll();
                        System.out.println(t.toString());
                        menu();
                        break;
                    case 4:
                        Response r = serviceTransaction.ServicepRead();
                        System.out.println(r.toString());
                        menu();
                        break;
                    case 5:
                        menu();
                        break;
                    default:
                        response.check(0,"Invalid choice");
                        System.out.println(response.toString());
                        menu();
                }
                break; // break for case3 at first switch
            //for exit
            case 5:
                System.exit(0);
                break;
            default:
                response.check(0,"Invalid choice");
                System.out.println(response.toString());
                menu();
                // menu again opens when an incorrect choice is entered

        } //switch for main menu
    }   catch (Exception e){
            response.check(0,"Invalid choice");
            System.out.println(response.toString());
            menu();
        }
    }

}
