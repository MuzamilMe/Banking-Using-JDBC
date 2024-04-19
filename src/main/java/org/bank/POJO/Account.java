package org.bank.POJO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Account {
    int customerid;
    int amout, pinCode;
    int accountNo;
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateOfCreate = dateFormat.format(date);
    String accountType;

    public Account() {
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public int getAccountNo() {

        return accountNo;
    }

    public void setAccountNo(int accountNo) {

        this.accountNo = accountNo;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {

        this.pinCode = pinCode;
    }

    public String getDateOfCreate() {

        return dateOfCreate;
    }

    public void setDateOfCreate(String Doc) {
        Doc = this.dateOfCreate;

    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getAmout() {
        return amout;
    }

    public void setAmout(int amout) {
        this.amout = amout;
    }

    @Override
    public String toString() {
        return "ServiceAccount{" +
                "customerid=" + customerid +
                ", amout=" + amout +
                ", pinCode=" + pinCode +
                ", accountNo=" + accountNo +
                ", dateOfCreate='" + dateOfCreate + '\'' +
                ", accountType='" + accountType + '\'' +
                "\n"+'}';
    }
}
