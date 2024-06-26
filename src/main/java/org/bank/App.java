package org.bank;

import org.bank.Account.ACreate;
import org.bank.Account.ADelete;
import org.bank.Account.ARead;
import org.bank.Account.AUpdate;
import org.bank.Customer.CDelete;
import org.bank.Customer.CRead;
import org.bank.Customer.CUpdate;
import org.bank.Customer.Create;
import org.bank.Transaction.TCreate;
import org.bank.Transaction.TRead;

import java.sql.SQLException;
import java.util.Scanner;


/**
 * @author Muzamil-M
 */
public class App {
    public void menu() throws SQLException {
        char choice;
        char choice2;
        Scanner scan = new Scanner(System.in);
        System.out.println("========== Enter Choice ==========");
        System.out.println("1. Customer Operations");
        System.out.println("2. Account Operations");
        System.out.println("3. Transaction Operations");
        System.out.println("4. Exit");
        System.out.println("Enter Choice");
        choice = scan.next().charAt(0);
        switch (choice) {
            case '1':   // case for org.bank.Customer opreations
                System.out.println("Customer Operations");
                System.out.println("1. Create");
                System.out.println("2. Read");
                System.out.println("3. Get Customer By Pagination");
                System.out.println("4. Update");
                System.out.println("5. Delete");
                System.out.println("6. Back");
                System.out.println("Enter Opreation");
                choice2 = scan.next().charAt(0);
                switch (choice2) {
                    case '1': // case for org.bank.Customer create
                        new Create().customercreate();
                        menu();
                        break;
                    case '2': // case for customer read
                        new CRead().read();
                        menu();
                        break;
                    case '3':
                        new CRead().showbypage();
                        break;
                    case '4': // case for customer update
                        new CUpdate().customerupdate();
                        menu();
                        break;
                    case '5': // case for customer delete
                        new CDelete().delete();
                        menu();
                        break;
                    case '6': // case for back
                        menu();
                    default:
                        System.out.println("Invalid choice");
                        menu();
                }
                // break for case 1: at first switch
                break;
            // case for course opreations
            case '2':
                System.out.println("Account Operations");
                System.out.println("1. Create");
                System.out.println("2. Read");
                System.out.println("3. Get Account By Pagination");
                System.out.println("4. Update");
                System.out.println("5. Delete");
                System.out.println("6. Back");
                System.out.println("Enter Opreation");
                choice2 = scan.next().charAt(0);
                switch (choice2) {
                    case '1': // case for Student create
                        new ACreate().accountcreate();
                        menu();
                        break;
                    case '2':
                        new ARead().read();
                        menu();
                        break;
                    case '3':
                        new ARead().showbypage();
                        break;
                    case '4':
                        new AUpdate().accountupdate();
                        menu();
                        break;
                    case '5':
                        new ADelete().delete();
                        menu();
                        break;
                    case '6':
                        menu();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        menu();
                }
                // break for case2 at first switch
                break;
            // case 3 for department
            case '3':
                System.out.println("Transaction Operations");
                System.out.println("1. Perform Transaction");
                System.out.println("2. Get Transaction by id");
                System.out.println("3. Get All Transactions");
                System.out.println("4. Get By Pagination");
                System.out.println("5. Back");
                System.out.println("Enter Opreation");
                choice2 = scan.next().charAt(0);
                switch (choice2) {
                    case '1':
                        new TCreate().create();
                        menu();
                        break;
                    case '2':
                        new TRead().read();
                        menu();
                        break;
                    case '3':
                        new TRead().showall();
                        break;
                    case '4':
                        new TRead().showbypage();
                        break;
                    case '5':
                        menu();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        menu();
                }
                break; // break for case3 at first switch
            //for exit
            case '5':
                System.exit(0);
                break;
            default:
                System.out.println("Invalid Choice");
                menu();
                // menu again opens when an incorrect choice is entered

        } //switch for main menu
    }

    public static void main(String[] args) throws SQLException {
        new App().menu();

    }
}
